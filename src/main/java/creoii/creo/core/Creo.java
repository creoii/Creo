package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.util.BiomeUtil;
import creoii.creo.core.util.BlockUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		AttributeRegistry.register();

		BlockUtil.setBounciness(Blocks.STONE, 1.2F);
		BiomeUtil.addBeachToBiome(BuiltinRegistries.BIOME.getKey(BuiltinRegistries.BIOME.get(37)).get(), new BiomeUtil.BiomeBeach(BuiltinRegistries.BIOME.getKey(BuiltinRegistries.BIOME.get(168)).get(), 1));
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}
}
