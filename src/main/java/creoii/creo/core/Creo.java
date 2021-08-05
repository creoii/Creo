package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.util.DefaultCreator;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		AttributeRegistry.register();

		DefaultCreator.createArmorMaterial("test", 0, new int[]{}, 1, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, Ingredient::empty);
		DefaultCreator.createBoatType(new Identifier(MOD_ID, "test"), Blocks.DIAMOND_BLOCK);
		DefaultCreator.createDyeColor(new Identifier(MOD_ID, "test"), 0, 0, MapColor.BLACK, 0, 0);
		DefaultCreator.createEnchantmentRarity("test", 1);
		DefaultCreator.createItemRarity("test", Formatting.AQUA);
		DefaultCreator.createToolMaterial("test", 1, 2, 0.0F, 0.0F, 3, Ingredient::empty);

		System.out.println("ARMOR MATERIALS");
		for (ArmorMaterials material : ArmorMaterials.values()) {
			System.out.println(material.getName());
		}
		System.out.println("BOAT TYPES");
		for (BoatEntity.Type type : BoatEntity.Type.values()) {
			System.out.println(type.getName());
		}
		System.out.println("DYE COLORS");
		for (DyeColor color : DyeColor.values()) {
			System.out.println(color.getName());
		}
		System.out.println("ENCHANTMENT RARITIES");
		for (Enchantment.Rarity rarity : Enchantment.Rarity.values()) {
			System.out.println(rarity.name());
		}
		System.out.println("ITEM RARITIES");
		for (Rarity rarity : Rarity.values()) {
			System.out.println(rarity.name());
		}
		System.out.println("TOOL MATERIALS");
		for (ToolMaterials material : ToolMaterials.values()) {
			System.out.println(material.name());
		}

		//Registry.register(Registry.ITEM, "test_boat", new BoatItem(BoatEntity.Type.valueOf("TEST"), new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}

	public static Biome createJungleRiver() {
		SpawnSettings.Builder builder = (new SpawnSettings.Builder()).spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 2, 1, 4)).spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.SALMON, 5, 1, 5));
		DefaultBiomeFeatures.addBatsAndMonsters(builder);
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.DROWNED, 100, 1, 1));
		net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
		builder2.structureFeature(ConfiguredStructureFeatures.MINESHAFT);
		builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
		DefaultBiomeFeatures.addLandCarvers(builder2);
		DefaultBiomeFeatures.addDefaultLakes(builder2);
		DefaultBiomeFeatures.addAmethystGeodes(builder2);
		DefaultBiomeFeatures.addDungeons(builder2);
		DefaultBiomeFeatures.addMineables(builder2);
		DefaultBiomeFeatures.addDefaultOres(builder2);
		DefaultBiomeFeatures.addDefaultDisks(builder2);
		DefaultBiomeFeatures.addWaterBiomeOakTrees(builder2);
		DefaultBiomeFeatures.addDefaultFlowers(builder2);
		DefaultBiomeFeatures.addDefaultGrass(builder2);
		DefaultBiomeFeatures.addDefaultMushrooms(builder2);
		DefaultBiomeFeatures.addDefaultVegetation(builder2);
		DefaultBiomeFeatures.addSprings(builder2);
		builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SEAGRASS_SWAMP);
		builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.BAMBOO);
		DefaultBiomeFeatures.addFrozenTopLayer(builder2);
		return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.RIVER).depth(-0.5F).scale(0.0F).temperature(0.95F).downfall(0.9F).effects((new BiomeEffects.Builder()).waterColor(4445678).waterFogColor(270131).fogColor(12638463).skyColor(8103167).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
	}
}
