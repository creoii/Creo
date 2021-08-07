package creoii.creo.core.mixin.enchantment;

import creoii.creo.core.util.EnchantmentUtil;
import creoii.creo.core.util.tags.EnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void creo$applyTreasures(CallbackInfoReturnable<Boolean> cir) {
        if (!EnchantmentTags.TREASURE.values().isEmpty()) cir.setReturnValue(EnchantmentUtil.isIn((Enchantment) (Object) this, EnchantmentTags.TREASURE));
    }

    //@Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void creo$applyCurses(CallbackInfoReturnable<Boolean> cir) {
        if (!EnchantmentTags.CURSED.values().isEmpty()) cir.setReturnValue(EnchantmentUtil.isIn((Enchantment) (Object) this, EnchantmentTags.CURSED));
    }
}