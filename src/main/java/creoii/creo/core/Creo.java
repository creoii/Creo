package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class Creo implements ModInitializer, ClientModInitializer {
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
}