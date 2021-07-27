package creoii.creo.core.mixin;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.util.Tags;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public double getAttributeValue(EntityAttribute attribute) { return 0.0D; }
    @Shadow public EntityDimensions getDimensions(EntityPose pose) { return null; }

    private LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "createLivingAttributes", at = @At("HEAD"), cancellable = true)
    private static void creo$createNewAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(DefaultAttributeContainer.builder().add(EntityAttributes.GENERIC_MAX_HEALTH).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).add(EntityAttributes.GENERIC_MOVEMENT_SPEED).add(EntityAttributes.GENERIC_ARMOR).add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).add(AttributeRegistry.GENERIC_GRAVITY).add(AttributeRegistry.GENERIC_SWIM_SPEED).add(AttributeRegistry.GENERIC_REACH_DISTANCE));
    }

    @Inject(method = "knockDownwards", at = @At("HEAD"), cancellable = true)
    private void creo$applyKnockbackSwimSpeed(CallbackInfo ci) {
        this.setVelocity(this.getVelocity().add(0.0D, -0.03999999910593033D * this.getAttributeValue(AttributeRegistry.GENERIC_SWIM_SPEED), 0.0D));
        ci.cancel();
    }

    @Inject(method = "swimUpward", at = @At("HEAD"), cancellable = true)
    private void creo$applyUpwardSwimSpeed(Tag<Fluid> fluid, CallbackInfo ci) {
        this.setVelocity(this.getVelocity().add(0.0D, 0.03999999910593033D * this.getAttributeValue(AttributeRegistry.GENERIC_SWIM_SPEED), 0.0D));
        ci.cancel();
    }

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"))
    private void creo$applySwimSpeed(LivingEntity entity, float speed, Vec3d movementInput) {
        speed *= this.getAttributeValue(AttributeRegistry.GENERIC_SWIM_SPEED);
        this.updateVelocity(speed, movementInput);
    }

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;method_26317(DZLnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d creo$applyGravity(LivingEntity entity, double d, boolean bl, Vec3d vec3d) {
        return entity.method_26317(entity.getAttributeValue(AttributeRegistry.GENERIC_GRAVITY), bl, vec3d);
    }

    @Inject(method = "isInsideWall", at = @At("HEAD"), cancellable = true)
    private void creo$vexInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if (this.getType() == EntityType.VEX) {
            float f = this.getType().getDimensions().width * 0.8F;
            Box box = Box.of(this.getEyePos(), f, 1.0E-6D, f);
            cir.setReturnValue(this.world.getBlockCollisions(this, box, (state, pos) -> state.isIn(Tags.Blocks.BLOCKS_VEX)).findAny().isPresent());
        }
    }
}