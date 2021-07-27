package creoii.creo.core;

import creoii.creo.common.entity.BoatEntity;
import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.util.DefaultCreator;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		DefaultCreator.createBoat(new BoatEntity.Boat("test", Blocks.DIAMOND_BLOCK, Items.DIAMOND));
		AttributeRegistry.register();
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}
}
