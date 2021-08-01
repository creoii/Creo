package creoii.creo.core.mixin.entity;

import creoii.creo.core.registry.AttributeRegistry;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    private PlayerEntity player;

    @ModifyConstant(method = "add", constant = @Constant(intValue = 20))
    private int creo$addMinHunger(int i) {
        return player != null && player.getAttributes() != null ? (int) player.getAttributeValue(AttributeRegistry.PLAYER_MAX_HUNGER) : 20;
    }

    @Inject(method = "getFoodLevel", at = @At("RETURN"), cancellable = true)
    private void creo$getMaxHunger(CallbackInfoReturnable<Integer> cir) {
        if (player != null) cir.setReturnValue(player.getAttributes() != null ? (int) player.getAttributeValue(AttributeRegistry.PLAYER_MAX_HUNGER) : 20);
    }

    @Inject(method = "update", at = @At("HEAD"))
    private void creo$applyMaxHunger(PlayerEntity player, CallbackInfo ci) {
        this.player = player;
    }
}