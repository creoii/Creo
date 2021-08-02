package creoii.creo.core.mixin.block;

import creoii.creo.core.util.BlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {
    @Redirect(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean creo$applyEnchantBoostParticles(BlockState state, Block block) {
        return state.isIn(BlockTags.BOOSTS_ENCHANTS);
    }
}