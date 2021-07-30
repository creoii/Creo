package creoii.creo.core.util;

import net.minecraft.util.math.BlockPos;

import java.util.function.BiConsumer;

public class WorldGenUtil {
    public static void forEachInRange(BlockPos first, BlockPos second, BiConsumer<BlockPos, BlockPos> action) {
        for (int z = first.getZ(); z <= second.getZ(); ++z) {
            for (int y = first.getY(); y <= second.getY(); ++y) {
                for (int x = first.getX(); x <= second.getX(); ++x) {
                    action.accept(first, second);
                }
            }
        }
    }
}