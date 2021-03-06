package creoii.creo.core.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class RegistryUtil {
    public static <B extends Block> Block createBlock(Identifier id, B block, @Nullable ItemGroup group) {
        Registry.register(Registry.BLOCK, id, block);
        if (group != null) Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }

    public static RegistryKey<Biome> createBiome(Identifier id, Biome biome) {
        Registry.register(BuiltinRegistries.BIOME, id, biome);
        return RegistryKey.of(Registry.BIOME_KEY, id);
    }
}