package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.util.DefaultCreator;
import creoii.creo.core.util.tags.BiomeTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		AttributeRegistry.register();
		BiomeTags.load();

		DefaultCreator.createBoatType(new Identifier(MOD_ID, "test"), Blocks.DIAMOND_BLOCK);
		for (BoatEntity.Type type : BoatEntity.Type.values()) {
			System.out.println(type.getName());
		}
		//Registry.register(Registry.ITEM, "test_boat", new BoatItem(BoatEntity.Type.valueOf("TEST"), new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}
}
