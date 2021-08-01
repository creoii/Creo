package creoii.creo.core.util;

import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    private static double MINECART_SPEED = 2.0D;
    private static float BOAT_SPEED = 0.05F;
    private static int BLOCK_BREAKING_COOLDOWN = 5;

    private static final Map<BlockPos, Integer> BREAKING_PROGRESSES = new HashMap<>();

    public static void setMinecartSpeed(double speed) {
        MINECART_SPEED = speed;
    }

    public static double getMinecartSpeed() {
        return MINECART_SPEED;
    }

    public static void setBoatSpeed(float boatSpeed) {
        BOAT_SPEED = boatSpeed;
    }

    public static float getBoatSpeed() {
        return BOAT_SPEED;
    }

    public static void setBlockBreakingCooldown(int blockBreakingCooldown) {
        BLOCK_BREAKING_COOLDOWN = blockBreakingCooldown;
    }

    public static int getBlockBreakingCooldown() {
        return BLOCK_BREAKING_COOLDOWN;
    }

    public static void setBreakingProgress(BlockPos pos, int progress) {
        BREAKING_PROGRESSES.put(pos, progress);
    }

    public static int getBreakingProgress(BlockPos pos) {
        return BREAKING_PROGRESSES.getOrDefault(pos, -1);
    }

    public static void removeBreakingProgress(BlockPos pos) {
        BREAKING_PROGRESSES.remove(pos);
    }
}