package creoii.creo.core.mixin;

import creoii.creo.core.registry.AttributeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
    private void creo$applyReachDistance(CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = client.player;
        if (player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE) > 4.5F || (player.isCreative() && player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE) > 5.0F)) {
            cir.setReturnValue(((float) player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE)));
        }
    }
}