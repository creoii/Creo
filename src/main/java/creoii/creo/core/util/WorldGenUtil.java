package creoii.creo.core.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;
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

    public static List<BlockPos> getTopPositions(World world, int x, int z, boolean includeWater) {
        List<BlockPos> positions = ImmutableList.of();
        BlockPos.Mutable mutable = new BlockPos(x, -64, z).mutableCopy();
        for (int y = -64; y <= world.getHeight(); ++y) {
            mutable.setY(y);
            if (world.getBlockState(mutable.up()).isAir() || (world.getBlockState(mutable.up()).isOf(Blocks.WATER) && !world.getBlockState(mutable).isOf(Blocks.WATER))) {
                positions.add(mutable);
            }
        }

        return positions;
    }

    public static Biome getBiomeFromKey(RegistryKey<Biome> key) {
        return BuiltinRegistries.BIOME.get(key);
    }
}