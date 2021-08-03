package creoii.creo.core.mixin.screen;

import creoii.creo.core.util.BlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {
    //@Redirect(method = "onContentChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean creo$applyEnchantBoosters(BlockState state, Block block) {
        return state.isIn(BlockTags.BOOSTS_ENCHANTS);
    }
}