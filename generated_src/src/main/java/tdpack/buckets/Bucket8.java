package tdpack.buckets;

import tdpack.generated.KfcGen;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/** Auto-bucketed: 61 datapack functions. */
public final class Bucket8 {
    private Bucket8() { throw new UnsupportedOperationException(); }

    public static void _m_7f8c2e6e_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_execute(ServerCommandSource source) {
        _m_7f8c2e6e_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source);
    }

    public static int _m_7f8c2e6e_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.55 ~0 ~-0.55 if entity @n[type=marker,tag=hitscan-marker,dx=1.1,dy=2.27,dz=1.1] run return 1
        {
            ServerCommandSource kfcSrc89 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.55), (source.getPosition().y + 0.0), (source.getPosition().z + -0.55)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_breeze"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc89.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.1, 2.27, 1.1)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.6 ~-0.5 ~-0.6 if entity @n[type=marker,tag=hitscan-marker,dx=1.2,dy=1,dz=1.2] run return 1
        {
            ServerCommandSource kfcSrc90 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.6), (source.getPosition().y + -0.5), (source.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_cave_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc90.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.2, 1, 1.2)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.55 ~0 ~-0.55 if entity @n[type=marker,tag=hitscan-marker,dx=1.1,dy=2.2,dz=1.1] run return 1
        {
            ServerCommandSource kfcSrc91 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.55), (source.getPosition().y + 0.0), (source.getPosition().z + -0.55)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_creeper"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc91.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.1, 2.2, 1.1)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-0.55 ~0 ~-0.55 if entity @n[type=marker,tag=hitscan-marker,dx=1.1,dy=2.45,dz=1.1] run return 1
        {
            ServerCommandSource kfcSrc92 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.55), (source.getPosition().y + 0.0), (source.getPosition().z + -0.55)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_drowned"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc92.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.1, 2.45, 1.1)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.55 ~0 ~-0.55 if entity @n[type=marker,tag=hitscan-marker,dx=1.1,dy=3.4,dz=1.1] run return 1
        {
            ServerCommandSource kfcSrc93 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.55), (source.getPosition().y + 0.0), (source.getPosition().z + -0.55)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_enderman"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc93.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.1, 3.4, 1.1)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.45 ~-0.7 ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.5,dz=0] positioned ~-0.1 ~0 ~-0.1 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc94 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), (source.getPosition().y + -0.7), (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc95 = kfcSrc94.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc94.getPosition().x + -0.1), (kfcSrc94.getPosition().y + 0.0), (kfcSrc94.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_endermite"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc94.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.5, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc95.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.7 ~-0.5 ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=1.4,dy=1,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc96 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.5), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_phantom"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc96.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.4, 1, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.45 ~-0.5 ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.5,dz=0] positioned ~-0.1 ~0 ~-0.1 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc97 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), (source.getPosition().y + -0.5), (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc98 = kfcSrc97.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc97.getPosition().x + -0.1), (kfcSrc97.getPosition().y + 0.0), (kfcSrc97.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_rabbit"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc97.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.5, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc98.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-1.225 ~0 ~-1.225 if entity @n[type=marker,tag=hitscan-marker,dx=2.45,dy=2.7,dz=2.45] run return 1
        {
            ServerCommandSource kfcSrc99 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.225), (source.getPosition().y + 0.0), (source.getPosition().z + -1.225)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_ravager"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc99.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.45, 2.7, 2.45)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.55 ~0 ~-0.55 if entity @n[type=marker,tag=hitscan-marker,dx=1.1,dy=2.49,dz=1.1] run return 1
        {
            ServerCommandSource kfcSrc100 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.55), (source.getPosition().y + 0.0), (source.getPosition().z + -0.55)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc100.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.1, 2.49, 1.1)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-0.94824 ~0 ~-0.94824 if entity @n[type=marker,tag=hitscan-marker,dx=1.89648,dy=2.1,dz=1.89648] run return 1
        {
            ServerCommandSource kfcSrc101 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.94824), (source.getPosition().y + 0.0), (source.getPosition().z + -0.94824)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton_horse"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc101.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.89648, 2.1, 1.89648)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-0.95 ~-0.1 ~-0.95 if entity @n[type=marker,tag=hitscan-marker,dx=1.9,dy=1.4,dz=1.9] run return 1
        {
            ServerCommandSource kfcSrc102 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.95), (source.getPosition().y + -0.1), (source.getPosition().z + -0.95)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc102.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.9, 1.4, 1.9)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.45 ~-0.2 ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.5,dz=0] positioned ~-0.1 ~0 ~-0.1 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc103 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), (source.getPosition().y + -0.2), (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc104 = kfcSrc103.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc103.getPosition().x + -0.1), (kfcSrc103.getPosition().y + 0.0), (kfcSrc103.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_vex"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc103.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.5, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc104.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.7 ~0 ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=1.4,dy=3.4,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc105 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + 0.0), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_warden"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc105.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.4, 3.4, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.6 ~0 ~-0.6 if entity @n[type=marker,tag=hitscan-marker,dx=1.2,dy=2.9,dz=1.2] run return 1
        {
            ServerCommandSource kfcSrc106 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.6), (source.getPosition().y + 0.0), (source.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_wither_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc106.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.2, 2.9, 1.2)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.4 ~-0.025 ~-0.4 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.5,dz=0] positioned ~-0.2 ~0 ~-0.2 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
        {
            ServerCommandSource kfcSrc107 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.4), (source.getPosition().y + -0.025), (source.getPosition().z + -0.4)));
            ServerCommandSource kfcSrc108 = kfcSrc107.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc107.getPosition().x + -0.2), (kfcSrc107.getPosition().y + 0.0), (kfcSrc107.getPosition().z + -0.2)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_baby"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc107.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0.5, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc108.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0, 0, 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.58 ~0 ~-0.58 if entity @n[type=marker,tag=hitscan-marker,dx=1.16,dy=2.645,dz=1.16] run return 1
        {
            ServerCommandSource kfcSrc109 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.58), (source.getPosition().y + 0.0), (source.getPosition().z + -0.58)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_1"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc109.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.16, 2.645, 1.16)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.725 ~0 ~-0.725 if entity @n[type=marker,tag=hitscan-marker,dx=1.45,dy=3.15,dz=1.45] run return 1
        {
            ServerCommandSource kfcSrc110 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.725), (source.getPosition().y + 0.0), (source.getPosition().z + -0.725)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_5"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc110.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.45, 3.15, 1.45)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-0.825 ~0 ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=1.65,dy=3.75,dz=1.65] run return 1
        {
            ServerCommandSource kfcSrc111 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.825), (source.getPosition().y + 0.0), (source.getPosition().z + -0.825)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_8"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc111.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.65, 3.75, 1.65)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-0.9 ~0 ~-0.9 if entity @n[type=marker,tag=hitscan-marker,dx=1.8,dy=4.3,dz=1.8] run return 1
        {
            ServerCommandSource kfcSrc112 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.9), (source.getPosition().y + 0.0), (source.getPosition().z + -0.9)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_2"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc112.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.8, 4.3, 1.8)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-0.95 ~ ~-0.95 if entity @n[type=marker,tag=hitscan-marker,dx=0.9,dy=0.9,dz=0.9] run return 1
        {
            ServerCommandSource kfcSrc113 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.95), source.getPosition().y, (source.getPosition().z + -0.95)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zoglin"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc113.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 0.9, 0.9, 0.9)) {
                    return 1;
                }
            }
        }

        // return 0
        return 0;
    }

    public static void _m_cb772cc1_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_execute(ServerCommandSource source) {
        _m_cb772cc1_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source);
    }

    public static int _m_cb772cc1_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.675 ~0 ~-0.675 if entity @n[type=marker,tag=hitscan-marker,dx=1.35,dy=2.52,dz=1.35] run return 1
        {
            ServerCommandSource kfcSrc114 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.675), (source.getPosition().y + 0.0), (source.getPosition().z + -0.675)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_breeze"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc114.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.35, 2.52, 1.35)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.725 ~-0.5 ~-0.725 if entity @n[type=marker,tag=hitscan-marker,dx=1.45,dy=1.25,dz=1.45] run return 1
        {
            ServerCommandSource kfcSrc115 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.725), (source.getPosition().y + -0.5), (source.getPosition().z + -0.725)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_cave_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc115.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.45, 1.25, 1.45)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.675 ~0 ~-0.675 if entity @n[type=marker,tag=hitscan-marker,dx=1.35,dy=2.45,dz=1.35] run return 1
        {
            ServerCommandSource kfcSrc116 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.675), (source.getPosition().y + 0.0), (source.getPosition().z + -0.675)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_creeper"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc116.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.35, 2.45, 1.35)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-0.675 ~0 ~-0.675 if entity @n[type=marker,tag=hitscan-marker,dx=1.35,dy=2.7,dz=1.35] run return 1
        {
            ServerCommandSource kfcSrc117 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.675), (source.getPosition().y + 0.0), (source.getPosition().z + -0.675)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_drowned"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc117.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.35, 2.7, 1.35)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.675 ~0 ~-0.675 if entity @n[type=marker,tag=hitscan-marker,dx=1.35,dy=3.65,dz=1.35] run return 1
        {
            ServerCommandSource kfcSrc118 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.675), (source.getPosition().y + 0.0), (source.getPosition().z + -0.675)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_enderman"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc118.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.35, 3.65, 1.35)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.575 ~-0.7 ~-0.575 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=1.05,dz=1.15] run return 1
        {
            ServerCommandSource kfcSrc119 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.575), (source.getPosition().y + -0.7), (source.getPosition().z + -0.575)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_endermite"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc119.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.15, 1.05, 1.15)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.825 ~-0.5 ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=1.65,dy=1.25,dz=1.65] run return 1
        {
            ServerCommandSource kfcSrc120 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.825), (source.getPosition().y + -0.5), (source.getPosition().z + -0.825)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_phantom"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc120.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.65, 1.25, 1.65)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.575 ~-0.5 ~-0.575 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=1.25,dz=1.15] run return 1
        {
            ServerCommandSource kfcSrc121 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.575), (source.getPosition().y + -0.5), (source.getPosition().z + -0.575)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_rabbit"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc121.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.15, 1.25, 1.15)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-1.35 ~0 ~-1.35 if entity @n[type=marker,tag=hitscan-marker,dx=2.7,dy=2.95,dz=2.7] run return 1
        {
            ServerCommandSource kfcSrc122 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.35), (source.getPosition().y + 0.0), (source.getPosition().z + -1.35)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_ravager"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc122.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.7, 2.95, 2.7)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.675 ~0 ~-0.675 if entity @n[type=marker,tag=hitscan-marker,dx=1.35,dy=2.74,dz=1.35] run return 1
        {
            ServerCommandSource kfcSrc123 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.675), (source.getPosition().y + 0.0), (source.getPosition().z + -0.675)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc123.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.35, 2.74, 1.35)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-1.07324 ~0 ~-1.07324 if entity @n[type=marker,tag=hitscan-marker,dx=2.14648,dy=2.35,dz=2.14648] run return 1
        {
            ServerCommandSource kfcSrc124 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.07324), (source.getPosition().y + 0.0), (source.getPosition().z + -1.07324)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton_horse"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc124.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.14648, 2.35, 2.14648)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-1.075 ~-0.1 ~-1.075 if entity @n[type=marker,tag=hitscan-marker,dx=2.15,dy=1.65,dz=2.15] run return 1
        {
            ServerCommandSource kfcSrc125 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.075), (source.getPosition().y + -0.1), (source.getPosition().z + -1.075)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc125.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.15, 1.65, 2.15)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.575 ~-0.2 ~-0.575 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=1.55,dz=1.15] run return 1
        {
            ServerCommandSource kfcSrc126 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.575), (source.getPosition().y + -0.2), (source.getPosition().z + -0.575)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_vex"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc126.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.15, 1.55, 1.15)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.825 ~0 ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=1.65,dy=3.65,dz=1.65] run return 1
        {
            ServerCommandSource kfcSrc127 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.825), (source.getPosition().y + 0.0), (source.getPosition().z + -0.825)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_warden"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc127.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.65, 3.65, 1.65)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.725 ~0 ~-0.725 if entity @n[type=marker,tag=hitscan-marker,dx=1.45,dy=3.15,dz=1.45] run return 1
        {
            ServerCommandSource kfcSrc128 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.725), (source.getPosition().y + 0.0), (source.getPosition().z + -0.725)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_wither_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc128.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.45, 3.15, 1.45)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.525 ~-0.025 ~-0.525 if entity @n[type=marker,tag=hitscan-marker,dx=1.05,dy=1.725,dz=1.05] run return 1
        {
            ServerCommandSource kfcSrc129 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.525), (source.getPosition().y + -0.025), (source.getPosition().z + -0.525)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_baby"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc129.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.05, 1.725, 1.05)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.705 ~0 ~-0.705 if entity @n[type=marker,tag=hitscan-marker,dx=1.41,dy=2.895,dz=1.41] run return 1
        {
            ServerCommandSource kfcSrc130 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.705), (source.getPosition().y + 0.0), (source.getPosition().z + -0.705)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_1"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc130.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.41, 2.895, 1.41)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.825 ~0 ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=1.65,dy=3.45,dz=1.65] run return 1
        {
            ServerCommandSource kfcSrc131 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.825), (source.getPosition().y + 0.0), (source.getPosition().z + -0.825)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_5"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc131.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.65, 3.45, 1.65)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-0.975 ~0 ~-0.975 if entity @n[type=marker,tag=hitscan-marker,dx=1.95,dy=4.05,dz=1.95] run return 1
        {
            ServerCommandSource kfcSrc132 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.975), (source.getPosition().y + 0.0), (source.getPosition().z + -0.975)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_8"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc132.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.95, 4.05, 1.95)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~1.15 ~0 ~-1.15 if entity @n[type=marker,tag=hitscan-marker,dx=2.3,dy=4.6,dz=2.3] run return 1
        {
            ServerCommandSource kfcSrc133 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + 1.15), (source.getPosition().y + 0.0), (source.getPosition().z + -1.15)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_2"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc133.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.3, 4.6, 2.3)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-1.025 ~ ~-1.025 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=1.15,dz=1.15] run return 1
        {
            ServerCommandSource kfcSrc134 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.025), source.getPosition().y, (source.getPosition().z + -1.025)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zoglin"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc134.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.15, 1.15, 1.15)) {
                    return 1;
                }
            }
        }

        // return 0
        return 0;
    }

    public static void _m_7d0b1719_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_execute(ServerCommandSource source) {
        _m_7d0b1719_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source);
    }

    public static int _m_7d0b1719_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=hitscan-marker,dx=1.6,dy=2.77,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc135 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_breeze"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc135.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.6, 2.77, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.85 ~-0.5 ~-0.85 if entity @n[type=marker,tag=hitscan-marker,dx=1.7,dy=1.5,dz=1.7] run return 1
        {
            ServerCommandSource kfcSrc136 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.85), (source.getPosition().y + -0.5), (source.getPosition().z + -0.85)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_cave_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc136.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.7, 1.5, 1.7)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=hitscan-marker,dx=1.6,dy=2.7,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc137 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_creeper"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc137.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.6, 2.7, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=hitscan-marker,dx=1.6,dy=2.95,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc138 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_drowned"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc138.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.6, 2.95, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=hitscan-marker,dx=1.6,dy=3.9,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc139 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_enderman"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc139.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.6, 3.9, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.7 ~-0.7 ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=1.4,dy=1.3,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc140 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.7), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_endermite"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc140.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.4, 1.3, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.95 ~-0.5 ~-0.95 if entity @n[type=marker,tag=hitscan-marker,dx=1.9,dy=1.5,dz=1.9] run return 1
        {
            ServerCommandSource kfcSrc141 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.95), (source.getPosition().y + -0.5), (source.getPosition().z + -0.95)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_phantom"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc141.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.9, 1.5, 1.9)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.7 ~-0.5 ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=1.4,dy=1.5,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc142 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.5), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_rabbit"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc142.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.4, 1.5, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-1.475 ~0 ~-1.475 if entity @n[type=marker,tag=hitscan-marker,dx=2.95,dy=3.2,dz=2.95] run return 1
        {
            ServerCommandSource kfcSrc143 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.475), (source.getPosition().y + 0.0), (source.getPosition().z + -1.475)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_ravager"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc143.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.95, 3.2, 2.95)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=hitscan-marker,dx=1.6,dy=2.99,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc144 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc144.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.6, 2.99, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-1.19824 ~0 ~-1.19824 if entity @n[type=marker,tag=hitscan-marker,dx=2.39648,dy=2.6,dz=2.39648] run return 1
        {
            ServerCommandSource kfcSrc145 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.19824), (source.getPosition().y + 0.0), (source.getPosition().z + -1.19824)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton_horse"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc145.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.39648, 2.6, 2.39648)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-1.2 ~-0.1 ~-1.2 if entity @n[type=marker,tag=hitscan-marker,dx=2.4,dy=1.9,dz=2.4] run return 1
        {
            ServerCommandSource kfcSrc146 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.2), (source.getPosition().y + -0.1), (source.getPosition().z + -1.2)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc146.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.4, 1.9, 2.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.7 ~-0.2 ~-0.7 if entity @n[type=marker,tag=hitscan-marker,dx=1.4,dy=1.8,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc147 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.2), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_vex"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc147.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.4, 1.8, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.95 ~0 ~-0.95 if entity @n[type=marker,tag=hitscan-marker,dx=1.9,dy=3.9,dz=1.9] run return 1
        {
            ServerCommandSource kfcSrc148 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.95), (source.getPosition().y + 0.0), (source.getPosition().z + -0.95)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_warden"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc148.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.9, 3.9, 1.9)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.85 ~0 ~-0.85 if entity @n[type=marker,tag=hitscan-marker,dx=1.7,dy=3.4,dz=1.7] run return 1
        {
            ServerCommandSource kfcSrc149 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.85), (source.getPosition().y + 0.0), (source.getPosition().z + -0.85)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_wither_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc149.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.7, 3.4, 1.7)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.65 ~-0.025 ~-0.65 if entity @n[type=marker,tag=hitscan-marker,dx=1.3,dy=1.975,dz=1.3] run return 1
        {
            ServerCommandSource kfcSrc150 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.65), (source.getPosition().y + -0.025), (source.getPosition().z + -0.65)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_baby"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc150.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.3, 1.975, 1.3)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.83 ~0 ~-0.83 if entity @n[type=marker,tag=hitscan-marker,dx=1.66,dy=3.145,dz=1.66] run return 1
        {
            ServerCommandSource kfcSrc151 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.83), (source.getPosition().y + 0.0), (source.getPosition().z + -0.83)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_1"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc151.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.66, 3.145, 1.66)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.925 ~0 ~-0.925 if entity @n[type=marker,tag=hitscan-marker,dx=1.85,dy=4.025,dz=1.85] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc152 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.925), (source.getPosition().y + 0.0), (source.getPosition().z + -0.925)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_5"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc152.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.85, 4.025, 1.85)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc152) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-1.025 ~0 ~-1.025 if entity @n[type=marker,tag=hitscan-marker,dx=2.3,dy=4.6,dz=2.3] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc153 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.025), (source.getPosition().y + 0.0), (source.getPosition().z + -1.025)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_8"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc153.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.3, 4.6, 2.3)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc153) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-1.1 ~0 ~-1.1 if entity @n[type=marker,tag=hitscan-marker,dx=2.4,dy=5.15,dz=2.4] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc154 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.1), (source.getPosition().y + 0.0), (source.getPosition().z + -1.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_2"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc154.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 2.4, 5.15, 2.4)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc154) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-1.2 ~ ~-1.2 if entity @n[type=marker,tag=hitscan-marker,dx=1.4,dy=1.4,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc155 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.2), source.getPosition().y, (source.getPosition().z + -1.2)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zoglin"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc155.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"hitscan-marker"}, new String[0], 1.4, 1.4, 1.4)) {
                    return 1;
                }
            }
        }

        // return 0
        return 0;
    }

    public static void _m_f6f2a84c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_macr_execute(ServerCommandSource source) {
        _m_f6f2a84c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_macr_executeReturn(source);
    }

    public static int _m_f6f2a84c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_macr_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute unless score @s hitscan-marker.area matches 1.. if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-0 run return 1
        if (!(KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", 1, Integer.MAX_VALUE))) {
            if ((tdpack.buckets.Bucket7._m_03ddee11_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source) != 0)) {
                return 1;
            }
        }

        // execute if score @s hitscan-marker.area matches 1 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-1 run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", 1, 1)) {
            if ((tdpack.buckets.Bucket7._m_91dff13c_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source) != 0)) {
                return 1;
            }
        }

        // execute if score @s hitscan-marker.area matches 2 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-2 run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", 2, 2)) {
            if ((tdpack.buckets.Bucket8._m_7f8c2e6e_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source) != 0)) {
                return 1;
            }
        }

        // execute if score @s hitscan-marker.area matches 3 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-2 run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", 3, 3)) {
            if ((tdpack.buckets.Bucket8._m_7f8c2e6e_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source) != 0)) {
                return 1;
            }
        }

        // execute if score @s hitscan-marker.area matches 4 if function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox-area-2 run return 1
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", 4, 4)) {
            if ((tdpack.buckets.Bucket8._m_7f8c2e6e_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_area_executeReturn(source) != 0)) {
                return 1;
            }
        }

        // return 0
        return 0;
    }

    public static void _m_8aa33662_tower_attack_fire_hitscan_hit_marker_move_hit_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_8aa33662_tower_attack_fire_hitscan_hit_marker_move_hit_executeReturn(source, macroArgs);
    }

    public static int _m_8aa33662_tower_attack_fire_hitscan_hit_marker_move_hit_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set #temp game.return 42
        KfcGen.setScore(sb, "#temp", "game.return", 42);

        // execute store result score #damage enemy.hp run scoreboard players get @s bullet.attack
        KfcGen.setScore(sb, "#damage", "enemy.hp", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attack"));

        // execute store result score #temp enemy.state.stun run scoreboard players get @s bullet.attribute.stun
        KfcGen.setScore(sb, "#temp", "enemy.state.stun", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.stun"));

        // execute store result score #temp enemy.state.freeze run scoreboard players get @s bullet.attribute.freeze
        KfcGen.setScore(sb, "#temp", "enemy.state.freeze", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.freeze"));

        // execute store result score #temp enemy.state.poison run scoreboard players get @s bullet.attribute.poison
        KfcGen.setScore(sb, "#temp", "enemy.state.poison", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.poison"));

        // execute store result score #temp enemy.state.flame run scoreboard players get @s bullet.attribute.flame
        KfcGen.setScore(sb, "#temp", "enemy.state.flame", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.flame"));

        // execute store result score #temp enemy.state.bleed run scoreboard players get @s bullet.attribute.bleed
        KfcGen.setScore(sb, "#temp", "enemy.state.bleed", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.bleed"));

        // execute store result score #temp enemy.state.weakness run scoreboard players get @s bullet.attribute.weakness
        KfcGen.setScore(sb, "#temp", "enemy.state.weakness", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.weakness"));

        // $execute unless entity @n[tag=hitscan-area-effect] as @n[tag=target_$(number)] at @s run function enemy:hit/main
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"hitscan-area-effect"}, new String[0], -1, -1))) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number")}, new String[0], -1, -1, _ee -> (true));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc157 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                // -> enemy:hit/main
                tdpack.buckets.Bucket5._m_8b8e0228_enemy_hit_main_execute(kfcSrc157);
            } }
        }

        // execute as @e[tag=hitscan-area-effect] at @s run function enemy:hit/main
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("hitscan-area-effect"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc158 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:hit/main
            tdpack.buckets.Bucket5._m_8b8e0228_enemy_hit_main_execute(kfcSrc158);
        }

        // execute as @e[tag=hitscan-area-effect] run tag @s remove hitscan-area-effect
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("hitscan-area-effect"))) continue;
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("hitscan-area-effect");
        }

        // kill @e[tag=hitscan-marker]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"hitscan-marker"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // return 1
        return 1;
    }

    public static void _m_945db37f_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute_execute(ServerCommandSource source) {
        _m_945db37f_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute_executeReturn(source);
    }

    public static int _m_945db37f_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute if score @s hitscan-marker.area matches 1.. as @e[tag=enemy.target,distance=..5] at @s positioned ~-0.5 ~ ~-0.5 if entity @n[tag=hitscan-marker,dx=0,dy=1,dz=0] run tag @s add hitscan-area-effect
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", 1, Integer.MAX_VALUE)) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, 5))) continue;
                ServerCommandSource es = source.withEntity(e);
                ServerCommandSource kfcSrc159 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                ServerCommandSource kfcSrc160 = kfcSrc159.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc159.getPosition().x + -0.5), kfcSrc159.getPosition().y, (kfcSrc159.getPosition().z + -0.5)));
                if (!(KfcGen.anyEntityInBox(ctx, kfcSrc160.getPosition(), null, new String[]{"hitscan-marker"}, new String[0], 0, 1, 0))) continue;
                if (e != null) e.addCommandTag("hitscan-area-effect");
            }
        }

        // execute if score @s bullet.ability.chain matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox with storage tower temp
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.ability.chain", 1, Integer.MAX_VALUE)) {
            // -> tower:attack/fire/hitscan/hit-marker/move/detect-hitbox
            tdpack.buckets.Bucket7._m_ff7b6ff4_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute if score #temp game.return matches 41 run function tower:attack/fire/hitscan/hit-marker/move/hitscan-attribute-chain with storage tower temp
        if (KfcGen.scoreMatches(sb, "#temp", "game.return", 41, 41)) {
            // -> tower:attack/fire/hitscan/hit-marker/move/hitscan-attribute-chain
            tdpack.buckets.Bucket8._m_05e6fc48_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute__execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }
        return 0;
    }

    public static void _m_05e6fc48_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute__execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_05e6fc48_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute__executeReturn(source, macroArgs);
    }

    public static int _m_05e6fc48_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute__executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute store result score #damage enemy.hp run scoreboard players get @s bullet.attack
        KfcGen.setScore(sb, "#damage", "enemy.hp", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attack"));

        // execute store result score #temp enemy.state.stun run scoreboard players get @s bullet.attribute.stun
        KfcGen.setScore(sb, "#temp", "enemy.state.stun", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.stun"));

        // execute store result score #temp enemy.state.freeze run scoreboard players get @s bullet.attribute.freeze
        KfcGen.setScore(sb, "#temp", "enemy.state.freeze", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.freeze"));

        // execute store result score #temp enemy.state.poison run scoreboard players get @s bullet.attribute.poison
        KfcGen.setScore(sb, "#temp", "enemy.state.poison", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.poison"));

        // execute store result score #temp enemy.state.flame run scoreboard players get @s bullet.attribute.flame
        KfcGen.setScore(sb, "#temp", "enemy.state.flame", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.flame"));

        // execute store result score #temp enemy.state.bleed run scoreboard players get @s bullet.attribute.bleed
        KfcGen.setScore(sb, "#temp", "enemy.state.bleed", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.bleed"));

        // execute store result score #temp enemy.state.weakness run scoreboard players get @s bullet.attribute.weakness
        KfcGen.setScore(sb, "#temp", "enemy.state.weakness", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.weakness"));

        // $tag @n[tag=target_$(number)] add hitscan-area-effect
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number")}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.addCommandTag("hitscan-area-effect"); }

        // $tag @n[tag=target_$(number)] remove target_$(number)
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number")}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); }

        // $tag @n[tag=enemy.target,tag=!hitscan-area-effect] add target_$(number)
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"enemy.target"}, new String[]{"hitscan-area-effect"}, -1, -1, _ee -> (true)); if (_t != null) _t.addCommandTag("target_" + macroArgs.get("number")); }

        // $rotate @s facing entity @n[tag=target_$(number)] eyes
        { net.minecraft.entity.Entity _rt = executor, _fe = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number")}, new String[0], -1, -1, _ee -> (true)); if (_rt != null && _fe != null) KfcGen.rotateToFaceEntity(_rt, _fe, true); }

        // scoreboard players add @s bullet.life 3
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "bullet.life", 3);

        // scoreboard players remove @s bullet.ability.chain 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "bullet.ability.chain", -(1));

        // scoreboard players set #temp game.return 0
        KfcGen.setScore(sb, "#temp", "game.return", 0);

        // execute if score @s bullet.ability.chain matches 0 as @e[tag=hitscan-area-effect] run function enemy:hit/main
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.ability.chain", 0, 0)) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = e; if (!(en.getCommandTags().contains("hitscan-area-effect"))) continue;
                ServerCommandSource es = source.withEntity(e);
                // -> enemy:hit/main
                tdpack.buckets.Bucket5._m_8b8e0228_enemy_hit_main_execute(es);
            }
        }

        // execute if score @s bullet.ability.chain matches 0 as @e[tag=hitscan-area-effect] run tag @s remove hitscan-area-effect
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.ability.chain", 0, 0)) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = e; if (!(en.getCommandTags().contains("hitscan-area-effect"))) continue;
                ServerCommandSource es = source.withEntity(e);
                if (e != null) e.removeCommandTag("hitscan-area-effect");
            }
        }

        // execute if score @s bullet.ability.chain matches 0 run kill @e[tag=hitscan-marker]
        if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.ability.chain", 0, 0)) {
            for (net.minecraft.entity.Entity _k : KfcGen.allEntitiesAnyType(ctx, source.getPosition(), new String[]{"hitscan-marker"}, new String[0], -1, -1)) {
                KfcGen.killEntity(_k);
            }
        }
        return 0;
    }

    public static void _m_6ae89a4e_tower_attack_fire_hitscan_hit_marker_move_main_execute(ServerCommandSource source) {
        _m_6ae89a4e_tower_attack_fire_hitscan_hit_marker_move_main_executeReturn(source);
    }

    public static int _m_6ae89a4e_tower_attack_fire_hitscan_hit_marker_move_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result storage tower temp.number int 1 run scoreboard players get @n[tag=tower.data] tower.number
        KfcGen.storagePutNumber(server, "tower", "temp.number", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "tower.number"), "int");

        // execute at @s run function tower:attack/fire/hitscan/hit-marker/move/ray with storage tower temp
        {
            ServerCommandSource kfcSrc161 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> tower:attack/fire/hitscan/hit-marker/move/ray
            tdpack.buckets.Bucket8._m_a88d7255_tower_attack_fire_hitscan_hit_marker_move_ray_execute(kfcSrc161);
        }
        return 0;
    }

    public static void _m_a88d7255_tower_attack_fire_hitscan_hit_marker_move_ray_execute(ServerCommandSource source) {
        _m_a88d7255_tower_attack_fire_hitscan_hit_marker_move_ray_executeReturn(source);
    }

    public static int _m_a88d7255_tower_attack_fire_hitscan_hit_marker_move_ray_executeReturn(ServerCommandSource source) {
        
        // 자기재귀 → 브릿지(스택오버플로 방지) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "attack/fire/hitscan/hit-marker/move/ray"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // function tower:attack/fire/hitscan/hit-marker/animation/main with storage tower temp
                // // -> tower:attack/fire/hitscan/hit-marker/animation/main
                // tdpack.buckets.Bucket7._m_8689bbb7_tower_attack_fire_hitscan_hit_marker_animation_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
                // 
                // // function tower:attack/fire/hitscan/hit-marker/move/hitscan-attribute with storage tower temp
                // // -> tower:attack/fire/hitscan/hit-marker/move/hitscan-attribute
                // tdpack.buckets.Bucket8._m_945db37f_tower_attack_fire_hitscan_hit_marker_move_hitscan_attribute_execute(source);
                // 
                // // execute unless score @s hitscan-marker.area matches 1.. unless score @s bullet.ability.chain matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox with storage tower temp
                // if (!(KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", 1, Integer.MAX_VALUE))) {
                //     if (!(KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.ability.chain", 1, Integer.MAX_VALUE))) {
                //         // -> tower:attack/fire/hitscan/hit-marker/move/detect-hitbox
                //         tdpack.buckets.Bucket7._m_ff7b6ff4_tower_attack_fire_hitscan_hit_marker_move_detect_hitbox_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
                //     }
                // }
                // 
                // // execute if score #temp game.return matches 41 run function tower:attack/fire/hitscan/hit-marker/move/hit with storage tower temp
                // if (KfcGen.scoreMatches(sb, "#temp", "game.return", 41, 41)) {
                //     // -> tower:attack/fire/hitscan/hit-marker/move/hit
                //     tdpack.buckets.Bucket8._m_8aa33662_tower_attack_fire_hitscan_hit_marker_move_hit_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
                // }
                // 
                // // execute if score #temp game.return matches 42 run return 0
                // if (KfcGen.scoreMatches(sb, "#temp", "game.return", 42, 42)) {
                //     return 0;
                // }
                // 
                // // tp @s ^ ^ ^0.25
                // {
                //     net.minecraft.util.math.Vec3d _tpPos = KfcGen.localOffset(source.getPosition(), source.getRotation(), 0.0, 0.0, 0.25);
                // if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
                // }
                // 
                // // scoreboard players remove @s bullet.life 1
                // if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "bullet.life", -(1));
                // 
                // // execute if score @s[scores={bullet.life=..0}] hitscan-marker.area matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/hit with storage tower temp
                // if (KfcGen.entityScoreMatches(sb, executor, "hitscan-marker.area", 1, Integer.MAX_VALUE, false)) {
                //     // -> tower:attack/fire/hitscan/hit-marker/move/hit
                //     tdpack.buckets.Bucket8._m_8aa33662_tower_attack_fire_hitscan_hit_marker_move_hit_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
                // }
                // 
                // // execute if score @s[scores={bullet.life=..0}] bullet.ability.chain matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/hit with storage tower temp
                // if (KfcGen.entityScoreMatches(sb, executor, "bullet.ability.chain", 1, Integer.MAX_VALUE, false)) {
                //     // -> tower:attack/fire/hitscan/hit-marker/move/hit
                //     tdpack.buckets.Bucket8._m_8aa33662_tower_attack_fire_hitscan_hit_marker_move_hit_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));
                // }
                // 
                // // execute if score @s bullet.life matches ..0 run kill @s
                // if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.life", Integer.MIN_VALUE, 0)) {
                //     if (executor != null) KfcGen.killEntity(executor);
                // }
                // 
                // // execute if score @s bullet.life matches ..0 run return 0
                // if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.life", Integer.MIN_VALUE, 0)) {
                //     return 0;
                // }
                // 
                // // execute at @s run function tower:attack/fire/hitscan/hit-marker/move/ray with storage tower temp
                // {
                //     ServerCommandSource kfcSrc162 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
                //     // -> tower:attack/fire/hitscan/hit-marker/move/ray
                //     tdpack.buckets.Bucket8._m_a88d7255_tower_attack_fire_hitscan_hit_marker_move_ray_execute(kfcSrc162);
                // }
                return kfcBridgeRet;
    }

    public static void _m_19a7cd59_tower_attack_fire_hitscan_hit_marker_spawn_main_execute(ServerCommandSource source) {
        _m_19a7cd59_tower_attack_fire_hitscan_hit_marker_spawn_main_executeReturn(source);
    }

    public static int _m_19a7cd59_tower_attack_fire_hitscan_hit_marker_spawn_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~ ~ {Tags:[hitscan-marker]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[hitscan-marker]}"); }

        // execute as @n[tag=hitscan-marker] at @s unless score @s bullet.attack matches 0.. run function tower:attack/fire/hitscan/hit-marker/spawn/malloc
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"hitscan-marker"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc163 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if ((!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "bullet.attack", 0, Integer.MAX_VALUE)))) {
                // -> tower:attack/fire/hitscan/hit-marker/spawn/malloc
                tdpack.buckets.Bucket8._m_32e8d06a_tower_attack_fire_hitscan_hit_marker_spawn_malloc_execute(kfcSrc163);
            }
        } }

        // execute as @n[tag=hitscan-marker] at @s run function tower:attack/fire/hitscan/hit-marker/move/main with storage tower temp
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"hitscan-marker"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc164 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:attack/fire/hitscan/hit-marker/move/main
            tdpack.buckets.Bucket8._m_6ae89a4e_tower_attack_fire_hitscan_hit_marker_move_main_execute(kfcSrc164);
        } }
        return 0;
    }

    public static void _m_32e8d06a_tower_attack_fire_hitscan_hit_marker_spawn_malloc_execute(ServerCommandSource source) {
        _m_32e8d06a_tower_attack_fire_hitscan_hit_marker_spawn_malloc_executeReturn(source);
    }

    public static int _m_32e8d06a_tower_attack_fire_hitscan_hit_marker_spawn_malloc_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score @s bullet.attack run data get storage tower temp.attack
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attack", (int)(KfcGen.storageGetDouble(server, "tower", "temp.attack")));

        // execute store result score @s bullet.life run data get storage tower temp.Bullet.life 4.0
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.life", (int)((KfcGen.storageGetDouble(server, "tower", "temp.Bullet.life")) * 4.0));

        // execute store result score @s bullet.attribute.freeze run data get storage tower temp.Bullet.attribute.freeze
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.freeze", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.freeze")));

        // execute store result score @s bullet.attribute.poison run data get storage tower temp.Bullet.attribute.poison
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.poison", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.poison")));

        // execute store result score @s bullet.attribute.flame run data get storage tower temp.Bullet.attribute.flame
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.flame", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.flame")));

        // execute store result score @s bullet.attribute.bleed run data get storage tower temp.Bullet.attribute.bleed
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.bleed", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.bleed")));

        // execute store result score @s bullet.attribute.stun run data get storage tower temp.Bullet.attribute.stun
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.stun", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.stun")));

        // execute store result score @s bullet.ability.chain run data get storage tower temp.Bullet.attribute.chain
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.ability.chain", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.chain")));

        // scoreboard players add @s[scores={bullet.ability.chain=1..}] bullet.ability.chain 1
        if (executor != null && ((executor != null && KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), "bullet.ability.chain", 1, Integer.MAX_VALUE)))) KfcGen.addScore(sb, executor.getNameForScoreboard(), "bullet.ability.chain", 1);

        // data remove storage tower temp.hitscan_area
        KfcGen.storageRemovePath(server, "tower", "temp.hitscan_area");

        // data remove storage tower temp.hitscan_area_2
        KfcGen.storageRemovePath(server, "tower", "temp.hitscan_area_2");

        // scoreboard players set @s hitscan-marker.area 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "hitscan-marker.area", 0);

        // execute if data storage tower temp.Bullet.hitscan.area store result score @s hitscan-marker.area run data get storage tower temp.Bullet.hitscan.area
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.hitscan.area")) {
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.hitscan.area")));
        }

        // execute if data storage tower temp.Bullet.hitscan.area store result storage tower temp.hitscan_area float 1.0 run data get storage tower temp.Bullet.hitscan.area
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.hitscan.area")) {
            KfcGen.storagePutNumber(server, "tower", "temp.hitscan_area", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.hitscan.area")), "float");
        }

        // data modify entity @s Rotation set from entity @n[tag=tower.muzzle] Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "Rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "Rotation", _v); }
        return 0;
    }

    public static void _m_59cc4b80_tower_attack_fire_projectile_main_execute(ServerCommandSource source) {
        _m_59cc4b80_tower_attack_fire_projectile_main_executeReturn(source);
    }

    public static int _m_59cc4b80_tower_attack_fire_projectile_main_executeReturn(ServerCommandSource source) {
        net.minecraft.server.MinecraftServer server = source.getServer();
        // function tower:bullet/spawn/main with storage tower temp.Bullet
        // -> tower:bullet/spawn/main
        tdpack.buckets.Bucket8._m_65f56f79_tower_bullet_spawn_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp.Bullet"));
        return 0;
    }

    public static void _m_1d8a2ab3_tower_attack_fire_sword_main_execute(ServerCommandSource source) {
        _m_1d8a2ab3_tower_attack_fire_sword_main_executeReturn(source);
    }

    public static int _m_1d8a2ab3_tower_attack_fire_sword_main_executeReturn(ServerCommandSource source) {
        
        // function tower:attack/fire/sword/hit-marker/spawn/main with storage tower temp.Bullet
        // -> tower:attack/fire/sword/hit-marker/spawn/main
        tdpack.buckets.Bucket8._m_850fc601_tower_attack_fire_sword_hit_marker_spawn_main_execute(source);
        return 0;
    }

    public static void _m_d6ad2cf5_tower_attack_fire_sword_hit_marker_move_detect_hitbox_execute(ServerCommandSource source) {
        _m_d6ad2cf5_tower_attack_fire_sword_hit_marker_move_detect_hitbox_executeReturn(source);
    }

    public static int _m_d6ad2cf5_tower_attack_fire_sword_hit_marker_move_detect_hitbox_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute as @e[distance=..5,tag=enemy.data] at @s if function tower:attack/fire/sword/hit-marker/move/detect-hitbox-area-0 run tag @s add sword-hit-area
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("enemy.data") && KfcGen.inRange(source.getPosition(), en, -1, 5))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc22 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (!((tdpack.buckets.Bucket8._m_2e7fc669_tower_attack_fire_sword_hit_marker_move_detect_hitbox_area_0_executeReturn(kfcSrc22) != 0))) continue;
            if (e != null) e.addCommandTag("sword-hit-area");
        }
        return 0;
    }

    public static void _m_2e7fc669_tower_attack_fire_sword_hit_marker_move_detect_hitbox_area_0_execute(ServerCommandSource source) {
        _m_2e7fc669_tower_attack_fire_sword_hit_marker_move_detect_hitbox_area_0_executeReturn(source);
    }

    public static int _m_2e7fc669_tower_attack_fire_sword_hit_marker_move_detect_hitbox_area_0_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.77,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc1 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_breeze"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc1.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.6, 2.77, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.85 ~-0.5 ~-0.85 if entity @n[type=marker,tag=sword-hit-marker,dx=1.7,dy=1.5,dz=1.7] run return 1
        {
            ServerCommandSource kfcSrc2 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.85), (source.getPosition().y + -0.5), (source.getPosition().z + -0.85)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_cave_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc2.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.7, 1.5, 1.7)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.7,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc3 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_creeper"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc3.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.6, 2.7, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-1.3 ~0 ~-1.3 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.95,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc4 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.3), (source.getPosition().y + 0.0), (source.getPosition().z + -1.3)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_drowned"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc4.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.6, 2.95, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=3.9,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc5 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_enderman"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc5.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.6, 3.9, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.7 ~-0.7 ~-0.7 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.3,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc6 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.7), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_endermite"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc6.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.4, 1.3, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.95 ~-0.5 ~-0.95 if entity @n[type=marker,tag=sword-hit-marker,dx=1.9,dy=1.5,dz=1.9] run return 1
        {
            ServerCommandSource kfcSrc7 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.95), (source.getPosition().y + -0.5), (source.getPosition().z + -0.95)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_phantom"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc7.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.9, 1.5, 1.9)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.7 ~-0.5 ~-0.7 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.5,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc8 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.5), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_rabbit"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc8.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.4, 1.5, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-1.475 ~0 ~-1.475 if entity @n[type=marker,tag=sword-hit-marker,dx=2.95,dy=3.2,dz=2.95] run return 1
        {
            ServerCommandSource kfcSrc9 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.475), (source.getPosition().y + 0.0), (source.getPosition().z + -1.475)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_ravager"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc9.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 2.95, 3.2, 2.95)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.99,dz=1.6] run return 1
        {
            ServerCommandSource kfcSrc10 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.8), (source.getPosition().y + 0.0), (source.getPosition().z + -0.8)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc10.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.6, 2.99, 1.6)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-1.19824 ~0 ~-1.19824 if entity @n[type=marker,tag=sword-hit-marker,dx=2.39648,dy=2.6,dz=2.39648] run return 1
        {
            ServerCommandSource kfcSrc11 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.19824), (source.getPosition().y + 0.0), (source.getPosition().z + -1.19824)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton_horse"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc11.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 2.39648, 2.6, 2.39648)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-1.2 ~-0.1 ~-1.2 if entity @n[type=marker,tag=sword-hit-marker,dx=2.4,dy=1.9,dz=2.4] run return 1
        {
            ServerCommandSource kfcSrc12 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.2), (source.getPosition().y + -0.1), (source.getPosition().z + -1.2)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc12.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 2.4, 1.9, 2.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.7 ~-0.2 ~-0.7 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.8,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc13 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.2), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_vex"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc13.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.4, 1.8, 1.4)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.95 ~0 ~-0.95 if entity @n[type=marker,tag=sword-hit-marker,dx=1.9,dy=3.9,dz=1.9] run return 1
        {
            ServerCommandSource kfcSrc14 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.95), (source.getPosition().y + 0.0), (source.getPosition().z + -0.95)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_warden"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc14.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.9, 3.9, 1.9)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.85 ~0 ~-0.85 if entity @n[type=marker,tag=sword-hit-marker,dx=1.7,dy=3.4,dz=1.7] run return 1
        {
            ServerCommandSource kfcSrc15 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.85), (source.getPosition().y + 0.0), (source.getPosition().z + -0.85)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_wither_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc15.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.7, 3.4, 1.7)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.65 ~-0.025 ~-0.65 if entity @n[type=marker,tag=sword-hit-marker,dx=1.3,dy=1.975,dz=1.3] run return 1
        {
            ServerCommandSource kfcSrc16 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.65), (source.getPosition().y + -0.025), (source.getPosition().z + -0.65)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_baby"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc16.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.3, 1.975, 1.3)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.83 ~0 ~-0.83 if entity @n[type=marker,tag=sword-hit-marker,dx=1.66,dy=3.145,dz=1.66] run return 1
        {
            ServerCommandSource kfcSrc17 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.83), (source.getPosition().y + 0.0), (source.getPosition().z + -0.83)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_1"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc17.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.66, 3.145, 1.66)) {
                    return 1;
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.925 ~0 ~-0.925 if entity @n[type=marker,tag=sword-hit-marker,dx=1.85,dy=4.025,dz=1.85] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc18 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.925), (source.getPosition().y + 0.0), (source.getPosition().z + -0.925)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_5"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc18.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.85, 4.025, 1.85)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc18) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-1.025 ~0 ~-1.025 if entity @n[type=marker,tag=sword-hit-marker,dx=2.3,dy=4.6,dz=2.3] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc19 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.025), (source.getPosition().y + 0.0), (source.getPosition().z + -1.025)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_8"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc19.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 2.3, 4.6, 2.3)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc19) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-1.1 ~0 ~-1.1 if entity @n[type=marker,tag=sword-hit-marker,dx=2.4,dy=5.15,dz=2.4] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc20 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.1), (source.getPosition().y + 0.0), (source.getPosition().z + -1.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_2"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc20.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 2.4, 5.15, 2.4)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc20) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-1.2 ~ ~-1.2 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.4,dz=1.4] run return 1
        {
            ServerCommandSource kfcSrc21 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -1.2), source.getPosition().y, (source.getPosition().z + -1.2)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zoglin"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc21.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"sword-hit-marker"}, new String[0], 1.4, 1.4, 1.4)) {
                    return 1;
                }
            }
        }

        // return 0
        return 0;
    }

    public static void _m_e8e156b9_tower_attack_fire_sword_hit_marker_move_main_execute(ServerCommandSource source) {
        _m_e8e156b9_tower_attack_fire_sword_hit_marker_move_main_executeReturn(source);
    }

    public static int _m_e8e156b9_tower_attack_fire_sword_hit_marker_move_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result storage tower temp.number int 1 run scoreboard players get @n[tag=tower.data] tower.number
        KfcGen.storagePutNumber(server, "tower", "temp.number", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.data"}, new String[0], -1, -1, _ee -> (true)), "tower.number"), "int");

        // execute run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        // -> tower:attack/fire/sword/hit-marker/move/sweep
        tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp"));

        // execute rotated ~-60 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc23 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + -60.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc23, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~-50 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc24 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + -50.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc24, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~-40 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc25 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + -40.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc25, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~-30 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc26 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + -30.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc26, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~-20 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc27 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + -20.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc27, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~-10 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc28 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + -10.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc28, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~10 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc29 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 10.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc29, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~20 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc30 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 20.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc30, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~30 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc31 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 30.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc31, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~40 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc32 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 40.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc32, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~50 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc33 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 50.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc33, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // execute rotated ~60 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
        {
            ServerCommandSource kfcSrc34 = source.withRotation(new net.minecraft.util.math.Vec2f(source.getRotation().x, (source.getRotation().y + 60.0f)));
            // -> tower:attack/fire/sword/hit-marker/move/sweep
            tdpack.buckets.Bucket8._m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(kfcSrc34, KfcGen.storageMacroArgs(server, "tower", "temp"));
        }

        // scoreboard players set #temp game.return 42
        KfcGen.setScore(sb, "#temp", "game.return", 42);

        // execute store result score #damage enemy.hp run scoreboard players get @s bullet.attack
        KfcGen.setScore(sb, "#damage", "enemy.hp", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attack"));

        // execute store result score #temp enemy.state.stun run scoreboard players get @s bullet.attribute.stun
        KfcGen.setScore(sb, "#temp", "enemy.state.stun", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.stun"));

        // execute store result score #temp enemy.state.freeze run scoreboard players get @s bullet.attribute.freeze
        KfcGen.setScore(sb, "#temp", "enemy.state.freeze", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.freeze"));

        // execute store result score #temp enemy.state.poison run scoreboard players get @s bullet.attribute.poison
        KfcGen.setScore(sb, "#temp", "enemy.state.poison", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.poison"));

        // execute store result score #temp enemy.state.flame run scoreboard players get @s bullet.attribute.flame
        KfcGen.setScore(sb, "#temp", "enemy.state.flame", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.flame"));

        // execute store result score #temp enemy.state.bleed run scoreboard players get @s bullet.attribute.bleed
        KfcGen.setScore(sb, "#temp", "enemy.state.bleed", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.bleed"));

        // execute store result score #temp enemy.state.weakness run scoreboard players get @s bullet.attribute.weakness
        KfcGen.setScore(sb, "#temp", "enemy.state.weakness", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.weakness"));

        // execute as @e[tag=sword-hit-area] at @s run function enemy:hit/main
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("sword-hit-area"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc35 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:hit/main
            tdpack.buckets.Bucket5._m_8b8e0228_enemy_hit_main_execute(kfcSrc35);
        }

        // execute as @e[tag=sword-hit-area] run tag @s remove sword-hit-area
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("sword-hit-area"))) continue;
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("sword-hit-area");
        }

        // kill @s
        if (executor != null) KfcGen.killEntity(executor);
        return 0;
    }

    public static void _m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_executeReturn(source, macroArgs);
    }

    public static int _m_f724f823_tower_attack_fire_sword_hit_marker_move_sweep_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $tp @s ^ ^ ^$(range)
        {
            net.minecraft.util.math.Vec3d _tpPos = null; try { _tpPos = KfcGen.localOffset(source.getPosition(), source.getRotation(), 0.0, 0.0, Double.parseDouble(macroArgs.get("range"))); } catch (NumberFormatException _nfe) {}
        if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
        }

        // execute at @s run tp @s ^ ^ ^-1.0
        {
            ServerCommandSource kfcSrc36 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            {
                net.minecraft.util.math.Vec3d _tpPos = KfcGen.localOffset(kfcSrc36.getPosition(), kfcSrc36.getRotation(), 0.0, 0.0, -1.0);
            if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
            }
        }

        // execute at @s run particle sweep_attack ~ ~ ~
        {
            ServerCommandSource kfcSrc37 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc37.getPosition().x, kfcSrc37.getPosition().y, kfcSrc37.getPosition().z); KfcGen.spawnParticle(ctx.world, "sweep_attack", _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), false, null); }
        }

        // execute at @s run playsound minecraft:entity.player.attack.sweep neutral @a ~ ~ ~ 0.3 1
        {
            ServerCommandSource kfcSrc38 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {
                KfcGen.playSound(_ps, "minecraft:entity.player.attack.sweep", "neutral", new net.minecraft.util.math.Vec3d(kfcSrc38.getPosition().x, kfcSrc38.getPosition().y, kfcSrc38.getPosition().z).x, new net.minecraft.util.math.Vec3d(kfcSrc38.getPosition().x, kfcSrc38.getPosition().y, kfcSrc38.getPosition().z).y, new net.minecraft.util.math.Vec3d(kfcSrc38.getPosition().x, kfcSrc38.getPosition().y, kfcSrc38.getPosition().z).z, 0.3f, 1.0f);
            }
        }

        // execute at @s run function tower:attack/fire/sword/hit-marker/move/detect-hitbox with storage tower temp
        {
            ServerCommandSource kfcSrc39 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            // -> tower:attack/fire/sword/hit-marker/move/detect-hitbox
            tdpack.buckets.Bucket8._m_d6ad2cf5_tower_attack_fire_sword_hit_marker_move_detect_hitbox_execute(kfcSrc39);
        }

        // $execute at @s run tp @s ^ ^ ^-$(range)
        {
            ServerCommandSource kfcSrc40 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            {
                net.minecraft.util.math.Vec3d _tpPos = null; try { _tpPos = KfcGen.localOffset(kfcSrc40.getPosition(), kfcSrc40.getRotation(), 0.0, 0.0, (-Double.parseDouble(macroArgs.get("range")))); } catch (NumberFormatException _nfe) {}
            if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
            }
        }

        // execute at @s run tp @s ^ ^ ^1.0
        {
            ServerCommandSource kfcSrc41 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            {
                net.minecraft.util.math.Vec3d _tpPos = KfcGen.localOffset(kfcSrc41.getPosition(), kfcSrc41.getRotation(), 0.0, 0.0, 1.0);
            if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
            }
        }
        return 0;
    }

    public static void _m_850fc601_tower_attack_fire_sword_hit_marker_spawn_main_execute(ServerCommandSource source) {
        _m_850fc601_tower_attack_fire_sword_hit_marker_spawn_main_executeReturn(source);
    }

    public static int _m_850fc601_tower_attack_fire_sword_hit_marker_spawn_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon marker ~ ~ ~ {Tags:[sword-hit-marker]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[sword-hit-marker]}"); }

        // execute as @n[tag=sword-hit-marker] at @s unless score @s bullet.attack matches 0.. run function tower:attack/fire/sword/hit-marker/spawn/malloc
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"sword-hit-marker"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc42 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if ((!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "bullet.attack", 0, Integer.MAX_VALUE)))) {
                // -> tower:attack/fire/sword/hit-marker/spawn/malloc
                tdpack.buckets.Bucket8._m_e6fbf7c2_tower_attack_fire_sword_hit_marker_spawn_malloc_execute(kfcSrc42);
            }
        } }

        // execute as @n[tag=sword-hit-marker] at @s run function tower:attack/fire/sword/hit-marker/move/main with storage tower temp
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"sword-hit-marker"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc43 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:attack/fire/sword/hit-marker/move/main
            tdpack.buckets.Bucket8._m_e8e156b9_tower_attack_fire_sword_hit_marker_move_main_execute(kfcSrc43);
        } }
        return 0;
    }

    public static void _m_e6fbf7c2_tower_attack_fire_sword_hit_marker_spawn_malloc_execute(ServerCommandSource source) {
        _m_e6fbf7c2_tower_attack_fire_sword_hit_marker_spawn_malloc_executeReturn(source);
    }

    public static int _m_e6fbf7c2_tower_attack_fire_sword_hit_marker_spawn_malloc_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score @s bullet.attack run data get storage tower temp.attack
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attack", (int)(KfcGen.storageGetDouble(server, "tower", "temp.attack")));

        // execute store result score @s bullet.life run data get storage tower temp.Bullet.life 4.0
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.life", (int)((KfcGen.storageGetDouble(server, "tower", "temp.Bullet.life")) * 4.0));

        // execute store result score @s bullet.attribute.freeze run data get storage tower temp.Bullet.attribute.freeze
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.freeze", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.freeze")));

        // execute store result score @s bullet.attribute.poison run data get storage tower temp.Bullet.attribute.poison
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.poison", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.poison")));

        // execute store result score @s bullet.attribute.flame run data get storage tower temp.Bullet.attribute.flame
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.flame", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.flame")));

        // execute store result score @s bullet.attribute.bleed run data get storage tower temp.Bullet.attribute.bleed
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.bleed", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.bleed")));

        // execute store result score @s bullet.attribute.stun run data get storage tower temp.Bullet.attribute.stun
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attribute.stun", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.stun")));

        // data remove storage tower temp.hitscan_area
        KfcGen.storageRemovePath(server, "tower", "temp.hitscan_area");

        // data remove storage tower temp.hitscan_area_2
        KfcGen.storageRemovePath(server, "tower", "temp.hitscan_area_2");

        // scoreboard players set @s hitscan-marker.area 0
        if (executor != null) KfcGen.setScore(sb, executor.getNameForScoreboard(), "hitscan-marker.area", 0);

        // execute if data storage tower temp.Bullet.hitscan.area store result score @s hitscan-marker.area run data get storage tower temp.Bullet.hitscan.area
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.hitscan.area")) {
            KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "hitscan-marker.area", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.hitscan.area")));
        }

        // execute if data storage tower temp.Bullet.hitscan.area store result storage tower temp.hitscan_area float 1.0 run data get storage tower temp.Bullet.hitscan.area
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.hitscan.area")) {
            KfcGen.storagePutNumber(server, "tower", "temp.hitscan_area", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.hitscan.area")), "float");
        }

        // data modify entity @s Rotation set from entity @n[tag=tower.muzzle] Rotation
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"tower.muzzle"}, new String[0], -1, -1, _ee -> (true)), "Rotation"); if (_v != null) KfcGen.nbtSetEntity(executor, "Rotation", _v); }
        return 0;
    }

    public static void _m_259d798c_tower_attack_targeting_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_259d798c_tower_attack_targeting_main_executeReturn(source, macroArgs);
    }

    public static int _m_259d798c_tower_attack_targeting_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute positioned ~ -60 ~ if score @s tower.target_mode matches 1 run function tower:attack/targeting/get_target/front/main with storage tower temp
        {
            ServerCommandSource kfcSrc48 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.target_mode", 1, 1)) {
                // -> tower:attack/targeting/get_target/front/main
                tdpack.buckets.Bucket8._m_19981047_tower_attack_targeting_get_target_front_main_execute(kfcSrc48, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // execute positioned ~ -60 ~ if score @s tower.target_mode matches 2 run function tower:attack/targeting/get_target/back/main with storage tower temp
        {
            ServerCommandSource kfcSrc49 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.target_mode", 2, 2)) {
                // -> tower:attack/targeting/get_target/back/main
                tdpack.buckets.Bucket8._m_9064a087_tower_attack_targeting_get_target_back_main_execute(kfcSrc49, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // execute positioned ~ -60 ~ if score @s tower.target_mode matches 3 run function tower:attack/targeting/get_target/nearest/main with storage tower temp
        {
            ServerCommandSource kfcSrc50 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.target_mode", 3, 3)) {
                // -> tower:attack/targeting/get_target/nearest/main
                tdpack.buckets.Bucket8._m_0589f87e_tower_attack_targeting_get_target_nearest_main_execute(kfcSrc50, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // execute positioned ~ -60 ~ if score @s tower.target_mode matches 4 run function tower:attack/targeting/get_target/farthest/main with storage tower temp
        {
            ServerCommandSource kfcSrc51 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.target_mode", 4, 4)) {
                // -> tower:attack/targeting/get_target/farthest/main
                tdpack.buckets.Bucket8._m_96abe4c7_tower_attack_targeting_get_target_farthest_main_execute(kfcSrc51, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // execute positioned ~ -60 ~ if score @s tower.target_mode matches 5 run function tower:attack/targeting/get_target/lowest_health/main with storage tower temp
        {
            ServerCommandSource kfcSrc52 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.target_mode", 5, 5)) {
                // -> tower:attack/targeting/get_target/lowest_health/main
                tdpack.buckets.Bucket8._m_3a42a6b1_tower_attack_targeting_get_target_lowest_health_main_execute(kfcSrc52, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // execute positioned ~ -60 ~ if score @s tower.target_mode matches 6 run function tower:attack/targeting/get_target/highest_health/main with storage tower temp
        {
            ServerCommandSource kfcSrc53 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.target_mode", 6, 6)) {
                // -> tower:attack/targeting/get_target/highest_health/main
                tdpack.buckets.Bucket8._m_2bb25c5f_tower_attack_targeting_get_target_highest_health_main_execute(kfcSrc53, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // execute positioned ~ -60 ~ unless score @s tower.target_mode matches 1..6 run function tower:attack/targeting/get_target/front/main with storage tower temp
        {
            ServerCommandSource kfcSrc54 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            if (!(KfcGen.scoreMatches(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.target_mode", 1, 6))) {
                // -> tower:attack/targeting/get_target/front/main
                tdpack.buckets.Bucket8._m_19981047_tower_attack_targeting_get_target_front_main_execute(kfcSrc54, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // $execute positioned ~ -60 ~ if entity @e[tag=enemy.attribute.taunt,distance=..$(range),type=#minecraft:target] run function tower:attack/targeting/taunt with storage tower temp
        {
            ServerCommandSource kfcSrc55 = source.withPosition(new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z));
            boolean _mcond56 = false; try { _mcond56 = ((KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.BLOCK_DISPLAY, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.BOGGED, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.BREEZE, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.CAVE_SPIDER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.CREEPER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.DROWNED, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ENDERMAN, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ENDERMITE, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.EVOKER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.HUSK, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ILLUSIONER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ITEM_DISPLAY, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.PHANTOM, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.PILLAGER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.RABBIT, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.RAVAGER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.SILVERFISH, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.SKELETON, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.SKELETON_HORSE, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.SPIDER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.STRAY, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.VEX, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.VINDICATOR, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.WARDEN, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.WITCH, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.WITHER_SKELETON, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ZOGLIN, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ZOMBIE, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ZOMBIE_HORSE, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.ZOMBIE_VILLAGER, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))) || KfcGen.anyEntity(ctx, kfcSrc55.getPosition(), EntityType.END_CRYSTAL, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))))); } catch (NumberFormatException _nfe) {}
            if (_mcond56) {
                // -> tower:attack/targeting/taunt
                tdpack.buckets.Bucket8._m_cb762273_tower_attack_targeting_taunt_execute(kfcSrc55, KfcGen.storageMacroArgs(server, "tower", "temp"));
            }
        }

        // execute on vehicle on passengers if entity @s[tag=tower.head] run function tower:attack/targeting/rotate with storage tower temp
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if ((_onEnt2 != null && _onEnt2.getCommandTags().contains("tower.head"))) {
                    // -> tower:attack/targeting/rotate
                    tdpack.buckets.Bucket8._m_2d195c2d_tower_attack_targeting_rotate_execute(_on2, KfcGen.storageMacroArgs(server, "tower", "temp"));
                }
            }
          } }

        // scoreboard players remove @s tower.attack_countdown 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "tower.attack_countdown", -(1));

        // scoreboard players operation #temp tower.attack_countdown = @s tower.attack_countdown
        if (executor != null) KfcGen.opScore(sb, "#temp", "tower.attack_countdown", "=", executor.getNameForScoreboard(), "tower.attack_countdown");

        // execute on vehicle on passengers run scoreboard players operation @s tower.animation = #temp tower.attack_countdown
        { ServerCommandSource _on1 = KfcGen.onVehicle(source);
          if (_on1 != null) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            for (ServerCommandSource _on2 : KfcGen.onPassengers(_on1)) {
                net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                if (_onEnt2 != null) KfcGen.opScore(sb, _onEnt2.getNameForScoreboard(), "tower.animation", "=", "#temp", "tower.attack_countdown");
            }
          } }
        return 0;
    }

    public static void _m_2d195c2d_tower_attack_targeting_rotate_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_2d195c2d_tower_attack_targeting_rotate_executeReturn(source, macroArgs);
    }

    public static int _m_2d195c2d_tower_attack_targeting_rotate_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // $rotate @s facing entity @e[tag=target_$(number),tag=enemy.target,limit=1,type=#minecraft:target] eyes
        { net.minecraft.entity.Entity _rt = executor, _fe = KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY, EntityType.BOGGED, EntityType.BREEZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.DROWNED, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER, EntityType.HUSK, EntityType.ILLUSIONER, EntityType.ITEM_DISPLAY, EntityType.PHANTOM, EntityType.PILLAGER, EntityType.RABBIT, EntityType.RAVAGER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR, EntityType.WARDEN, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER, EntityType.END_CRYSTAL}, new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, -1); if (_rt != null && _fe != null) KfcGen.rotateToFaceEntity(_rt, _fe, true); }
        return 0;
    }

    public static void _m_cb762273_tower_attack_targeting_taunt_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_cb762273_tower_attack_targeting_taunt_executeReturn(source, macroArgs);
    }

    public static int _m_cb762273_tower_attack_targeting_taunt_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // $tag @n[tag=target_$(number),distance=..$(range),tag=enemy.target,limit=1,type=#minecraft:target] remove target_$(number)
        try { { net.minecraft.entity.Entity _t = KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY, EntityType.BOGGED, EntityType.BREEZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.DROWNED, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER, EntityType.HUSK, EntityType.ILLUSIONER, EntityType.ITEM_DISPLAY, EntityType.PHANTOM, EntityType.PILLAGER, EntityType.RABBIT, EntityType.RAVAGER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR, EntityType.WARDEN, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER, EntityType.END_CRYSTAL}, new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); } } catch (NumberFormatException _nfe) {}

        // $tag @n[tag=enemy.attribute.taunt,distance=..$(range),type=#minecraft:target] add target_$(number)
        try { { net.minecraft.entity.Entity _t = KfcGen.nearestEntity(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY, EntityType.BOGGED, EntityType.BREEZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.DROWNED, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER, EntityType.HUSK, EntityType.ILLUSIONER, EntityType.ITEM_DISPLAY, EntityType.PHANTOM, EntityType.PILLAGER, EntityType.RABBIT, EntityType.RAVAGER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR, EntityType.WARDEN, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER, EntityType.END_CRYSTAL}, new String[]{"enemy.attribute.taunt"}, new String[0], -1, Double.parseDouble(macroArgs.get("range"))); if (_t != null) _t.addCommandTag("target_" + macroArgs.get("number")); } } catch (NumberFormatException _nfe) {}
        return 0;
    }

    public static void _m_a08b0755_tower_attack_targeting_get_target_back_entity_execute(ServerCommandSource source) {
        _m_a08b0755_tower_attack_targeting_get_target_back_entity_executeReturn(source);
    }

    public static int _m_a08b0755_tower_attack_targeting_get_target_back_entity_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.progress < max enemy.progress run scoreboard players set #temp game.return 1
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress", "<", "max", "enemy.progress")) {
            KfcGen.setScore(sb, "#temp", "game.return", 1);
        }

        // execute if score @s enemy.progress < max enemy.progress run scoreboard players operation max enemy.progress = @s enemy.progress
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress", "<", "max", "enemy.progress")) {
            if (executor != null) KfcGen.opScore(sb, "max", "enemy.progress", "=", executor.getNameForScoreboard(), "enemy.progress");
        }
        return 0;
    }

    public static void _m_9064a087_tower_attack_targeting_get_target_back_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_9064a087_tower_attack_targeting_get_target_back_main_executeReturn(source, macroArgs);
    }

    public static int _m_9064a087_tower_attack_targeting_get_target_back_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set max enemy.progress 2147483647
        KfcGen.setScore(sb, "max", "enemy.progress", 2147483647);

        // scoreboard players set #temp game.return 0
        KfcGen.setScore(sb, "#temp", "game.return", 0);

        // $tag @e[tag=target_$(number),tag=enemy.target,limit=1] remove target_$(number)
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); }

        // $execute if data storage tower temp.Bullet.attribute.freeze as @e[distance=..$(range),tag=enemy.target] unless score @s enemy.state.freeze matches 1.. run function tower:attack/targeting/get_target/back/entity
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.freeze")) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
                ServerCommandSource es = source.withEntity(e);
                if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)))) continue;
                // -> tower:attack/targeting/get_target/back/entity
                tdpack.buckets.Bucket8._m_a08b0755_tower_attack_targeting_get_target_back_entity_execute(es);
            }
        }

        // $execute unless score #temp game.return matches 1 as @e[distance=..$(range),tag=enemy.target] run function tower:attack/targeting/get_target/back/entity
        if (!(KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1))) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
                ServerCommandSource es = source.withEntity(e);
                // -> tower:attack/targeting/get_target/back/entity
                tdpack.buckets.Bucket8._m_a08b0755_tower_attack_targeting_get_target_back_entity_execute(es);
            }
        }

        // $execute as @e[distance=..$(range),tag=enemy.target] if score @s enemy.progress = max enemy.progress run tag @s add target_$(number)
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "enemy.progress", "=", "max", "enemy.progress"))) continue;
            if (e != null) e.addCommandTag("target_" + macroArgs.get("number"));
        }
        return 0;
    }

    public static void _m_96abe4c7_tower_attack_targeting_get_target_farthest_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_96abe4c7_tower_attack_targeting_get_target_farthest_main_executeReturn(source, macroArgs);
    }

    public static int _m_96abe4c7_tower_attack_targeting_get_target_farthest_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 2/3줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "attack/targeting/get_target/farthest/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $tag @e[tag=target_$(number),tag=enemy.target,limit=1] remove target_$(number)
                // { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); }
                // 
                // // $execute if data storage tower temp.Bullet.attribute.freeze as @e[distance=..$(range),tag=enemy.target,sort=farthest,limit=1,scores={enemy.state.freeze=..0}] run tag @s add target_$(number)
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // $execute unless entity @e[tag=target_$(number)] as @e[distance=..$(range),tag=enemy.target,sort=farthest,limit=1] run tag @s add target_$(number)
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
        return 0;
    }

    public static void _m_05f6fb8b_tower_attack_targeting_get_target_front_entity_execute(ServerCommandSource source) {
        _m_05f6fb8b_tower_attack_targeting_get_target_front_entity_executeReturn(source);
    }

    public static int _m_05f6fb8b_tower_attack_targeting_get_target_front_entity_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.progress > max enemy.progress run scoreboard players set #temp game.return 1
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress", ">", "max", "enemy.progress")) {
            KfcGen.setScore(sb, "#temp", "game.return", 1);
        }

        // execute if score @s enemy.progress > max enemy.progress run scoreboard players operation max enemy.progress = @s enemy.progress
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.progress", ">", "max", "enemy.progress")) {
            if (executor != null) KfcGen.opScore(sb, "max", "enemy.progress", "=", executor.getNameForScoreboard(), "enemy.progress");
        }
        return 0;
    }

    public static void _m_19981047_tower_attack_targeting_get_target_front_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_19981047_tower_attack_targeting_get_target_front_main_executeReturn(source, macroArgs);
    }

    public static int _m_19981047_tower_attack_targeting_get_target_front_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set max enemy.progress -2147483648
        KfcGen.setScore(sb, "max", "enemy.progress", -2147483648);

        // scoreboard players set #temp game.return 0
        KfcGen.setScore(sb, "#temp", "game.return", 0);

        // $tag @e[tag=target_$(number),tag=enemy.target,limit=1] remove target_$(number)
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); }

        // $execute unless score #temp game.return matches 1 as @e[distance=..$(range),tag=enemy.target] run function tower:attack/targeting/get_target/front/entity
        if (!(KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1))) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
                ServerCommandSource es = source.withEntity(e);
                // -> tower:attack/targeting/get_target/front/entity
                tdpack.buckets.Bucket8._m_05f6fb8b_tower_attack_targeting_get_target_front_entity_execute(es);
            }
        }

        // $execute as @e[distance=..$(range),tag=enemy.target] if score @s enemy.progress = max enemy.progress run tag @s add target_$(number)
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "enemy.progress", "=", "max", "enemy.progress"))) continue;
            if (e != null) e.addCommandTag("target_" + macroArgs.get("number"));
        }
        return 0;
    }

    public static void _m_47f75d69_tower_attack_targeting_get_target_highest_health_entity_execute(ServerCommandSource source) {
        _m_47f75d69_tower_attack_targeting_get_target_highest_health_entity_executeReturn(source);
    }

    public static int _m_47f75d69_tower_attack_targeting_get_target_highest_health_entity_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.hp > max enemy.hp run scoreboard players set #temp game.return 1
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", ">", "max", "enemy.hp")) {
            KfcGen.setScore(sb, "#temp", "game.return", 1);
        }

        // execute if score @s enemy.hp > max enemy.hp run scoreboard players operation max enemy.hp = @s enemy.hp
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", ">", "max", "enemy.hp")) {
            if (executor != null) KfcGen.opScore(sb, "max", "enemy.hp", "=", executor.getNameForScoreboard(), "enemy.hp");
        }
        return 0;
    }

    public static void _m_2bb25c5f_tower_attack_targeting_get_target_highest_health_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_2bb25c5f_tower_attack_targeting_get_target_highest_health_main_executeReturn(source, macroArgs);
    }

    public static int _m_2bb25c5f_tower_attack_targeting_get_target_highest_health_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set max enemy.hp -2147483648
        KfcGen.setScore(sb, "max", "enemy.hp", -2147483648);

        // scoreboard players set #temp game.return 0
        KfcGen.setScore(sb, "#temp", "game.return", 0);

        // $tag @e[tag=target_$(number),tag=enemy.target,limit=1] remove target_$(number)
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); }

        // $execute if data storage tower temp.Bullet.attribute.freeze as @e[distance=..$(range),tag=enemy.target] unless score @s enemy.state.freeze matches 1.. run function tower:attack/targeting/get_target/highest_health/entity
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.freeze")) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
                ServerCommandSource es = source.withEntity(e);
                if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)))) continue;
                // -> tower:attack/targeting/get_target/highest_health/entity
                tdpack.buckets.Bucket8._m_47f75d69_tower_attack_targeting_get_target_highest_health_entity_execute(es);
            }
        }

        // $execute unless score #temp game.return matches 1 as @e[distance=..$(range),tag=enemy.target] run function tower:attack/targeting/get_target/highest_health/entity
        if (!(KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1))) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
                ServerCommandSource es = source.withEntity(e);
                // -> tower:attack/targeting/get_target/highest_health/entity
                tdpack.buckets.Bucket8._m_47f75d69_tower_attack_targeting_get_target_highest_health_entity_execute(es);
            }
        }

        // $execute as @e[distance=..$(range),tag=enemy.target] if score @s enemy.hp = max enemy.hp run tag @s add target_$(number)
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "enemy.hp", "=", "max", "enemy.hp"))) continue;
            if (e != null) e.addCommandTag("target_" + macroArgs.get("number"));
        }
        return 0;
    }

    public static void _m_43f400cd_tower_attack_targeting_get_target_lowest_health_entity_execute(ServerCommandSource source) {
        _m_43f400cd_tower_attack_targeting_get_target_lowest_health_entity_executeReturn(source);
    }

    public static int _m_43f400cd_tower_attack_targeting_get_target_lowest_health_entity_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute if score @s enemy.hp < max enemy.hp run scoreboard players set #temp game.return 1
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<", "max", "enemy.hp")) {
            KfcGen.setScore(sb, "#temp", "game.return", 1);
        }

        // execute if score @s enemy.hp < max enemy.hp run scoreboard players operation max enemy.hp = @s enemy.hp
        if (KfcGen.scoreCmp(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "enemy.hp", "<", "max", "enemy.hp")) {
            if (executor != null) KfcGen.opScore(sb, "max", "enemy.hp", "=", executor.getNameForScoreboard(), "enemy.hp");
        }
        return 0;
    }

    public static void _m_3a42a6b1_tower_attack_targeting_get_target_lowest_health_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_3a42a6b1_tower_attack_targeting_get_target_lowest_health_main_executeReturn(source, macroArgs);
    }

    public static int _m_3a42a6b1_tower_attack_targeting_get_target_lowest_health_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players set max enemy.hp 2147483647
        KfcGen.setScore(sb, "max", "enemy.hp", 2147483647);

        // scoreboard players set #temp game.return 0
        KfcGen.setScore(sb, "#temp", "game.return", 0);

        // $tag @e[tag=target_$(number),tag=enemy.target,limit=1] remove target_$(number)
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); }

        // $execute if data storage tower temp.Bullet.attribute.freeze as @e[distance=..$(range),tag=enemy.target] unless score @s enemy.state.freeze matches 1.. run function tower:attack/targeting/get_target/lowest_health/entity
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.freeze")) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
                ServerCommandSource es = source.withEntity(e);
                if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "enemy.state.freeze", 1, Integer.MAX_VALUE)))) continue;
                // -> tower:attack/targeting/get_target/lowest_health/entity
                tdpack.buckets.Bucket8._m_43f400cd_tower_attack_targeting_get_target_lowest_health_entity_execute(es);
            }
        }

        // $execute unless score #temp game.return matches 1 as @e[distance=..$(range),tag=enemy.target] run function tower:attack/targeting/get_target/lowest_health/entity
        if (!(KfcGen.scoreMatches(sb, "#temp", "game.return", 1, 1))) {
            for (Entity e : ctx.world.iterateEntities()) {
                if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
                ServerCommandSource es = source.withEntity(e);
                // -> tower:attack/targeting/get_target/lowest_health/entity
                tdpack.buckets.Bucket8._m_43f400cd_tower_attack_targeting_get_target_lowest_health_entity_execute(es);
            }
        }

        // $execute as @e[distance=..$(range),tag=enemy.target] if score @s enemy.hp = max enemy.hp run tag @s add target_$(number)
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "enemy.hp", "=", "max", "enemy.hp"))) continue;
            if (e != null) e.addCommandTag("target_" + macroArgs.get("number"));
        }
        return 0;
    }

    public static void _m_0589f87e_tower_attack_targeting_get_target_nearest_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_0589f87e_tower_attack_targeting_get_target_nearest_main_executeReturn(source, macroArgs);
    }

    public static int _m_0589f87e_tower_attack_targeting_get_target_nearest_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // $tag @e[tag=target_$(number),tag=enemy.target,limit=1] remove target_$(number)
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number"), "enemy.target"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("target_" + macroArgs.get("number")); }

        // $execute if data storage tower temp.Bullet.attribute.freeze as @e[distance=..$(range),tag=enemy.target,sort=nearest,limit=1,scores={enemy.state.freeze=..0}] run tag @s add target_$(number)
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.freeze")) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"enemy.target"}, new String[0], -1, Double.parseDouble(macroArgs.get("range")), _ee -> (KfcGen.scoreMatches(sb, _ee.getNameForScoreboard(), "enemy.state.freeze", Integer.MIN_VALUE, 0)));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                if (e != null) e.addCommandTag("target_" + macroArgs.get("number"));
            } }
        }

        // $execute unless entity @e[tag=target_$(number)] as @e[distance=..$(range),tag=enemy.target,sort=nearest,limit=1] run tag @s add target_$(number)
        if (!(KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"target_" + macroArgs.get("number")}, new String[0], -1, -1))) {
            { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"enemy.target"}, new String[0], -1, Double.parseDouble(macroArgs.get("range")), _ee -> (true));
              if (e != null) {
                ServerCommandSource es = source.withEntity(e);
                if (e != null) e.addCommandTag("target_" + macroArgs.get("number"));
            } }
        }
        return 0;
    }

    public static void _m_fe9c6bd9_tower_bullet_load_execute(ServerCommandSource source) {
        _m_fe9c6bd9_tower_bullet_load_executeReturn(source);
    }

    public static int _m_fe9c6bd9_tower_bullet_load_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard objectives add bullet.attack dummy
        KfcGen.ensureObjective(sb, "bullet.attack", "dummy");

        // scoreboard objectives add bullet.life dummy
        KfcGen.ensureObjective(sb, "bullet.life", "dummy");

        // scoreboard objectives add bullet.speed dummy
        KfcGen.ensureObjective(sb, "bullet.speed", "dummy");

        // scoreboard objectives add bullet.attribute.freeze dummy
        KfcGen.ensureObjective(sb, "bullet.attribute.freeze", "dummy");

        // scoreboard objectives add bullet.attribute.poison dummy
        KfcGen.ensureObjective(sb, "bullet.attribute.poison", "dummy");

        // scoreboard objectives add bullet.attribute.flame dummy
        KfcGen.ensureObjective(sb, "bullet.attribute.flame", "dummy");

        // scoreboard objectives add bullet.attribute.bleed dummy
        KfcGen.ensureObjective(sb, "bullet.attribute.bleed", "dummy");

        // scoreboard objectives add bullet.attribute.stun dummy
        KfcGen.ensureObjective(sb, "bullet.attribute.stun", "dummy");

        // scoreboard objectives add bullet.attribute.weakness dummy
        KfcGen.ensureObjective(sb, "bullet.attribute.weakness", "dummy");

        // scoreboard objectives add hitscan-marker.area dummy
        KfcGen.ensureObjective(sb, "hitscan-marker.area", "dummy");

        // scoreboard objectives add bullet.floor.time dummy
        KfcGen.ensureObjective(sb, "bullet.floor.time", "dummy");

        // scoreboard objectives add bullet.floor.range dummy
        KfcGen.ensureObjective(sb, "bullet.floor.range", "dummy");

        // scoreboard objectives add bullet.ability.chain dummy
        KfcGen.ensureObjective(sb, "bullet.ability.chain", "dummy");
        return 0;
    }

    public static void _m_1b810019_tower_bullet_main_execute(ServerCommandSource source) {
        _m_1b810019_tower_bullet_main_executeReturn(source);
    }

    public static int _m_1b810019_tower_bullet_main_executeReturn(ServerCommandSource source) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute as @e[tag=bullet.remove] at @s run kill @s
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("bullet.remove"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc58 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            if (e != null) KfcGen.killEntity(e);
        }

        // execute as @e[tag=bullet.core] at @s run function tower:bullet/move/main
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("bullet.core"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc59 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:bullet/move/main
            tdpack.buckets.Bucket8._m_ef16061b_tower_bullet_move_main_execute(kfcSrc59);
        }

        // execute as @e[tag=bullet.floor] at @s run function tower:bullet/floor/floor
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("bullet.floor"))) continue;
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc60 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> tower:bullet/floor/floor
            tdpack.buckets.Bucket8._m_faec22cd_tower_bullet_floor_floor_execute(kfcSrc60);
        }
        return 0;
    }

    public static void _m_dccc0fff_tower_bullet_remove_execute(ServerCommandSource source) {
        _m_dccc0fff_tower_bullet_remove_executeReturn(source);
    }

    public static int _m_dccc0fff_tower_bullet_remove_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result storage tower temp.Bullet.time int 1 run scoreboard players get @n[tag=bullet.data] bullet.floor.time
        KfcGen.storagePutNumber(server, "tower", "temp.Bullet.time", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.floor.time"), "int");

        // execute store result storage tower temp.Bullet.range int 1 run scoreboard players get @n[tag=bullet.data] bullet.floor.range
        KfcGen.storagePutNumber(server, "tower", "temp.Bullet.range", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.floor.range"), "int");

        // data modify storage tower temp.Bullet.attribute.floor set from entity @n[tag=bullet.data] data.Bullet.flooring
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "data.Bullet.flooring"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp.Bullet.attribute.floor", _v); }

        // execute if entity @n[tag=bullet.flooring] run function tower:bullet/spawn/floor/main with storage tower temp.Bullet
        if (KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"bullet.flooring"}, new String[0], -1, -1)) {
            // -> tower:bullet/spawn/floor/main
            tdpack.buckets.Bucket8._m_5fbeda1d_tower_bullet_spawn_floor_main_execute(source, KfcGen.storageMacroArgs(server, "tower", "temp.Bullet"));
        }

        // execute on passengers if entity @s[type=item_display] run tag @s add bullet.remove
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if ((_onEnt1 != null && _onEnt1.getType() == EntityType.ITEM_DISPLAY)) {
                if (_onEnt1 != null) _onEnt1.addCommandTag("bullet.remove");
            }
        }

        // execute on passengers unless entity @s[type=item_display] run kill @s
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (!((_onEnt1 != null && _onEnt1.getType() == EntityType.ITEM_DISPLAY))) {
                if (_onEnt1 != null) KfcGen.killEntity(_onEnt1);
            }
        }

        // kill @s
        if (executor != null) KfcGen.killEntity(executor);
        return 0;
    }

    public static void _m_faec22cd_tower_bullet_floor_floor_execute(ServerCommandSource source) {
        _m_faec22cd_tower_bullet_floor_floor_executeReturn(source);
    }

    public static int _m_faec22cd_tower_bullet_floor_floor_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // scoreboard players remove @s bullet.floor.time 1
        if (executor != null) KfcGen.addScore(sb, executor.getNameForScoreboard(), "bullet.floor.time", -(1));

        // scoreboard players set #7 enemy.attribute.timer 4
        KfcGen.setScore(sb, "#7", "enemy.attribute.timer", 4);

        // scoreboard players operation #temp enemy.attribute.timer = @s bullet.floor.time
        if (executor != null) KfcGen.opScore(sb, "#temp", "enemy.attribute.timer", "=", executor.getNameForScoreboard(), "bullet.floor.time");

        // scoreboard players operation #temp enemy.attribute.timer %= #7 enemy.attribute.timer
        KfcGen.opScore(sb, "#temp", "enemy.attribute.timer", "%=", "#7", "enemy.attribute.timer");

        // execute unless score #temp enemy.attribute.timer matches 0 run return 0
        if (!(KfcGen.scoreMatches(sb, "#temp", "enemy.attribute.timer", 0, 0))) {
            return 0;
        }

        // execute at @s[tag=floor-2] run function tower:bullet/floor/floor-on {range:2}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-2")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "2"));
        }

        // execute at @s[tag=floor-2.5] run function tower:bullet/floor/floor-on {range:2.5}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-2.5")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "2.5"));
        }

        // execute at @s[tag=floor-3] run function tower:bullet/floor/floor-on {range:3}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-3")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "3"));
        }

        // execute at @s[tag=floor-3.5] run function tower:bullet/floor/floor-on {range:3.5}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-3.5")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "3.5"));
        }

        // execute at @s[tag=floor-4] run function tower:bullet/floor/floor-on {range:4}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-4")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "4"));
        }

        // execute at @s[tag=floor-5] run function tower:bullet/floor/floor-on {range:5}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-5")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "5"));
        }

        // execute at @s[tag=floor-6] run function tower:bullet/floor/floor-on {range:6}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-6")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "6"));
        }

        // execute at @s[tag=floor-7] run function tower:bullet/floor/floor-on {range:7}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-7")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "7"));
        }

        // execute at @s[tag=floor-8] run function tower:bullet/floor/floor-on {range:8}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-8")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "8"));
        }

        // execute at @s[tag=floor-9] run function tower:bullet/floor/floor-on {range:9}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-9")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "9"));
        }

        // execute at @s[tag=floor-10] run function tower:bullet/floor/floor-on {range:10}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-10")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "10"));
        }

        // execute at @s[tag=floor-11] run function tower:bullet/floor/floor-on {range:11}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-11")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "11"));
        }

        // execute at @s[tag=floor-12] run function tower:bullet/floor/floor-on {range:12}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-12")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "12"));
        }

        // execute at @s[tag=floor-13] run function tower:bullet/floor/floor-on {range:13}
        if (executor != null && ((executor != null && executor.getCommandTags().contains("floor-13")))) {
            net.minecraft.server.command.ServerCommandSource _atSrc1 = source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw()));
            // -> tower:bullet/floor/floor-on
            tdpack.buckets.Bucket8._m_f8911f71_tower_bullet_floor_floor_on_execute(_atSrc1, KfcGen.macroArgs("range", "13"));
        }
        return 0;
    }

    public static void _m_f8911f71_tower_bullet_floor_floor_on_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_f8911f71_tower_bullet_floor_floor_on_executeReturn(source, macroArgs);
    }

    public static int _m_f8911f71_tower_bullet_floor_floor_on_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute store result score #damage enemy.hp run scoreboard players get @n[tag=bullet.data] bullet.attack
        KfcGen.setScore(sb, "#damage", "enemy.hp", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attack"));

        // execute store result score #temp enemy.state.stun run scoreboard players get @n[tag=bullet.data] bullet.attribute.stun
        KfcGen.setScore(sb, "#temp", "enemy.state.stun", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.stun"));

        // execute store result score #temp enemy.state.freeze run scoreboard players get @n[tag=bullet.data] bullet.attribute.freeze
        KfcGen.setScore(sb, "#temp", "enemy.state.freeze", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.freeze"));

        // execute store result score #temp enemy.state.poison run scoreboard players get @n[tag=bullet.data] bullet.attribute.poison
        KfcGen.setScore(sb, "#temp", "enemy.state.poison", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.poison"));

        // execute store result score #temp enemy.state.flame run scoreboard players get @n[tag=bullet.data] bullet.attribute.flame
        KfcGen.setScore(sb, "#temp", "enemy.state.flame", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.flame"));

        // execute store result score #temp enemy.state.bleed run scoreboard players get @n[tag=bullet.data] bullet.attribute.bleed
        KfcGen.setScore(sb, "#temp", "enemy.state.bleed", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.bleed"));

        // execute store result score #temp enemy.state.weakness run scoreboard players get @n[tag=bullet.data] bullet.attribute.weakness
        KfcGen.setScore(sb, "#temp", "enemy.state.weakness", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.weakness"));

        // $execute as @e[tag=enemy.target,distance=..$(range)] at @s run function enemy:hit/main
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = null; try { en = e; if (!(en.getCommandTags().contains("enemy.target") && KfcGen.inRange(source.getPosition(), en, -1, Double.parseDouble(macroArgs.get("range"))))) continue; } catch (NumberFormatException _nfe) {}
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc57 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:hit/main
            tdpack.buckets.Bucket5._m_8b8e0228_enemy_hit_main_execute(kfcSrc57);
        }

        // tp @s ~ ~ ~
        {
            net.minecraft.util.math.Vec3d _tpPos = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z);
        if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
        }

        // return 1
        return 1;
    }

    public static void _m_a287a7b3_tower_bullet_move_detect_hitbox_execute(ServerCommandSource source) {
        _m_a287a7b3_tower_bullet_move_detect_hitbox_executeReturn(source);
    }

    public static int _m_a287a7b3_tower_bullet_move_detect_hitbox_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.77,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.77,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc61 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc62 = kfcSrc61.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc61.getPosition().x + -0.4), kfcSrc61.getPosition().y, (kfcSrc61.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_breeze"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc61.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.77, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc62.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.77, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc62) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.35 ~ ~-0.35 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] positioned ~-0.3 ~-0.5 ~-0.3 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc63 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.35), source.getPosition().y, (source.getPosition().z + -0.35)));
            ServerCommandSource kfcSrc64 = kfcSrc63.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc63.getPosition().x + -0.3), (kfcSrc63.getPosition().y + -0.5), (kfcSrc63.getPosition().z + -0.3)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_cave_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc63.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc64.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc64) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.7,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.7,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc65 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc66 = kfcSrc65.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc65.getPosition().x + -0.4), kfcSrc65.getPosition().y, (kfcSrc65.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_creeper"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc65.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.7, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc66.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.7, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc66) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.95,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.95,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc67 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc68 = kfcSrc67.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc67.getPosition().x + -0.4), kfcSrc67.getPosition().y, (kfcSrc67.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_drowned"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc67.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.95, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc68.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.95, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc68) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.9,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.9,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc69 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc70 = kfcSrc69.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc69.getPosition().x + -0.4), kfcSrc69.getPosition().y, (kfcSrc69.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_enderman"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc69.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.9, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc70.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.9, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc70) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.2 ~ ~-0.2 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] positioned ~-0.6 ~-0.7 ~-0.6 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc71 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.2), source.getPosition().y, (source.getPosition().z + -0.2)));
            ServerCommandSource kfcSrc72 = kfcSrc71.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc71.getPosition().x + -0.6), (kfcSrc71.getPosition().y + -0.7), (kfcSrc71.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_endermite"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc71.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc72.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc72) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.45 ~ ~-0.45 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] positioned ~-0.1 ~-0.5 ~-0.1 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc73 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), source.getPosition().y, (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc74 = kfcSrc73.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc73.getPosition().x + -0.1), (kfcSrc73.getPosition().y + -0.5), (kfcSrc73.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_phantom"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc73.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc74.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc74) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.2 ~ ~-0.2 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] positioned ~-0.6 ~-0.5 ~-0.6 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc75 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.2), source.getPosition().y, (source.getPosition().z + -0.2)));
            ServerCommandSource kfcSrc76 = kfcSrc75.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc75.getPosition().x + -0.6), (kfcSrc75.getPosition().y + -0.5), (kfcSrc75.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_rabbit"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc75.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc76.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc76) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-0.975 ~ ~-0.975 if entity @n[type=marker,tag=bullet.data,dx=0.950,dy=1.2,dz=0.950] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc77 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.975), source.getPosition().y, (source.getPosition().z + -0.975)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_ravager"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc77.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0.950, 1.2, 0.950)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc77) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.99,dz=0] positioned ~-0.4 ~ ~-0.4 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0.99,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc78 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.3), source.getPosition().y, (source.getPosition().z + -0.3)));
            ServerCommandSource kfcSrc79 = kfcSrc78.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc78.getPosition().x + -0.4), kfcSrc78.getPosition().y, (kfcSrc78.getPosition().z + -0.4)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc78.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.99, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc79.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0.99, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc79) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-0.69824 ~ ~-0.69824 if entity @n[type=marker,tag=bullet.data,dx=0.19824,dy=0.6,dz=0.19824] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc80 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.69824), source.getPosition().y, (source.getPosition().z + -0.69824)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_skeleton_horse"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc80.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0.19824, 0.6, 0.19824)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc80) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-0.7 ~-0.1 ~-0.7 if entity @n[type=marker,tag=bullet.data,dx=0.4,dy=0,dz=0.4] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc81 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), (source.getPosition().y + -0.1), (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_spider"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc81.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0.4, 0, 0.4)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc81) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.2 ~ ~-0.2 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] positioned ~-0.6 ~-0.2 ~-0.6 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc82 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.2), source.getPosition().y, (source.getPosition().z + -0.2)));
            ServerCommandSource kfcSrc83 = kfcSrc82.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc82.getPosition().x + -0.6), (kfcSrc82.getPosition().y + -0.2), (kfcSrc82.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_vex"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc82.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc83.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc83) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.45 ~ ~-0.45 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.9,dz=0] positioned ~-0.1 ~ ~-0.1 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.9,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc84 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), source.getPosition().y, (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc85 = kfcSrc84.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc84.getPosition().x + -0.1), kfcSrc84.getPosition().y, (kfcSrc84.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_warden"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc84.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.9, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc85.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.9, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc85) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.35 ~ ~-0.35 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.4,dz=0] positioned ~-0.3 ~ ~-0.3 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.4,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc86 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.35), source.getPosition().y, (source.getPosition().z + -0.35)));
            ServerCommandSource kfcSrc87 = kfcSrc86.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc86.getPosition().x + -0.3), kfcSrc86.getPosition().y, (kfcSrc86.getPosition().z + -0.3)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_wither_skeleton"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc86.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.4, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc87.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.4, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc87) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.15 ~ ~-0.15 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] positioned ~-0.7 ~-0.025 ~-0.7 if entity @n[type=marker,tag=bullet.data,dx=0,dy=0,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc88 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.15), source.getPosition().y, (source.getPosition().z + -0.15)));
            ServerCommandSource kfcSrc89 = kfcSrc88.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc88.getPosition().x + -0.7), (kfcSrc88.getPosition().y + -0.025), (kfcSrc88.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_baby"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc88.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc89.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 0, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc89) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.33 ~ ~-0.33 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.145,dz=0] positioned ~-0.34 ~ ~-0.34 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.145,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc90 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.33), source.getPosition().y, (source.getPosition().z + -0.33)));
            ServerCommandSource kfcSrc91 = kfcSrc90.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc90.getPosition().x + -0.34), kfcSrc90.getPosition().y, (kfcSrc90.getPosition().z + -0.34)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_1"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc90.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.145, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc91.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.145, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc91) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.45 ~ ~-0.45 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.925,dz=0] positioned ~-0.1 ~ ~-0.1 if entity @n[type=marker,tag=bullet.data,dx=0,dy=1.925,dz=0] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc92 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.45), source.getPosition().y, (source.getPosition().z + -0.45)));
            ServerCommandSource kfcSrc93 = kfcSrc92.withPosition(new net.minecraft.util.math.Vec3d((kfcSrc92.getPosition().x + -0.1), kfcSrc92.getPosition().y, (kfcSrc92.getPosition().z + -0.1)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_5"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc92.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.925, 0)) {
                    if (KfcGen.anyEntityInBox(ctx, kfcSrc93.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0, 1.925, 0)) {
                        if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc93) != 0)) {
                            return 1;
                        }
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-0.54 ~ ~-0.54 if entity @n[type=marker,tag=bullet.data,dx=0.08,dy=3.6,dz=0.08] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc94 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.54), source.getPosition().y, (source.getPosition().z + -0.54)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_1_8"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc94.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0.08, 3.6, 0.08)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc94) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-0.6 ~ ~-0.6 if entity @n[type=marker,tag=bullet.data,dx=0.2,dy=2.9,dz=0.2] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc95 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.6), source.getPosition().y, (source.getPosition().z + -0.6)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zombie_scale_2"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc95.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0.2, 2.9, 0.2)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc95) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-0.7 ~ ~-0.7 if entity @n[type=marker,tag=bullet.data,dx=0.4,dy=0.4,dz=0.4] if function tower:bullet/move/tag run return 1
        {
            ServerCommandSource kfcSrc96 = source.withPosition(new net.minecraft.util.math.Vec3d((source.getPosition().x + -0.7), source.getPosition().y, (source.getPosition().z + -0.7)));
            if ((executor != null && executor.getCommandTags().contains("enemy.hitbox_type_zoglin"))) {
                if (KfcGen.anyEntityInBox(ctx, kfcSrc96.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.MARKER}, new String[]{"bullet.data"}, new String[0], 0.4, 0.4, 0.4)) {
                    if ((tdpack.buckets.Bucket8._m_3ad4b35f_tower_bullet_move_tag_executeReturn(kfcSrc96) != 0)) {
                        return 1;
                    }
                }
            }
        }

        // return 0
        return 0;
    }

    public static void _m_25ebc92f_tower_bullet_move_hit_execute(ServerCommandSource source) {
        _m_25ebc92f_tower_bullet_move_hit_executeReturn(source);
    }

    public static int _m_25ebc92f_tower_bullet_move_hit_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute store result score #damage enemy.hp run scoreboard players get @n[tag=bullet.data] bullet.attack
        KfcGen.setScore(sb, "#damage", "enemy.hp", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attack"));

        // execute store result score #temp enemy.state.stun run scoreboard players get @n[tag=bullet.data] bullet.attribute.stun
        KfcGen.setScore(sb, "#temp", "enemy.state.stun", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.stun"));

        // execute store result score #temp enemy.state.freeze run scoreboard players get @n[tag=bullet.data] bullet.attribute.freeze
        KfcGen.setScore(sb, "#temp", "enemy.state.freeze", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.freeze"));

        // execute store result score #temp enemy.state.poison run scoreboard players get @n[tag=bullet.data] bullet.attribute.poison
        KfcGen.setScore(sb, "#temp", "enemy.state.poison", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.poison"));

        // execute store result score #temp enemy.state.flame run scoreboard players get @n[tag=bullet.data] bullet.attribute.flame
        KfcGen.setScore(sb, "#temp", "enemy.state.flame", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.flame"));

        // execute store result score #temp enemy.state.bleed run scoreboard players get @n[tag=bullet.data] bullet.attribute.bleed
        KfcGen.setScore(sb, "#temp", "enemy.state.bleed", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.bleed"));

        // execute store result score #temp enemy.state.weakness run scoreboard players get @n[tag=bullet.data] bullet.attribute.weakness
        KfcGen.setScore(sb, "#temp", "enemy.state.weakness", KfcGen.getScoreOfEntity(sb, KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "bullet.attribute.weakness"));

        // execute as @n[tag=enemy.target.hit] at @s run function enemy:hit/main
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"enemy.target.hit"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc97 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            // -> enemy:hit/main
            tdpack.buckets.Bucket5._m_8b8e0228_enemy_hit_main_execute(kfcSrc97);
        } }

        // tp @s ~ ~ ~
        {
            net.minecraft.util.math.Vec3d _tpPos = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z);
        if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
        }

        // execute as @n[tag=bullet.core] run function tower:bullet/remove
        { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.core"}, new String[0], -1, -1, _ee -> (true));
          if (e != null) {
            ServerCommandSource es = source.withEntity(e);
            // -> tower:bullet/remove
            tdpack.buckets.Bucket8._m_dccc0fff_tower_bullet_remove_execute(es);
        } }

        // return 1
        return 1;
    }

    public static void _m_ef16061b_tower_bullet_move_main_execute(ServerCommandSource source) {
        _m_ef16061b_tower_bullet_move_main_executeReturn(source);
    }

    public static int _m_ef16061b_tower_bullet_move_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute on passengers as @s[tag=bullet.data] if score @s bullet.life matches ..0 on vehicle run function tower:bullet/remove
        for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
            net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
            if (_onEnt1 != null && _onEnt1.getCommandTags().contains("bullet.data")) { net.minecraft.entity.Entity _asE_98 = _onEnt1;
                ServerCommandSource _asSrc_99 = _on1.withEntity(_asE_98);
                if (KfcGen.scoreMatches(sb, (_asE_98 == null ? "<no-_asE_98>" : _asE_98.getNameForScoreboard()), "bullet.life", Integer.MIN_VALUE, 0)) {
                    { ServerCommandSource _on2 = KfcGen.onVehicle(_asSrc_99);
                      if (_on2 != null) {
                        net.minecraft.entity.Entity _onEnt2 = _on2.getEntity();
                        // -> tower:bullet/remove
                        tdpack.buckets.Bucket8._m_dccc0fff_tower_bullet_remove_execute(_on2);
                      } }
                }
            }
        }

        // execute store result score #temp bullet.life run scoreboard players get @s bullet.speed
        KfcGen.setScore(sb, "#temp", "bullet.life", KfcGen.getScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.speed"));

        // function tower:bullet/move/ray
        // -> tower:bullet/move/ray
        tdpack.buckets.Bucket8._m_0c33baed_tower_bullet_move_ray_execute(source);
        return 0;
    }

    public static void _m_0c33baed_tower_bullet_move_ray_execute(ServerCommandSource source) {
        _m_0c33baed_tower_bullet_move_ray_executeReturn(source);
    }

    public static int _m_0c33baed_tower_bullet_move_ray_executeReturn(ServerCommandSource source) {
        
        // 자기재귀 → 브릿지(스택오버플로 방지) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "bullet/move/ray"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // scoreboard players remove #temp bullet.life 1
                // KfcGen.addScore(sb, "#temp", "bullet.life", -(1));
                // 
                // // execute as @n[tag=enemy.target,distance=..5] at @s run function tower:bullet/move/detect_hitbox
                // { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"enemy.target"}, new String[0], -1, 5, _ee -> (true));
                //   if (e != null) {
                //     ServerCommandSource es = source.withEntity(e);
                //     ServerCommandSource kfcSrc100 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                //     // -> tower:bullet/move/detect_hitbox
                //     tdpack.buckets.Bucket8._m_a287a7b3_tower_bullet_move_detect_hitbox_execute(kfcSrc100);
                // } }
                // 
                // // execute if entity @n[tag=enemy.target.hit] if function tower:bullet/move/hit run return 0
                // if (KfcGen.anyEntityAnyType(ctx, source.getPosition(), new String[]{"enemy.target.hit"}, new String[0], -1, -1)) {
                //     if ((tdpack.buckets.Bucket8._m_25ebc92f_tower_bullet_move_hit_executeReturn(source) != 0)) {
                //         return 0;
                //     }
                // }
                // 
                // // tp @s ^ ^ ^0.25
                // {
                //     net.minecraft.util.math.Vec3d _tpPos = KfcGen.localOffset(source.getPosition(), source.getRotation(), 0.0, 0.0, 0.25);
                // if (executor != null) KfcGen.movePosition(executor, _tpPos.x, _tpPos.y, _tpPos.z);
                // }
                // 
                // // execute if score #temp bullet.life matches ..0 on passengers as @s[tag=bullet.data] run scoreboard players remove @s bullet.life 1
                // if (KfcGen.scoreMatches(sb, "#temp", "bullet.life", Integer.MIN_VALUE, 0)) {
                //     for (ServerCommandSource _on1 : KfcGen.onPassengers(source)) {
                //         net.minecraft.entity.Entity _onEnt1 = _on1.getEntity();
                //         { net.minecraft.entity.Entity e = _on1.getEntity();
                //           if (e != null && e.getCommandTags().contains("bullet.data")) {
                //             ServerCommandSource es = _on1.withEntity(e);
                //             if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "bullet.life", -(1));
                //         } }
                //     }
                // }
                // 
                // // execute if score #temp bullet.life matches ..0 run return 0
                // if (KfcGen.scoreMatches(sb, "#temp", "bullet.life", Integer.MIN_VALUE, 0)) {
                //     return 0;
                // }
                // 
                // // execute at @s run function tower:bullet/move/ray
                // {
                //     ServerCommandSource kfcSrc101 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
                //     // -> tower:bullet/move/ray
                //     tdpack.buckets.Bucket8._m_0c33baed_tower_bullet_move_ray_execute(kfcSrc101);
                // }
                return kfcBridgeRet;
    }

    public static void _m_3ad4b35f_tower_bullet_move_tag_execute(ServerCommandSource source) {
        _m_3ad4b35f_tower_bullet_move_tag_executeReturn(source);
    }

    public static int _m_3ad4b35f_tower_bullet_move_tag_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        // tag @s add enemy.target.hit
        if (executor != null) executor.addCommandTag("enemy.target.hit");

        // return 1
        return 1;
    }

    public static void _m_2db9ca26_tower_bullet_move_teleport_execute(ServerCommandSource source) {
        _m_2db9ca26_tower_bullet_move_teleport_executeReturn(source);
    }

    public static int _m_2db9ca26_tower_bullet_move_teleport_executeReturn(ServerCommandSource source) {
        
        // return 1
        return 1;
    }

    public static void _m_65f56f79_tower_bullet_spawn_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_65f56f79_tower_bullet_spawn_main_executeReturn(source, macroArgs);
    }

    public static int _m_65f56f79_tower_bullet_spawn_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        
        // 부분 변환(디스패처 의존 1/2줄) - 함수 단위 폴백.
                KfcGen.instantExecuteFunction(source, net.minecraft.util.Identifier.of("tower", "bullet/spawn/main"), macroArgs);
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // $function tower:bullet/spawn/model/$(model) with entity @s
                // // ⛔ 매크로 자바 변환 불가(함수 폴백)
                // 
                // // execute as @n[tag=bullet.data] at @s unless score @s bullet.attack matches 0.. run function tower:bullet/spawn/malloc
                // { net.minecraft.entity.Entity e = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true));
                //   if (e != null) {
                //     ServerCommandSource es = source.withEntity(e);
                //     ServerCommandSource kfcSrc102 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
                //     if ((!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "bullet.attack", 0, Integer.MAX_VALUE)))) {
                //         // -> tower:bullet/spawn/malloc
                //         tdpack.buckets.Bucket8._m_96ce5aa0_tower_bullet_spawn_malloc_execute(kfcSrc102);
                //     }
                // } }
        return 0;
    }

    public static void _m_96ce5aa0_tower_bullet_spawn_malloc_execute(ServerCommandSource source) {
        _m_96ce5aa0_tower_bullet_spawn_malloc_executeReturn(source);
    }

    public static int _m_96ce5aa0_tower_bullet_spawn_malloc_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score @s bullet.attack run data get storage tower temp.attack
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.attack", (int)(KfcGen.storageGetDouble(server, "tower", "temp.attack")));

        // execute store result score @s bullet.life run data get storage tower temp.Bullet.life
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "bullet.life", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.life")));

        // execute store result score @n[tag=bullet.core] bullet.speed run data get storage tower temp.Bullet.speed 4
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.core"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.speed", (int)((KfcGen.storageGetDouble(server, "tower", "temp.Bullet.speed")) * 4)); }

        // execute store result score @n[tag=bullet.data] bullet.attribute.freeze run data get storage tower temp.Bullet.attribute.freeze
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.freeze", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.freeze"))); }

        // execute store result score @n[tag=bullet.data] bullet.attribute.poison run data get storage tower temp.Bullet.attribute.poison
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.poison", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.poison"))); }

        // execute store result score @n[tag=bullet.data] bullet.attribute.flame run data get storage tower temp.Bullet.attribute.flame
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.flame", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.flame"))); }

        // execute store result score @n[tag=bullet.data] bullet.attribute.bleed run data get storage tower temp.Bullet.attribute.bleed
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.bleed", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.bleed"))); }

        // execute store result score @n[tag=bullet.data] bullet.attribute.stun run data get storage tower temp.Bullet.attribute.stun
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.stun", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.stun"))); }

        // execute store result score @n[tag=bullet.data] bullet.attribute.weakness run data get storage tower temp.Bullet.attribute.weakness
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.weakness", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.weakness"))); }

        // execute if data storage tower temp.Bullet.attribute.floor run tag @n[tag=bullet.data] add bullet.flooring
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.Bullet.attribute.floor")) {
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.addCommandTag("bullet.flooring"); }
        }

        // execute store result score @n[tag=bullet.data] bullet.floor.range run data get storage tower temp.Bullet.attribute.floor.range
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.floor.range", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.range"))); }

        // execute store result score @n[tag=bullet.data] bullet.floor.time run data get storage tower temp.Bullet.attribute.floor.time
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.floor.time", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.time"))); }

        // data modify entity @n[tag=bullet.data] data.Bullet.flooring set from storage tower temp.Bullet.attribute.floor
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetStorage(server, "tower", "temp.Bullet.attribute.floor"); if (_v != null) KfcGen.nbtSetEntity(KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"bullet.data"}, new String[0], -1, -1, _ee -> (true)), "data.Bullet.flooring", _v); }
        return 0;
    }

    public static void _m_5fbeda1d_tower_bullet_spawn_floor_main_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_5fbeda1d_tower_bullet_spawn_floor_main_executeReturn(source, macroArgs);
    }

    public static int _m_5fbeda1d_tower_bullet_spawn_floor_main_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // $summon area_effect_cloud ~ -60 ~ {Particle:{type:"dust",color:[0.369,0.486,0.086],scale:2},Radius:$(range)f,Duration:$(time),WaitTime:0,Tags:[floor-$(range),bullet.data,not-allocated,bullet.floor]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, -60.0, source.getPosition().z); KfcGen.summon(ctx.world, "area_effect_cloud", _sp.x, _sp.y, _sp.z, "{Particle:{type:\"dust\",color:[0.369,0.486,0.086],scale:2},Radius:" + macroArgs.get("range") + "f,Duration:" + macroArgs.get("time") + ",WaitTime:0,Tags:[floor-" + macroArgs.get("range") + ",bullet.data,not-allocated,bullet.floor]}"); }

        // $scoreboard players set @n[tag=not-allocated] bullet.floor.range $(range)
        try { { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "bullet.floor.range", Integer.parseInt(macroArgs.get("range"))); } } catch (NumberFormatException _nfe) {}

        // $scoreboard players set @n[tag=not-allocated] bullet.floor.time $(time)
        try { { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) KfcGen.setScore(sb, _t.getNameForScoreboard(), "bullet.floor.time", Integer.parseInt(macroArgs.get("time"))); } } catch (NumberFormatException _nfe) {}

        // execute store result score @n[tag=not-allocated] bullet.attack run data get storage tower temp.Bullet.attribute.floor.attack
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attack", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.attack"))); }

        // execute store result score @n[tag=not-allocated] bullet.attribute.freeze run data get storage tower temp.Bullet.attribute.floor.attribute.freeze
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.freeze", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.attribute.freeze"))); }

        // execute store result score @n[tag=not-allocated] bullet.attribute.poison run data get storage tower temp.Bullet.attribute.floor.attribute.poison
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.poison", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.attribute.poison"))); }

        // execute store result score @n[tag=not-allocated] bullet.attribute.flame run data get storage tower temp.Bullet.attribute.floor.attribute.flame
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.flame", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.attribute.flame"))); }

        // execute store result score @n[tag=not-allocated] bullet.attribute.bleed run data get storage tower temp.Bullet.attribute.floor.attribute.bleed
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.bleed", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.attribute.bleed"))); }

        // execute store result score @n[tag=not-allocated] bullet.attribute.stun run data get storage tower temp.Bullet.attribute.floor.attribute.stun
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.stun", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.attribute.stun"))); }

        // execute store result score @n[tag=not-allocated] bullet.attribute.weakness run data get storage tower temp.Bullet.attribute.floor.attribute.weakness
        { net.minecraft.entity.Entity _se = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), "bullet.attribute.weakness", (int)(KfcGen.storageGetDouble(server, "tower", "temp.Bullet.attribute.floor.attribute.weakness"))); }

        // tag @n[tag=not-allocated] remove not-allocated
        { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, source.getPosition(), new String[]{"not-allocated"}, new String[0], -1, -1, _ee -> (true)); if (_t != null) _t.removeCommandTag("not-allocated"); }
        return 0;
    }

    public static void _m_9c50909a_tower_bullet_spawn_model_arrow_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_9c50909a_tower_bullet_spawn_model_arrow_executeReturn(source, macroArgs);
    }

    public static int _m_9c50909a_tower_bullet_spawn_model_arrow_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // $summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:arrow"},item_display:"none",transformation:{left_rotation:[0.65328148f,0.27059805f,-0.27059805f,0.65328148f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[1f,1f,1f]},Tags:[bullet],teleport_duration:1,Rotation:$(Rotation)},{id:"minecraft:marker",Tags:[bullet,bullet.data]}],Tags:[bullet,bullet.core],teleport_duration:1,Rotation:$(Rotation)}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:arrow\"},item_display:\"none\",transformation:{left_rotation:[0.65328148f,0.27059805f,-0.27059805f,0.65328148f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[1f,1f,1f]},Tags:[bullet],teleport_duration:1,Rotation:" + macroArgs.get("Rotation") + "},{id:\"minecraft:marker\",Tags:[bullet,bullet.data]}],Tags:[bullet,bullet.core],teleport_duration:1,Rotation:" + macroArgs.get("Rotation") + "}"); }
        return 0;
    }

    public static void _m_f2851d2f_tower_bullet_spawn_model_potato_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_f2851d2f_tower_bullet_spawn_model_potato_executeReturn(source, macroArgs);
    }

    public static int _m_f2851d2f_tower_bullet_spawn_model_potato_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // $summon block_display ~ ~ ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:potato"},item_display:"none",transformation:{left_rotation:[0.65328148f,0.27059805f,-0.27059805f,0.65328148f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[1f,1f,1f]},Tags:[bullet],teleport_duration:1,Rotation:$(Rotation)},{id:"minecraft:marker",Tags:[bullet,bullet.data]}],Tags:[bullet,bullet.core],teleport_duration:1,Rotation:$(Rotation)}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{id:\"minecraft:item_display\",item:{id:\"minecraft:potato\"},item_display:\"none\",transformation:{left_rotation:[0.65328148f,0.27059805f,-0.27059805f,0.65328148f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[1f,1f,1f]},Tags:[bullet],teleport_duration:1,Rotation:" + macroArgs.get("Rotation") + "},{id:\"minecraft:marker\",Tags:[bullet,bullet.data]}],Tags:[bullet,bullet.core],teleport_duration:1,Rotation:" + macroArgs.get("Rotation") + "}"); }
        return 0;
    }

    public static void _m_d202a715_tower_spawn_main_execute(ServerCommandSource source) {
        _m_d202a715_tower_spawn_main_executeReturn(source);
    }

    public static int _m_d202a715_tower_spawn_main_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        net.minecraft.server.MinecraftServer server = source.getServer();
        // data remove storage tower temp
        KfcGen.storageRemovePath(server, "tower", "temp");

        // data remove storage tower temp2
        KfcGen.storageRemovePath(server, "tower", "temp2");

        // data modify storage tower temp set from entity @s SelectedItem
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "SelectedItem"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp", _v); }

        // data modify storage tower temp2 set from entity @s equipment.offhand
        { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(executor, "equipment.offhand"); if (_v != null) KfcGen.nbtSetStorage(server, "tower", "temp2", _v); }

        // execute if data storage tower temp.components.minecraft:custom_data.Tower_Status run function tower:spawn/blueprint/main
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp.components.minecraft:custom_data.Tower_Status")) {
            // -> tower:spawn/blueprint/main
            tdpack.buckets.Bucket8._m_82a0401f_tower_spawn_blueprint_main_execute(source);
        }

        // execute if entity @s[tag=blueprint_player] unless data storage tower temp.components.minecraft:custom_data.Tower_Status run function tower:spawn/blueprint/remove
        if ((executor != null && executor.getCommandTags().contains("blueprint_player"))) {
            if (!(KfcGen.storageHasPath(source.getServer(), "tower", "temp.components.minecraft:custom_data.Tower_Status"))) {
                // -> tower:spawn/blueprint/remove
                tdpack.buckets.Bucket8._m_7fa8352c_tower_spawn_blueprint_remove_execute(source);
            }
        }

        // execute if data storage tower temp2.components.minecraft:custom_data.Tower_Status run function tower:spawn/summon/main
        if (KfcGen.storageHasPath(source.getServer(), "tower", "temp2.components.minecraft:custom_data.Tower_Status")) {
            // -> tower:spawn/summon/main
            tdpack.buckets.Bucket10._m_545695b9_tower_spawn_summon_main_execute(source);
        }
        return 0;
    }

    public static void _m_82a0401f_tower_spawn_blueprint_main_execute(ServerCommandSource source) {
        _m_82a0401f_tower_spawn_blueprint_main_executeReturn(source);
    }

    public static int _m_82a0401f_tower_spawn_blueprint_main_executeReturn(ServerCommandSource source) {
        
        // 부분 변환(디스패처 의존 2/4줄) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/blueprint/main"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // execute store result score #temp blueprint_id run data get storage tower temp.components.minecraft:custom_data.Tower_Status.location 1.0
                // KfcGen.setScore(sb, "#temp", "blueprint_id", (int)(KfcGen.storageGetDouble(server, "tower", "temp.components.minecraft:custom_data.Tower_Status.location")));
                // 
                // // execute if score #temp blueprint_id matches 1 unless entity @s[tag=blueprint_player] anchored eyes facing ~ -60 ~ anchored feet positioned ^ ^ ^1 positioned ~ ~1 ~ positioned as @s[distance=..1] positioned ~ -60 ~ rotated ~ 0 positioned ^ ^ ^256 facing entity @s eyes positioned as @s positioned ^ ^1 ^ rotated as @s positioned ^ ^ ^1 positioned as @s[distance=..1.414] positioned ~ -60 ~ rotated ~ 0 positioned ^ ^ ^128 facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^64 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^32 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^16 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^8 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^4 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^2 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^1 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.5 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.25 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.125 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.0625 ^ positioned ~ -60 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -60 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.03125 ^ positioned ~ -60 ~ align xyz run function tower:spawn/blueprint/summon with storage tower temp.components.minecraft:custom_data.Tower_Status
                // // ⛔ 자바 변환 불가(함수 폴백): execute 수정자 미지원: positioned as @s[distance=..1] (단일 해소 불가)
                // 
                // // execute if score #temp blueprint_id matches 2 unless entity @s[tag=blueprint_player] anchored eyes facing ~ -52 ~ anchored feet positioned ^ ^ ^1 positioned ~ ~1 ~ positioned as @s[distance=..1] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^256 facing entity @s eyes positioned as @s positioned ^ ^1 ^ rotated as @s positioned ^ ^ ^1 positioned as @s[distance=..1.414] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^128 facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^64 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^32 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^16 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^8 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^4 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^2 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^1 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.5 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.25 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.125 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.0625 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.03125 ^ positioned ~ -52 ~ align xyz run function tower:spawn/blueprint/summon with storage tower temp.components.minecraft:custom_data.Tower_Status
                // // ⛔ 자바 변환 불가(함수 폴백): execute 수정자 미지원: positioned as @s[distance=..1] (단일 해소 불가)
                // 
                // // execute if entity @s[tag=blueprint_player] run function tower:spawn/blueprint/teleport
                // if ((executor != null && executor.getCommandTags().contains("blueprint_player"))) {
                //     // -> tower:spawn/blueprint/teleport
                //     tdpack.buckets.Bucket8._m_ad3a3973_tower_spawn_blueprint_teleport_execute(source);
                // }
                return kfcBridgeRet;
    }

    public static void _m_a066c139_tower_spawn_blueprint_modify_execute(ServerCommandSource source) {
        _m_a066c139_tower_spawn_blueprint_modify_executeReturn(source);
    }

    public static int _m_a066c139_tower_spawn_blueprint_modify_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        net.minecraft.server.MinecraftServer server = source.getServer();
        // execute store result score buffer tower.number run data get storage minecraft:tower temp.components.minecraft:custom_data.Tower_Status.number
        KfcGen.setScore(sb, "buffer", "tower.number", (int)(KfcGen.storageGetDouble(server, "minecraft:tower", "temp.components.minecraft:custom_data.Tower_Status.number")));

        // execute if score buffer tower.number = @s tower.number run return fail
        if (KfcGen.scoreCmp(sb, "buffer", "tower.number", "=", (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.number")) {
            return 0;
        }

        // execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.scale[0] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.size
        for (Entity e : ctx.world.getEntitiesByType(EntityType.BLOCK_DISPLAY,
                en -> en.getCommandTags().contains("blueprint") && KfcGen.scoreMatches(sb, en.getNameForScoreboard(), "blueprint_id", 1, Integer.MAX_VALUE))) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "blueprint_id", false))) continue;
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "SelectedItem.components.minecraft:custom_data.Tower_Status.size"); if (_v != null) KfcGen.nbtSetEntity(e, "transformation.scale[0]", _v); }
        }

        // execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.scale[2] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.size
        for (Entity e : ctx.world.getEntitiesByType(EntityType.BLOCK_DISPLAY,
                en -> en.getCommandTags().contains("blueprint") && KfcGen.scoreMatches(sb, en.getNameForScoreboard(), "blueprint_id", 1, Integer.MAX_VALUE))) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "blueprint_id", false))) continue;
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "SelectedItem.components.minecraft:custom_data.Tower_Status.size"); if (_v != null) KfcGen.nbtSetEntity(e, "transformation.scale[2]", _v); }
        }

        // execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.translation[0] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.translation
        for (Entity e : ctx.world.getEntitiesByType(EntityType.BLOCK_DISPLAY,
                en -> en.getCommandTags().contains("blueprint") && KfcGen.scoreMatches(sb, en.getNameForScoreboard(), "blueprint_id", 1, Integer.MAX_VALUE))) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "blueprint_id", false))) continue;
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "SelectedItem.components.minecraft:custom_data.Tower_Status.translation"); if (_v != null) KfcGen.nbtSetEntity(e, "transformation.translation[0]", _v); }
        }

        // execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.translation[2] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.translation
        for (Entity e : ctx.world.getEntitiesByType(EntityType.BLOCK_DISPLAY,
                en -> en.getCommandTags().contains("blueprint") && KfcGen.scoreMatches(sb, en.getNameForScoreboard(), "blueprint_id", 1, Integer.MAX_VALUE))) {
            ServerCommandSource es = source.withEntity(e);
            if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "blueprint_id", false))) continue;
            { net.minecraft.nbt.NbtElement _v = KfcGen.nbtGetEntity(KfcGen.nearestPlayer(ctx, es.getPosition(), new String[0], new String[0], -1, -1), "SelectedItem.components.minecraft:custom_data.Tower_Status.translation"); if (_v != null) KfcGen.nbtSetEntity(e, "transformation.translation[2]", _v); }
        }

        // execute store result score @s tower.number run data get storage minecraft:tower temp.components.minecraft:custom_data.Tower_Status.number
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "tower.number", (int)(KfcGen.storageGetDouble(server, "minecraft:tower", "temp.components.minecraft:custom_data.Tower_Status.number")));
        return 0;
    }

    public static void _m_7fa8352c_tower_spawn_blueprint_remove_execute(ServerCommandSource source) {
        _m_7fa8352c_tower_spawn_blueprint_remove_executeReturn(source);
    }

    public static int _m_7fa8352c_tower_spawn_blueprint_remove_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // scoreboard players operation buffer blueprint_id = @s blueprint_id
        if (executor != null) KfcGen.opScore(sb, "buffer", "blueprint_id", "=", executor.getNameForScoreboard(), "blueprint_id");

        // execute at @s as @e[type=minecraft:block_display,tag=blueprint] if score @s blueprint_id = buffer blueprint_id run kill @s
        {
            ServerCommandSource kfcSrcPre128 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if (kfcSrcPre128 != null) {
                for (Entity e : ctx.world.getEntitiesByType(EntityType.BLOCK_DISPLAY,
                        en -> en.getCommandTags().contains("blueprint"))) {
                    ServerCommandSource es = kfcSrcPre128.withEntity(e);
                    if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", "buffer", "blueprint_id"))) continue;
                    if (e != null) KfcGen.killEntity(e);
                }
            }
        }

        // execute at @s as @e[type=minecraft:marker,tag=blueprint_range] if score @s blueprint_id = buffer blueprint_id run kill @s
        {
            ServerCommandSource kfcSrcPre130 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if (kfcSrcPre130 != null) {
                for (Entity e : ctx.world.getEntitiesByType(EntityType.MARKER,
                        en -> en.getCommandTags().contains("blueprint_range"))) {
                    ServerCommandSource es = kfcSrcPre130.withEntity(e);
                    if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", "buffer", "blueprint_id"))) continue;
                    if (e != null) KfcGen.killEntity(e);
                }
            }
        }

        // scoreboard players reset @s blueprint_id
        if (executor != null) KfcGen.resetScore(sb, executor.getNameForScoreboard(), "blueprint_id");

        // scoreboard players reset @s tower.number
        if (executor != null) KfcGen.resetScore(sb, executor.getNameForScoreboard(), "tower.number");

        // tag @s remove blueprint_player
        if (executor != null) executor.removeCommandTag("blueprint_player");
        return 0;
    }

    public static void _m_722a98e6_tower_spawn_blueprint_summon_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_722a98e6_tower_spawn_blueprint_summon_executeReturn(source, macroArgs);
    }

    public static int _m_722a98e6_tower_spawn_blueprint_summon_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // execute store result score @s blueprint_id run scoreboard players add global blueprint_seq 1
        KfcGen.addScore(sb, "global", "blueprint_seq", 1);
        KfcGen.setScore(sb, (executor == null ? "<no-executor>" : executor.getNameForScoreboard()), "blueprint_id", KfcGen.getScore(sb, "global", "blueprint_seq"));

        // $summon block_display ~ ~ ~ {teleport_duration:1,Tags:["blueprint"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[$(translation),0f,$(translation)],scale:[$(size),0.2f,$(size)]},block_state:{Name:"minecraft:lime_stained_glass"}}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{teleport_duration:1,Tags:[\"blueprint\"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[" + macroArgs.get("translation") + ",0f," + macroArgs.get("translation") + "],scale:[" + macroArgs.get("size") + ",0.2f," + macroArgs.get("size") + "]},block_state:{Name:\"minecraft:lime_stained_glass\"}}"); }

        // execute at @s as @e[type=minecraft:block_display,tag=blueprint,sort=nearest,limit=1] unless score @s blueprint_id matches 0.. run scoreboard players operation @s blueprint_id = global blueprint_seq
        {
            ServerCommandSource kfcSrcPre132 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
            if (kfcSrcPre132 != null) {
                { net.minecraft.entity.Entity e = KfcGen.nearestEntity(ctx, kfcSrcPre132.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"blueprint"}, new String[0], -1, -1);
                  if (e != null) {
                    ServerCommandSource es = kfcSrcPre132.withEntity(e);
                    if ((!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", 0, Integer.MAX_VALUE)))) {
                        if (e != null) KfcGen.opScore(sb, e.getNameForScoreboard(), "blueprint_id", "=", "global", "blueprint_seq");
                    }
                } }
            }
        }

        // summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[0f,0f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[\"blueprint_range\"],Rotation:[0f,0f]}"); }

        // summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[90f,0f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[\"blueprint_range\"],Rotation:[90f,0f]}"); }

        // summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[180f,0f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[\"blueprint_range\"],Rotation:[180f,0f]}"); }

        // summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[270f,0f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "marker", _sp.x, _sp.y, _sp.z, "{Tags:[\"blueprint_range\"],Rotation:[270f,0f]}"); }

        // execute as @e[tag=blueprint_range] unless score @s blueprint_id matches 0.. run scoreboard players operation @s blueprint_id = global blueprint_seq
        for (Entity e : ctx.world.iterateEntities()) {
            if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
            Entity en = e; if (!(en.getCommandTags().contains("blueprint_range"))) continue;
            ServerCommandSource es = source.withEntity(e);
            if (!(!(KfcGen.scoreMatches(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", 0, Integer.MAX_VALUE)))) continue;
            if (e != null) KfcGen.opScore(sb, e.getNameForScoreboard(), "blueprint_id", "=", "global", "blueprint_seq");
        }

        // tag @s add blueprint_player
        if (executor != null) executor.addCommandTag("blueprint_player");
        return 0;
    }

    public static void _m_ad3a3973_tower_spawn_blueprint_teleport_execute(ServerCommandSource source) {
        _m_ad3a3973_tower_spawn_blueprint_teleport_executeReturn(source);
    }

    public static int _m_ad3a3973_tower_spawn_blueprint_teleport_executeReturn(ServerCommandSource source) {
        
        // 부분 변환(디스패처 의존 2/7줄) - 함수 단위 폴백 (반환값 전파).
                int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, net.minecraft.util.Identifier.of("tower", "spawn/blueprint/teleport"));
                // 아래는 줄 단위 변환 시도 결과(참고용 주석):
                // // scoreboard players operation buffer blueprint_id = @s blueprint_id
                // if (executor != null) KfcGen.opScore(sb, "buffer", "blueprint_id", "=", executor.getNameForScoreboard(), "blueprint_id");
                // 
                // // execute if score #temp blueprint_id matches 1 at @s at @s positioned 0 -60 0 positioned ^ ^ ^-1056 rotated ~180 ~ positioned ^ ^ ^992 facing 0 -60 0 positioned as @s positioned ^ ^32768 ^ positioned ~ -60 ~ facing entity @s eyes positioned as @s positioned ^ ^1024 ^ positioned ~ -60 ~ as @e[type=minecraft:block_display,tag=blueprint] if score @s blueprint_id = buffer blueprint_id align xyz positioned ~ -60 ~ run tp ~ ~ ~
                // {
                //     ServerCommandSource kfcSrcPre148 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
                //     ServerCommandSource kfcSrcPre149 = (executor != null ? kfcSrcPre148.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : kfcSrcPre148);
                //     ServerCommandSource kfcSrcPre150 = kfcSrcPre149.withPosition(new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0));
                //     ServerCommandSource kfcSrcPre151 = kfcSrcPre150.withPosition(KfcGen.localOffset(kfcSrcPre150.getPosition(), kfcSrcPre150.getRotation(), 0.0, 0.0, -1056.0));
                //     ServerCommandSource kfcSrcPre152 = kfcSrcPre151.withRotation(new net.minecraft.util.math.Vec2f(kfcSrcPre151.getRotation().x, (kfcSrcPre151.getRotation().y + 180.0f)));
                //     ServerCommandSource kfcSrcPre153 = kfcSrcPre152.withPosition(KfcGen.localOffset(kfcSrcPre152.getPosition(), kfcSrcPre152.getRotation(), 0.0, 0.0, 992.0));
                //     ServerCommandSource kfcSrcPre154 = KfcGen.facing(kfcSrcPre153, new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0).x, new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0).y, new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0).z);
                //     ServerCommandSource kfcSrcPre155 = (executor != null ? kfcSrcPre154.withPosition(executor.getPos()) : null);
                //     ServerCommandSource kfcSrcPre156 = (kfcSrcPre155 == null ? null : kfcSrcPre155.withPosition(KfcGen.localOffset(kfcSrcPre155.getPosition(), kfcSrcPre155.getRotation(), 0.0, 32768.0, 0.0)));
                //     ServerCommandSource kfcSrcPre157 = (kfcSrcPre156 == null ? null : kfcSrcPre156.withPosition(new net.minecraft.util.math.Vec3d(kfcSrcPre156.getPosition().x, -60.0, kfcSrcPre156.getPosition().z)));
                //     net.minecraft.entity.Entity _faceE158 = executor;
                //     ServerCommandSource kfcSrcPre159 = (kfcSrcPre157 == null ? null : KfcGen.facingEntity(kfcSrcPre157, _faceE158, true));
                //     ServerCommandSource kfcSrcPre160 = (kfcSrcPre159 == null ? null : (executor != null ? kfcSrcPre159.withPosition(executor.getPos()) : null));
                //     ServerCommandSource kfcSrcPre161 = (kfcSrcPre160 == null ? null : kfcSrcPre160.withPosition(KfcGen.localOffset(kfcSrcPre160.getPosition(), kfcSrcPre160.getRotation(), 0.0, 1024.0, 0.0)));
                //     ServerCommandSource kfcSrcPre162 = (kfcSrcPre161 == null ? null : kfcSrcPre161.withPosition(new net.minecraft.util.math.Vec3d(kfcSrcPre161.getPosition().x, -60.0, kfcSrcPre161.getPosition().z)));
                //     if (KfcGen.scoreMatches(sb, "#temp", "blueprint_id", 1, 1)) {
                //         if (kfcSrcPre162 != null) {
                //             for (Entity e : ctx.world.getEntitiesByType(EntityType.BLOCK_DISPLAY,
                //                     en -> en.getCommandTags().contains("blueprint"))) {
                //                 ServerCommandSource es = kfcSrcPre162.withEntity(e);
                //                 ServerCommandSource kfcSrc163 = es.withPosition(new net.minecraft.util.math.Vec3d(Math.floor(es.getPosition().x), Math.floor(es.getPosition().y), Math.floor(es.getPosition().z)));
                //                 ServerCommandSource kfcSrc164 = kfcSrc163.withPosition(new net.minecraft.util.math.Vec3d(kfcSrc163.getPosition().x, -60.0, kfcSrc163.getPosition().z));
                //                 if (!(KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", "buffer", "blueprint_id"))) continue;
                //                 {
                //                     net.minecraft.util.math.Vec3d _tpPos = new net.minecraft.util.math.Vec3d(kfcSrc164.getPosition().x, kfcSrc164.getPosition().y, kfcSrc164.getPosition().z);
                //                 if (e != null) KfcGen.movePosition(e, _tpPos.x, _tpPos.y, _tpPos.z);
                //                 }
                //             }
                //         }
                //     }
                // }
                // 
                // // execute if score #temp blueprint_id matches 2 at @s anchored eyes facing ~ -52 ~ anchored feet positioned ^ ^ ^1 positioned ~ ~1 ~ positioned as @s[distance=..1] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^256 facing entity @s eyes positioned as @s positioned ^ ^1 ^ rotated as @s positioned ^ ^ ^1 positioned as @s[distance=..1.414] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^128 facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^64 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^32 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^16 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^8 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^4 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^2 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^1 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.5 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.25 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.125 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.0625 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.03125 ^ positioned ~ -52 ~ as @e[type=minecraft:block_display,tag=blueprint] if score @s blueprint_id = buffer blueprint_id align xyz positioned ~ -52 ~ run tp ~ ~ ~
                // // ⛔ 자바 변환 불가(함수 폴백): execute 수정자 미지원: positioned as @s[distance=..1] (단일 해소 불가)
                // 
                // // execute store result storage minecraft:buffer particledata.range float 1 run data get storage minecraft:tower temp.components.minecraft:custom_data.Tower_Status.range 1
                // KfcGen.storagePutNumber(server, "minecraft:buffer", "particledata.range", (int)(KfcGen.storageGetDouble(server, "minecraft:tower", "temp.components.minecraft:custom_data.Tower_Status.range")), "float");
                // 
                // // execute if score #temp blueprint_id matches 1 at @s positioned 0 -60 0 positioned ^ ^ ^-1056 rotated ~180 ~ positioned ^ ^ ^992 facing 0 -60 0 positioned as @s positioned ^ ^32768 ^ positioned ~ -60 ~ facing entity @s eyes positioned as @s positioned ^ ^1024 ^ positioned ~ -60 ~ as @e[tag=blueprint_range] rotated as @s if score @s blueprint_id = buffer blueprint_id align xyz run function tower:spawn/blueprint/teleport_range with storage minecraft:buffer particledata
                // {
                //     ServerCommandSource kfcSrcPre191 = (executor != null ? source.withPosition(executor.getPos()).withRotation(new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : source);
                //     ServerCommandSource kfcSrcPre192 = kfcSrcPre191.withPosition(new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0));
                //     ServerCommandSource kfcSrcPre193 = kfcSrcPre192.withPosition(KfcGen.localOffset(kfcSrcPre192.getPosition(), kfcSrcPre192.getRotation(), 0.0, 0.0, -1056.0));
                //     ServerCommandSource kfcSrcPre194 = kfcSrcPre193.withRotation(new net.minecraft.util.math.Vec2f(kfcSrcPre193.getRotation().x, (kfcSrcPre193.getRotation().y + 180.0f)));
                //     ServerCommandSource kfcSrcPre195 = kfcSrcPre194.withPosition(KfcGen.localOffset(kfcSrcPre194.getPosition(), kfcSrcPre194.getRotation(), 0.0, 0.0, 992.0));
                //     ServerCommandSource kfcSrcPre196 = KfcGen.facing(kfcSrcPre195, new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0).x, new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0).y, new net.minecraft.util.math.Vec3d(0.0, -60.0, 0.0).z);
                //     ServerCommandSource kfcSrcPre197 = (executor != null ? kfcSrcPre196.withPosition(executor.getPos()) : null);
                //     ServerCommandSource kfcSrcPre198 = (kfcSrcPre197 == null ? null : kfcSrcPre197.withPosition(KfcGen.localOffset(kfcSrcPre197.getPosition(), kfcSrcPre197.getRotation(), 0.0, 32768.0, 0.0)));
                //     ServerCommandSource kfcSrcPre199 = (kfcSrcPre198 == null ? null : kfcSrcPre198.withPosition(new net.minecraft.util.math.Vec3d(kfcSrcPre198.getPosition().x, -60.0, kfcSrcPre198.getPosition().z)));
                //     net.minecraft.entity.Entity _faceE200 = executor;
                //     ServerCommandSource kfcSrcPre201 = (kfcSrcPre199 == null ? null : KfcGen.facingEntity(kfcSrcPre199, _faceE200, true));
                //     ServerCommandSource kfcSrcPre202 = (kfcSrcPre201 == null ? null : (executor != null ? kfcSrcPre201.withPosition(executor.getPos()) : null));
                //     ServerCommandSource kfcSrcPre203 = (kfcSrcPre202 == null ? null : kfcSrcPre202.withPosition(KfcGen.localOffset(kfcSrcPre202.getPosition(), kfcSrcPre202.getRotation(), 0.0, 1024.0, 0.0)));
                //     ServerCommandSource kfcSrcPre204 = (kfcSrcPre203 == null ? null : kfcSrcPre203.withPosition(new net.minecraft.util.math.Vec3d(kfcSrcPre203.getPosition().x, -60.0, kfcSrcPre203.getPosition().z)));
                //     if (KfcGen.scoreMatches(sb, "#temp", "blueprint_id", 1, 1)) {
                //         if (kfcSrcPre204 != null) {
                //             for (Entity e : ctx.world.iterateEntities()) {
                //                 if (e == null) continue;   // iterateEntities() 가 null 슬롯을 낼 수 있음(NPE 방지)
                //                 Entity en = e; if (!(en.getCommandTags().contains("blueprint_range"))) continue;
                //                 ServerCommandSource es = kfcSrcPre204.withEntity(e);
                //                 ServerCommandSource kfcSrc205 = (e != null ? es.withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : null);
                //                 ServerCommandSource kfcSrc206 = (kfcSrc205 == null ? null : kfcSrc205.withPosition(new net.minecraft.util.math.Vec3d(Math.floor(kfcSrc205.getPosition().x), Math.floor(kfcSrc205.getPosition().y), Math.floor(kfcSrc205.getPosition().z))));
                //                 if (!(kfcSrc206 != null && KfcGen.scoreCmp(sb, (e == null ? "<no-e>" : e.getNameForScoreboard()), "blueprint_id", "=", "buffer", "blueprint_id"))) continue;
                //                 // -> tower:spawn/blueprint/teleport_range
                //                 tdpack.buckets.Bucket8._m_8fbd0c67_tower_spawn_blueprint_teleport_range_execute(kfcSrc206, KfcGen.storageMacroArgs(server, "minecraft:buffer", "particledata"));
                //             }
                //         }
                //     }
                // }
                // 
                // // execute if score #temp blueprint_id matches 2 anchored eyes facing ~ -52 ~ anchored feet positioned ^ ^ ^1 positioned ~ ~1 ~ positioned as @s[distance=..1] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^256 facing entity @s eyes positioned as @s positioned ^ ^1 ^ rotated as @s positioned ^ ^ ^1 positioned as @s[distance=..1.414] positioned ~ -52 ~ rotated ~ 0 positioned ^ ^ ^128 facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^64 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^32 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^16 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^8 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^4 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^2 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^1 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.5 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.25 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.125 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.0625 ^ positioned ~ -52 ~ facing entity @s eyes positioned ^ ^ ^1 rotated as @s positioned ^ ^ ^1 facing ~ -52 ~ positioned ^ ^ ^100000000 rotated 90 ~ positioned ^ ^ ^100000000 facing entity @s eyes positioned ^ ^.03125 ^ positioned ~ -52 ~ as @e[tag=blueprint_range] rotated as @s if score @s blueprint_id = buffer blueprint_id align xyz run function tower:spawn/blueprint/teleport_range with storage minecraft:buffer particledata
                // // ⛔ 자바 변환 불가(함수 폴백): execute 수정자 미지원: positioned as @s[distance=..1] (단일 해소 불가)
                // 
                // // function tower:spawn/blueprint/modify
                // // -> tower:spawn/blueprint/modify
                // tdpack.buckets.Bucket8._m_a066c139_tower_spawn_blueprint_modify_execute(source);
                return kfcBridgeRet;
    }

    public static void _m_8fbd0c67_tower_spawn_blueprint_teleport_range_execute(ServerCommandSource source, Map<String, String> macroArgs) {
        _m_8fbd0c67_tower_spawn_blueprint_teleport_range_executeReturn(source, macroArgs);
    }

    public static int _m_8fbd0c67_tower_spawn_blueprint_teleport_range_executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        // $execute positioned ^ ^ ^$(range) run particle dust{color:[1.000,0.000,0.000],scale:2} ~ -60 ~ 0 0 0 0 1 force
        {
            ServerCommandSource kfcSrc217 = null; try { kfcSrc217 = source.withPosition(KfcGen.localOffset(source.getPosition(), source.getRotation(), 0.0, 0.0, Double.parseDouble(macroArgs.get("range")))); } catch (NumberFormatException _nfe) {}
            { net.minecraft.util.math.Vec3d _pp = new net.minecraft.util.math.Vec3d(kfcSrc217.getPosition().x, -60.0, kfcSrc217.getPosition().z); KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, 0, 0, 0, 0, (int)(1), 1.000f, 0.000f, 0.000f, 2f, true, null); }
        }

        // rotate @s ~5 0
        if (executor != null) KfcGen.rotateTo(executor, (source.getRotation().y + 5.0f), 0.0f);
        return 0;
    }

    public static void _m_c7494f59_tower_spawn_model_bleed_tower_lv0_execute(ServerCommandSource source) {
        _m_c7494f59_tower_spawn_model_bleed_tower_lv0_executeReturn(source);
    }

    public static int _m_c7494f59_tower_spawn_model_bleed_tower_lv0_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // summon block_display ~ ~1.5 ~ {Passengers:[{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:wooden_sword"},item_display:"none",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]},{id:"minecraft:interaction",width:2f,height:-1.5f,response:1b,Tags:[tower,tower.hitbox]},{id:"minecraft:marker",Tags:[tower,bleed-tower,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,bleed-tower,tower.core]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, (source.getPosition().y + 1.5), source.getPosition().z); KfcGen.summon(ctx.world, "block_display", _sp.x, _sp.y, _sp.z, "{Passengers:[{teleport_duration:3,Tags:[tower,tower.head],id:\"minecraft:item_display\",item:{id:\"minecraft:wooden_sword\"},item_display:\"none\",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]},{id:\"minecraft:interaction\",width:2f,height:-1.5f,response:1b,Tags:[tower,tower.hitbox]},{id:\"minecraft:marker\",Tags:[tower,bleed-tower,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,bleed-tower,tower.core]}"); }

        // function tower:spawn/model/supporter/lv0
        // -> tower:spawn/model/supporter/lv0
        tdpack.buckets.Bucket10._m_ac8c6cbc_tower_spawn_model_supporter_lv0_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc218 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc218.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }
        return 0;
    }

    public static void _m_ff23f7ce_tower_spawn_model_bleed_tower_lv1_execute(ServerCommandSource source) {
        _m_ff23f7ce_tower_spawn_model_bleed_tower_lv1_executeReturn(source);
    }

    public static int _m_ff23f7ce_tower_spawn_model_bleed_tower_lv1_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv1
        // -> tower:spawn/model/supporter/lv1
        tdpack.buckets.Bucket10._m_c2f15088_tower_spawn_model_supporter_lv1_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc219 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc219.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }

        // kill @e[distance=..1,tag=tower.head,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.head"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon item_display ~ ~ ~ {Tags:[tower,tower.head],item:{id:"minecraft:stone_sword"},item_display:"none",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "item_display", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.head],item:{id:\"minecraft:stone_sword\"},item_display:\"none\",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}"); }

        // execute as @e[tag=tower.head,distance=..1,type=item_display] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.head") && KfcGen.inRange(source.getPosition(), en, -1, 1))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc220 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc220.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }

    public static void _m_8f5578bf_tower_spawn_model_bleed_tower_lv2_execute(ServerCommandSource source) {
        _m_8f5578bf_tower_spawn_model_bleed_tower_lv2_executeReturn(source);
    }

    public static int _m_8f5578bf_tower_spawn_model_bleed_tower_lv2_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv2
        // -> tower:spawn/model/supporter/lv2
        tdpack.buckets.Bucket10._m_083f103b_tower_spawn_model_supporter_lv2_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc221 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc221.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }

        // kill @e[distance=..1,tag=tower.head,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.head"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon item_display ~ ~ ~ {Tags:[tower,tower.head],item:{id:"minecraft:iron_sword"},item_display:"none",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "item_display", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.head],item:{id:\"minecraft:iron_sword\"},item_display:\"none\",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}"); }

        // execute as @e[tag=tower.head,distance=..1,type=item_display] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.head") && KfcGen.inRange(source.getPosition(), en, -1, 1))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc222 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc222.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }

    public static void _m_a8d13ab2_tower_spawn_model_bleed_tower_lv3_execute(ServerCommandSource source) {
        _m_a8d13ab2_tower_spawn_model_bleed_tower_lv3_executeReturn(source);
    }

    public static int _m_a8d13ab2_tower_spawn_model_bleed_tower_lv3_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv3
        // -> tower:spawn/model/supporter/lv3
        tdpack.buckets.Bucket10._m_d95fbe34_tower_spawn_model_supporter_lv3_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc223 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc223.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }

        // kill @e[distance=..1,tag=tower.head,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.head"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon item_display ~ ~ ~ {Tags:[tower,tower.head],item:{id:"minecraft:golden_sword"},item_display:"none",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "item_display", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.head],item:{id:\"minecraft:golden_sword\"},item_display:\"none\",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}"); }

        // execute as @e[tag=tower.head,distance=..1,type=item_display] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.head") && KfcGen.inRange(source.getPosition(), en, -1, 1))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc224 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc224.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }

    public static void _m_a265f7bc_tower_spawn_model_bleed_tower_lv4_execute(ServerCommandSource source) {
        _m_a265f7bc_tower_spawn_model_bleed_tower_lv4_executeReturn(source);
    }

    public static int _m_a265f7bc_tower_spawn_model_bleed_tower_lv4_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv4
        // -> tower:spawn/model/supporter/lv4
        tdpack.buckets.Bucket10._m_b0cdf714_tower_spawn_model_supporter_lv4_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc225 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc225.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }

        // kill @e[distance=..1,tag=tower.head,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.head"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon item_display ~ ~ ~ {Tags:[tower,tower.head],item:{id:"minecraft:diamond_sword"},item_display:"none",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "item_display", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.head],item:{id:\"minecraft:diamond_sword\"},item_display:\"none\",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}"); }

        // execute as @e[tag=tower.head,distance=..1,type=item_display] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.head") && KfcGen.inRange(source.getPosition(), en, -1, 1))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc226 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc226.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }

    public static void _m_ace6c905_tower_spawn_model_bleed_tower_lv5_execute(ServerCommandSource source) {
        _m_ace6c905_tower_spawn_model_bleed_tower_lv5_executeReturn(source);
    }

    public static int _m_ace6c905_tower_spawn_model_bleed_tower_lv5_executeReturn(ServerCommandSource source) {
        Entity executor = source.getEntity();
        KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);
        ServerScoreboard sb = ctx.scoreboard;
        // kill @e[distance=..1,tag=tower.supporter,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // function tower:spawn/model/supporter/lv5
        // -> tower:spawn/model/supporter/lv5
        tdpack.buckets.Bucket10._m_c0a85629_tower_spawn_model_supporter_lv5_execute(source);

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
        java.util.List<net.minecraft.entity.Entity> _eset0 = KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1);
        for (net.minecraft.entity.Entity _vE1 : _eset0) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = (int)((KfcGen.entityGetDouble(_vE1, "transformation.translation.[1]")) * 1000);
            if (_vE1 != null) KfcGen.setScore(sb, (_vE1 == null ? "<no-executor>" : _vE1.getNameForScoreboard()), "tower.model.y_sync", (int)(_stv));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) KfcGen.addScore(sb, e.getNameForScoreboard(), "tower.model.y_sync", -(1500));
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
        for (net.minecraft.entity.Entity _vE1 : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            ServerCommandSource _vS1 = source.withEntity(_vE1);
            int _stv = KfcGen.getScore(sb, (_vE1 == null ? "<no-_vE1>" : _vE1.getNameForScoreboard()), "tower.model.y_sync");
            { net.minecraft.entity.Entity _se = _vE1; if (_se != null) KfcGen.entityPutNumberPath(_se, "transformation.translation.[1]", "float", (_stv) * 0.001); }
        }

        // kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.BLOCK_DISPLAY}, new String[]{"tower.supporter", "not-allocated"}, new String[0], -1, -1)) {
            KfcGen.killEntity(_k);
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc227 = es.withPosition(new net.minecraft.util.math.Vec3d(es.getPosition().x, (es.getPosition().y + 1.5), es.getPosition().z));
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc227.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }

        // execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.supporter") && en.getCommandTags().contains("not-allocated"))) {
            ServerCommandSource es = source.withEntity(e);
            if (e != null) e.removeCommandTag("not-allocated");
        }

        // kill @e[distance=..1,tag=tower.head,type=item_display]
        for (net.minecraft.entity.Entity _k : KfcGen.allEntities(ctx, source.getPosition(), new net.minecraft.entity.EntityType<?>[]{EntityType.ITEM_DISPLAY}, new String[]{"tower.head"}, new String[0], -1, 1)) {
            KfcGen.killEntity(_k);
        }

        // summon item_display ~ ~ ~ {Tags:[tower,tower.head],item:{id:"minecraft:netherite_sword"},item_display:"none",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}
        { net.minecraft.util.math.Vec3d _sp = new net.minecraft.util.math.Vec3d(source.getPosition().x, source.getPosition().y, source.getPosition().z); KfcGen.summon(ctx.world, "item_display", _sp.x, _sp.y, _sp.z, "{Tags:[tower,tower.head],item:{id:\"minecraft:netherite_sword\"},item_display:\"none\",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]}"); }

        // execute as @e[tag=tower.head,distance=..1,type=item_display] at @s run ride @s mount @n[tag=tower.core]
        for (Entity e : ctx.world.getEntitiesByType(EntityType.ITEM_DISPLAY,
                en -> en.getCommandTags().contains("tower.head") && KfcGen.inRange(source.getPosition(), en, -1, 1))) {
            ServerCommandSource es = source.withEntity(e);
            ServerCommandSource kfcSrc228 = (e != null ? es.withPosition(e.getPos()).withRotation(new net.minecraft.util.math.Vec2f(e.getPitch(), e.getYaw())) : es);
            { net.minecraft.entity.Entity _t = KfcGen.nearestEntityAnyTypeWhere(ctx, kfcSrc228.getPosition(), new String[]{"tower.core"}, new String[0], -1, -1, _ee -> (true));
              if (_t != null) if (e != null) { if (e != null && _t != null && e.getVehicle() != _t) { e.stopRiding(); e.startRiding(_t, true); } } }
        }
        return 0;
    }
}
