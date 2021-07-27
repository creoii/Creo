package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		AttributeRegistry.register();
	}

	@Override
	public void onInitializeClient() {
	}
}
