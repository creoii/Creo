package creoii.creo.core.mixin.item;

import creoii.creo.core.util.tags.BlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.EndCrystalItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndCrystalItem.class)
public class EndCrystalItemMixin {
    //@Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$AbstractBlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean creo$applyEndCrystalBaseBlocks(AbstractBlock.AbstractBlockState state, Block block) {
        return state.isIn(BlockTags.END_CRYSTAL_BASE_BLOCKS);
    }
}