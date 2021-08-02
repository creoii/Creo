package creoii.creo.core.mixin.entity;

import creoii.creo.core.util.ItemTags;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public class ItemFrameEntityMixin {
    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void creo$stopUnframeables(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (player.getStackInHand(hand).isIn(ItemTags.UNFRAMEABLE)) cir.setReturnValue(ActionResult.PASS);
    }
}