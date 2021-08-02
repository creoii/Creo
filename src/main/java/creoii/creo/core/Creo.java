package creoii.creo.core;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.registry.EntityRegistry;
import creoii.creo.core.util.BlockUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public class Creo implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "creo";

	@Override
	public void onInitialize() {
		EntityRegistry.register();
		AttributeRegistry.register();

		BlockUtil.setDropsLike(Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK);
		BlockUtil.setStrength(Blocks.EMERALD_BLOCK, 1000.0F, 100.0F);
		BlockUtil.setEmissive(Blocks.EMERALD_BLOCK, true);
		BlockUtil.setLuminance(Blocks.EMERALD_BLOCK, 15);
		BlockUtil.setMapColor(Blocks.EMERALD_BLOCK, MapColor.BLACK);
		BlockUtil.setNoCollision(Blocks.EMERALD_BLOCK);
		BlockUtil.setNoDrops(Blocks.DIAMOND_BLOCK);
		BlockUtil.setVelocity(Blocks.DIAMOND_BLOCK, 1.5F);
		BlockUtil.setJumpVelocity(Blocks.EMERALD_BLOCK, 1.5F);
		BlockUtil.setSlipperiness(Blocks.EMERALD_BLOCK, 0.5F);
		BlockUtil.setSounds(Blocks.EMERALD_BLOCK, BlockSoundGroup.WOOD);
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.registerClient();
	}
}
