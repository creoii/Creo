package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.util.BiomeTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.minecraft.world.biome.BiomeKeys;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		AttributeRegistry.register();
		BiomeTags.load();

		OverworldBiomes.addBiomeVariant(BiomeKeys.WARM_OCEAN, BiomeKeys.BAMBOO_JUNGLE, 0.75D);
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}
}
