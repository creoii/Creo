package creoii.creo.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class BlockUtil {
    public static Map<Block, OverrideBlockSettings> BLOCK_SETTINGS_REPLACED = new HashMap<>();

    public static void partiallyBreakBlock(World world, BlockPos pos, int progress) { }

    public static OverrideBlockSettings getOrDefaultSettings(Block block) {
        if (BLOCK_SETTINGS_REPLACED.containsKey(block)) return BLOCK_SETTINGS_REPLACED.get(block);
        else {
            BLOCK_SETTINGS_REPLACED.put(block, OverrideBlockSettings.toOverrideSettings(block));
            return OverrideBlockSettings.toOverrideSettings(block);
        }
    }

    public static void setHardness(Block block, float hardness) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).hardness(hardness));
    }

    public static float getHardness(Block block) {
        return getOrDefaultSettings(block).hardness;
    }

    public static void setResistance(Block block, float resistance) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).resistance(resistance));
    }

    public static float getResistance(Block block) {
        return getOrDefaultSettings(block).resistance;
    }

    public static void setStrength(Block block, float hardness, float resistance) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).hardness(hardness).resistance(resistance));
    }

    public static void setSlipperiness(Block block, float slipperiness) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).slipperiness(slipperiness));
    }

    public static float getSlipperiness(Block block) {
        return getOrDefaultSettings(block).slipperiness;
    }

    public static void setBounciness(Block block, float bounciness) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).bounciness(bounciness));
    }

    public static float getBounciness(Block block) {
        return getOrDefaultSettings(block).bounciness;
    }

    public static void setVelocityMultiplier(Block block, float multiplier) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).velocityMultiplier(multiplier));
    }

    public static float getVelocityMultiplier(Block block) {
        return getOrDefaultSettings(block).velocityMultiplier;
    }

    public static void setJumpVelocityMultiplier(Block block, float multiplier) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).jumpVelocityMultiplier(multiplier));
    }

    public static float getJumpVelocityMultiplier(Block block) {
        return getOrDefaultSettings(block).jumpVelocityMultiplier;
    }

    public static void setSlideVelocity(Block block, float value) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).slideVelocity(value));
    }

    public static float getSlideVelocity(Block block) {
        return getOrDefaultSettings(block).slideVelocity;
    }

    public static void setSoundGroup(Block block, BlockSoundGroup soundGroup) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).sounds(soundGroup));
    }

    public static BlockSoundGroup getSoundGroup(Block block) {
        return getOrDefaultSettings(block).soundGroup;
    }

    public static void setLuminance(Block block, int luminance) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).luminance(luminance));
    }

    public static int getLuminance(Block block) {
        return getOrDefaultSettings(block).luminance;
    }

    public static boolean hasLuminance(Block block) {
        return getLuminance(block) > 0;
    }

    public static void setMapColor(Block block, MapColor color) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).mapColor(color));
    }

    public static MapColor getMapColor(Block block) {
        return getOrDefaultSettings(block).mapColor;
    }

    public static void setEmissive(Block block, boolean emissive) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).emissiveLighting(emissive));
    }

    public static boolean isEmissive(Block block) {
        return getOrDefaultSettings(block).emissiveLighting;
    }

    public static void setSuffocates(Block block, boolean suffocates) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).suffocates(suffocates));
    }

    public static boolean doesSuffocate(Block block) {
        return getOrDefaultSettings(block).suffocation;
    }

    public static void setAllowsSpawning(Block block, boolean allows) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).allowsSpawning(allows));
    }

    public static boolean allowsSpawning(Block block) {
        return getOrDefaultSettings(block).allowsSpawning;
    }

    public static void setNoCollision(Block block) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).noCollision());
    }

    public static boolean hasNoCollision(Block block) {
        return !getOrDefaultSettings(block).collidable;
    }
}