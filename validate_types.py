#!/usr/bin/env python3
"""[안정망] KfcGen 호출 타입 정합성 정적 검증기 (javac 이전 게이트).

생성된 Java 의 모든 `KfcGen.<method>(...)` 호출을 KfcGen.java 시그니처에 대조해,
'컴파일이 깨질 확정적 타입 불일치'를 전수 검출한다. 특히 매크로 값(macroArgs.get →
String)이나 문자열 리터럴이 수치(double/int/float/long/...)·boolean 파라미터에 들어가는
사례(예전 셀렉터 박스 dx/dy/dz 버그류)를 생성 직후 잡아 바닐라 무결성 붕괴를 막는다.

오탐 최소화를 위해 '확정적으로 String/숫자/불리언' 으로 판정되는 인자만 대상으로 하고,
판정이 애매한 인자(변수/일반 표현식)는 통과시킨다(보수적).

사용:  python3 validate_types.py <KfcGen.java> <생성src_root> [--strict]
반환:  이슈 0 → exit 0 ; 이슈 있음 → 리포트 + (--strict 면 exit 1)
"""
import re, sys, os, glob

NUM={'double','float','int','long','short','byte'}

def load_sigs(kfc):
    s=open(kfc, encoding='utf-8').read()
    sigs={}
    pat=re.compile(r'\b(?:public|protected|private)?\s*static\s+(?:final\s+)?'
                   r'([\w.<>,?\[\]\s]+?)\s+([a-zA-Z_]\w*)\s*\(([^;{]*?)\)\s*\{', re.S)
    for m in pat.finditer(s):
        name=m.group(2); params=m.group(3).strip()
        if name in ('if','for','while','switch','catch','new','return','synchronized'): continue
        depth=0; buf=''; parts=[]
        for ch in params:
            if ch in '<([': depth+=1
            elif ch in '>)]': depth-=1
            if ch==',' and depth==0: parts.append(buf); buf=''
            else: buf+=ch
        if buf.strip(): parts.append(buf)
        types=[]
        varargs=False
        for p in parts:
            p=re.sub(r'^\s*final\s+','',p.strip())
            if '...' in p: varargs=True
            p=p.replace('...',' ')
            mm=re.match(r'(.+?)\s+\w+$', p)
            types.append((mm.group(1).strip() if mm else p).strip())
        sigs.setdefault(name,[]).append((types,varargs))
    return sigs

def _split_top(s, seps):
    depth=0;instr=False;esc=False;buf='';out=[]
    for ch in s:
        if instr:
            if esc:esc=False
            elif ch=='\\':esc=True
            elif ch=='"':instr=False
            buf+=ch;continue
        if ch=='"':instr=True;buf+=ch;continue
        if ch in '([{<':depth+=1
        elif ch in ')]}>':depth-=1
        if ch in seps and depth==0: out.append(buf);buf='';continue
        buf+=ch
    out.append(buf)
    return [x.strip() for x in out]

STR_BARE=re.compile(r'^macroArgs\.get\("[^"]*"\)$')
STRLIT=re.compile(r'^"(?:[^"\\]|\\.)*"$')

def arg_kind(a):
    """확정적으로만 분류(애매하면 OTHER=통과)."""
    a=a.strip()
    if STR_BARE.match(a) or STRLIT.match(a): return 'STRING'
    ops=_split_top(a,'+')
    if len(ops)>1 and any(STRLIT.match(o) or STR_BARE.match(o) for o in ops):
        return 'STRING'                     # 최상위 문자열 concat
    if re.match(r'^-?\d', a): return 'NUM'
    if a in ('true','false'): return 'BOOL'
    return 'OTHER'

def param_kind(t):
    t=t.strip()
    if t in NUM: return 'NUM'
    if t=='boolean': return 'BOOL'
    if t in ('String','CharSequence'): return 'STRING'
    return 'OTHER'

def check_file(path, sigs):
    s=open(path, encoding='utf-8').read()
    issues=[]
    for m in re.finditer(r'KfcGen\.([a-zA-Z_]\w*)\(', s):
        name=m.group(1)
        if name not in sigs: continue
        i=m.end(); depth=1; instr=False; esc=False; j=i
        while j<len(s) and depth:
            ch=s[j]
            if instr:
                if esc: esc=False
                elif ch=='\\': esc=True
                elif ch=='"': instr=False
            else:
                if ch=='"': instr=True
                elif ch=='(': depth+=1
                elif ch==')': depth-=1
            j+=1
        if depth!=0: continue
        argstr=s[i:j-1]
        args=_split_top(argstr,',') if argstr.strip() else []
        # 오버로드 중 인자수 일치(가변인자 허용)
        ovs=[t for (t,va) in sigs[name] if len(t)==len(args) or (va and len(args)>=len(t)-1)]
        if not ovs: continue
        for pos,a in enumerate(args):
            ak=arg_kind(a)
            if ak=='OTHER': continue
            pks=set()
            for t in ovs:
                pks.add(param_kind(t[pos]) if pos<len(t) else 'OTHER')
            bad=(ak=='STRING' and pks<= {'NUM','BOOL'}) or \
                (ak=='NUM'    and pks<= {'STRING','BOOL'}) or \
                (ak=='BOOL'   and pks<= {'STRING','NUM'})
            if bad:
                ln=s.count('\n',0,m.start())+1
                issues.append((path,ln,name,pos,ak,sorted(pks),a[:50]))
    return issues

def run(kfc, root, strict=False):
    sigs=load_sigs(kfc)
    files=glob.glob(os.path.join(root,'**','*.java'),recursive=True)
    alli=[]
    for f in files: alli+=check_file(f,sigs)
    if not alli:
        print(f"[type-check] OK — {len(files)} files, KfcGen 호출 타입 불일치 0")
        return 0
    print(f"[type-check] ✗ {len(alli)} 타입 불일치 (파일 {len(files)}개 중):")
    for (path,ln,name,pos,ak,pks,a) in alli:
        print(f"  {os.path.relpath(path,root)}:{ln}  KfcGen.{name}  인자#{pos}={ak} → 파라미터∈{pks}   «{a}»")
    return 1 if strict else 0

if __name__=='__main__':
    a=[x for x in sys.argv[1:] if not x.startswith('--')]
    strict='--strict' in sys.argv
    sys.exit(run(a[0], a[1], strict))