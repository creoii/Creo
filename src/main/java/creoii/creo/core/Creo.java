package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.registry.RegistryCreators;
import creoii.creo.core.util.tags.BiomeTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		AttributeRegistry.register();

		RegistryCreators.createItemRarity("test", Formatting.BOLD);

		System.out.println("ITEM RARITIES");
		for (Rarity rarity : Rarity.values()) {
			System.out.println(rarity.name());
		}

		BiomeTags.INFESTED.values().forEach((biome -> {
			biome.getGenerationSettings().getFeatures().get(7).add(() -> ConfiguredFeatures.ORE_INFESTED);
		}));

		//Registry.register(Registry.ITEM, "test_boat", new BoatItem(BoatEntity.Type.valueOf("TEST"), new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}
}
