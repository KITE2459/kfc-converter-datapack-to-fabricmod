import random, re, sys
sys.path.insert(0, __import__('os').path.dirname(__import__('os').path.abspath(__file__)))
import opt_post as op

INT_MIN=-2**31; INT_MAX=2**31-1
HOLDERS=['#a','#b','#c','#d']; OBJ='dm'

class Rec:
    def __init__(self,text): self.text=text; self.size=len(text.encode())

def sdiv(a,b):
    if b==0: return a               # KfcGen.sdiv 0-guard (must mirror runtime)
    import math
    return math.floor(a/b)          # mcfunction floored division
def smod(a,b):
    if b==0: return a
    return a-b*(a//b)               # floored modulo

def make_eval_ns(board, locals_):
    def gs(h,o): return board.get((h,o),0)
    def sm(h,o,mn,mx): v=board.get((h,o),0); return (mn<=v<=mx)
    ns={'gs':gs,'sm':sm,'sdiv':sdiv,'smod':smod,'min':min,'max':max,'INTMIN':INT_MIN,'INTMAX':INT_MAX}
    ns.update(locals_)
    return ns

def to_py(expr):
    e=expr
    e=re.sub(r'KfcGen\.getScore\(sb, "([^"]+)", "([^"]+)"\)', r'gs("\1","\2")', e)
    e=re.sub(r'KfcGen\.scoreMatches\(sb, "([^"]+)", "([^"]+)", ([^,]+), ([^)]+)\)', r'sm("\1","\2",\3,\4)', e)
    e=e.replace('KfcGen.sdiv(','sdiv(').replace('KfcGen.smod(','smod(')
    e=e.replace('Math.min(','min(').replace('Math.max(','max(')
    e=e.replace('Integer.MIN_VALUE','INTMIN').replace('Integer.MAX_VALUE','INTMAX')
    e=e.replace('&&',' and ').replace('||',' or ')
    return e

def ev(expr, board, locals_):
    return eval(to_py(expr), {'__builtins__':{}}, make_eval_ns(board, locals_))

OBSERVE='KfcGen.__obs__('
def interpret(text, board0=None):
    board=dict(board0) if board0 else {}; locals_={}; obs=[]
    for raw in text.split('\n'):
        ln=raw.strip()
        if not ln or ln.startswith('//'): continue
        if ln.endswith('{') or ln=='}' or ln.startswith('static ') or ln.startswith('void '): continue
        # observe barrier: record score
        m=re.match(r'KfcGen\.__obs__\(sb, "([^"]+)", "([^"]+)"\);$', ln)
        if m: obs.append((m.group(1),m.group(2),board.get((m.group(1),m.group(2)),0))); continue
        # if (COND) STMT
        mif=re.match(r'if \((.+?)\) (.+)$', ln)
        cond=None
        if mif:
            cond=mif.group(1); ln=mif.group(2)
            if not ev(cond, board, locals_):
                continue
        # local decl / assign
        m=re.match(r'int (_kd\d+) = (.+);$', ln)
        if m: locals_[m.group(1)]=ev(m.group(2),board,locals_); continue
        m=re.match(r'(_kd\d+) ([+\-*]?=) (.+);$', ln)
        if m:
            v=m.group(1); opx=m.group(2); rhs=ev(m.group(3),board,locals_)
            if opx=='=': locals_[v]=rhs
            elif opx=='+=': locals_[v]=locals_[v]+rhs
            elif opx=='-=': locals_[v]=locals_[v]-rhs
            elif opx=='*=': locals_[v]=locals_[v]*rhs
            continue
        m=re.match(r'KfcGen\.setScore\(sb, "([^"]+)", "([^"]+)", (.+)\);$', ln)
        if m: board[(m.group(1),m.group(2))]=ev(m.group(3),board,locals_); continue
        m=re.match(r'KfcGen\.addScore\(sb, "([^"]+)", "([^"]+)", (.+)\);$', ln)
        if m: k=(m.group(1),m.group(2)); board[k]=board.get(k,0)+ev(m.group(3),board,locals_); continue
        m=re.match(r'KfcGen\.opScoreN\(sb, "([^"]+)", "([^"]+)", "([^"]+)", (-?\d+)\);$', ln)
        if m:
            k=(m.group(1),m.group(2)); a=board.get(k,0); n=int(m.group(4)); o=m.group(3)
            board[k]={'=':n,'+=':a+n,'-=':a-n,'*=':a*n,'/=':sdiv(a,n),'%=':smod(a,n),'<':min(a,n),'>':max(a,n)}[o]; continue
        m=re.match(r'KfcGen\.opScore\(sb, "([^"]+)", "([^"]+)", "([^"]+)", "([^"]+)", "([^"]+)"\);$', ln)
        if m:
            k=(m.group(1),m.group(2)); a=board.get(k,0); b=board.get((m.group(4),m.group(5)),0); o=m.group(3)
            board[k]={'=':b,'+=':a+b,'-=':a-b,'*=':a*b,'/=':sdiv(a,b),'%=':smod(a,b),'<':min(a,b),'>':max(a,b)}[o]; continue
        raise RuntimeError("UNPARSED demoted line: "+ln)
    return board, obs

# ---- random program generator (pass-2.9 input syntax) ----
def rnd_expr(depth=0):
    r=random.random()
    if depth>=2 or r<0.45: return str(random.randint(-50,50))
    if r<0.75:
        h=random.choice(HOLDERS); return f'KfcGen.getScore(sb, "{h}", "{OBJ}")'
    a=rnd_expr(depth+1); b=rnd_expr(depth+1); o=random.choice(['+','-','*'])
    return f'({a} {o} {b})'

def rnd_bound(lo=True):
    r=random.random()
    if r<0.2: return 'Integer.MIN_VALUE' if lo else 'Integer.MAX_VALUE'
    return str(random.randint(-30,30))

def gen_line():
    r=random.random()
    h=random.choice(HOLDERS)
    if r<0.30:
        return f'        KfcGen.setScore(sb, "{h}", "{OBJ}", {rnd_expr()});'
    if r<0.50:
        return f'        KfcGen.addScore(sb, "{h}", "{OBJ}", {rnd_expr()});'
    if r<0.62:
        op=random.choice(['=','+=','-=','*=','/=','%=','<','>']); return f'        KfcGen.opScoreN(sb, "{h}", "{OBJ}", "{op}", {random.randint(-20,20)});'
    if r<0.72:
        sh=random.choice(HOLDERS); op=random.choice(['=','+=','-=','*=','<','>']); return f'        KfcGen.opScore(sb, "{h}", "{OBJ}", "{op}", "{sh}", "{OBJ}");'
    if r<0.92:
        # EBB conditional
        ch=random.choice(HOLDERS); lo=rnd_bound(True); hi=rnd_bound(False)
        th=random.choice(HOLDERS); wr=random.random()
        if wr<0.5: then=f'KfcGen.setScore(sb, "{th}", "{OBJ}", {rnd_expr(1)})'
        elif wr<0.8: then=f'KfcGen.addScore(sb, "{th}", "{OBJ}", {rnd_expr(1)})'
        else:
            op=random.choice(['=','+=','-=','*=','/=','%=','<','>']); then=f'KfcGen.opScoreN(sb, "{th}", "{OBJ}", "{op}", {random.randint(-20,20)})'
        return f'        if (KfcGen.scoreMatches(sb, "{ch}", "{OBJ}", {lo}, {hi})) {then};'
    # observe barrier
    oh=random.choice(HOLDERS); return f'        KfcGen.__obs__(sb, "{oh}", "{OBJ}");'

def gen_program(nlines):
    hdr='    static void run(ServerCommandSource src) {'
    body=[gen_line() for _ in range(nlines)]
    return hdr+'\n'+'\n'.join(body)+'\n    }'

lines_map={'f':['scoreboard objectives add dm dummy']}
random.seed(12345)
N=40000
mism=0; demoted_total=0; had_demote=0; examples=[]
for it in range(N):
    prog=gen_program(random.randint(2,14))
    rec=Rec(prog)
    # random initial board
    init={(h,OBJ):random.randint(-40,40) for h in HOLDERS}
    orig_board, orig_obs = interpret(prog, init)
    res=op.demote_scores([rec], lines_map, verbose=False)
    demoted_total+=res['demoted']
    if res['demoted']>0: had_demote+=1
    dem_board, dem_obs = interpret(rec.text, init)
    if orig_board!=dem_board or orig_obs!=dem_obs:
        mism+=1
        if len(examples)<3:
            examples.append((prog, rec.text, orig_board, dem_board, orig_obs, dem_obs))
print(f"N={N} demoted_ops_total={demoted_total} programs_with_demotion={had_demote} MISMATCHES={mism}")
for ex in examples:
    print("="*60,"\nORIG:\n",ex[0],"\nDEMOTED:\n",ex[1],"\nboard o/d:",ex[2],ex[3],"\nobs o/d:",ex[4],ex[5])
