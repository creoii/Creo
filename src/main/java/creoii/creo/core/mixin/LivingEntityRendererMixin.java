package creoii.creo.core.mixin;

import creoii.creo.core.registry.AttributeRegistry;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity> {
    @Inject(method = "setupTransforms", at = @At("HEAD"))
    private void creo$flipEntityRender(T entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, CallbackInfo ci) {
        if (entity.hasCustomName() || entity instanceof PlayerEntity) {
            if (entity.getAttributeValue(AttributeRegistry.GENERIC_GRAVITY) < 0.0D && (!(entity instanceof PlayerEntity) || ((PlayerEntity)entity).isPartVisible(PlayerModelPart.CAPE))) {
                matrices.translate(0.0D, entity.getHeight() + 0.1D, 0.0D);
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));
            }
        }
    }
}