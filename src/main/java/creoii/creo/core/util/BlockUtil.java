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

    public static void setResistance(Block block, float resistance) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).resistance(resistance));
    }

    public static void setStrength(Block block, float hardness, float resistance) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).hardness(hardness).resistance(resistance));
    }

    public static void setSlipperiness(Block block, float slipperiness) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).slipperiness(slipperiness));
    }

    public static void setBounciness(Block block, float bounciness) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).bounciness(bounciness));
    }

    public static void setVelocity(Block block, float multiplier) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).velocityMultiplier(multiplier));
    }

    public static void setJumpVelocity(Block block, float multiplier) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).jumpVelocityMultiplier(multiplier));
    }

    public static void setSlideVelocity(Block block, float value) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).slideVelocity(value));
    }

    public static void setSounds(Block block, BlockSoundGroup soundGroup) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).sounds(soundGroup));
    }

    public static void setLuminance(Block block, int luminance) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).luminance(luminance));
    }

    public static void setMapColor(Block block, MapColor color) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).mapColor(color));
    }

    public static void setEmissive(Block block, boolean emissive) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).emissiveLighting(emissive));
    }

    public static void setSuffocates(Block block, boolean suffocates) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).suffocates(suffocates));
    }

    public static void setAllowsSpawning(Block block, boolean allows) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).allowsSpawning(allows));
    }

    public static void setNoCollision(Block block) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).noCollision());
    }
}