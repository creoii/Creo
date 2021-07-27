package creoii.creo.core.mixin;

import creoii.creo.core.registry.AttributeRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerMixin {
    @Shadow @Final protected ServerPlayerEntity player;

    @ModifyConstant(method = "processBlockBreakingAction(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/network/packet/c2s/play/PlayerActionC2SPacket$Action;Lnet/minecraft/util/math/Direction;I)V", constant = @Constant(doubleValue = 36.0))
    private double getActualReachDistance(final double reachDistance) {
        double d = player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE);
        return d * d;
    }
}