package creoii.creo.core.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.function.ToIntFunction;

public class BlockUtil {
    public static void partiallyBreakBlock(World world, BlockPos pos, int progress) {

    }

    public static void setHardness(Block block, float hardness) {
        block.settings.hardness(hardness);
    }

    public static void setResistance(Block block, float resistance) {
        block.settings.resistance(resistance);
    }

    public static void setStrength(Block block, float hardness, float resistance) {
        block.settings.hardness(hardness).resistance(resistance);
    }

    public static void setSlipperiness(Block block, float slipperiness) {
        block.settings.slipperiness(slipperiness);
    }

    public static void setVelocity(Block block, float multiplier) {
        block.settings.velocityMultiplier(multiplier);
    }

    public static void setJumpVelocity(Block block, float multiplier) {
        block.settings.jumpVelocityMultiplier(multiplier);
    }

    public static void setSounds(Block block, BlockSoundGroup soundGroup) {
        block.settings.sounds(soundGroup);
    }

    public static void setLuminance(Block block, int luminance) {
        block.settings.luminance((state) -> luminance);
    }

    public static void setMapColor(Block block, MapColor color) {
        block.settings.mapColor(color);
    }

    public static void setEmissive(Block block, boolean emissive) {
        block.settings.emissiveLighting((state, world, pos) -> emissive);
    }

    public static void setSuffocates(Block block, boolean suffocates) {
        block.settings.suffocates((state, world, pos) -> suffocates);
    }

    public static void setAllowsSpawning(Block block, boolean allows) {
        block.settings.allowsSpawning((state, world, pos, type) -> allows);
    }

    public static void setDropsLike(Block block, Block other) {
        block.settings.dropsLike(other);
    }

    public static void setNoDrops(Block block) {
        block.settings.dropsNothing();
    }

    public static void setNoCollision(Block block) {
        block.settings.noCollision();
    }
}