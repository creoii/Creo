package creoii.creo.core.mixin.item;

import creoii.creo.core.util.tags.ItemTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow public abstract boolean hasGlint(ItemStack stack);

    @Inject(method = "hasGlint", at = @At("RETURN"), cancellable = true)
    private void creo$applyGlints(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(stack.isIn(ItemTags.GLINTED) || hasGlint(stack));
    }

    //@Inject(method = "getRarity", at = @At("RETURN"), cancellable = true)
    private void test(ItemStack stack, CallbackInfoReturnable<Rarity> cir) {
        cir.setReturnValue(Rarity.valueOf("TEST"));
    }
}