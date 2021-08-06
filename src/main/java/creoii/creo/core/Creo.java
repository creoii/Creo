package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.util.BiomeUtil;
import creoii.creo.core.util.tags.BiomeTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class Creo implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		AttributeRegistry.register();
		//Registry.register(Registry.ITEM, "test_boat", new BoatItem(BoatEntity.Type.valueOf("TEST"), new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}

	@Override
	public void onInitializeServer() {
		BiomeTags.INFESTED.values().forEach((biome -> {
			System.out.println(BiomeUtil.toKey(biome).getValue());
			biome.getGenerationSettings().getFeatures().get(7).add(() -> ConfiguredFeatures.ORE_INFESTED);
		}));
	}
}
