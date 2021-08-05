package creoii.creo.core.mixin.screen;

import creoii.creo.core.util.tags.BlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {
    @Redirect(method = "onContentChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$AbstractBlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean creo$applyEnchantBoosters(AbstractBlock.AbstractBlockState state, Block block) {
        return state.isIn(BlockTags.BOOSTS_ENCHANTS);
    }
}