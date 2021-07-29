package creoii.creo.core.mixin;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.util.BlockTags;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public double getAttributeValue(EntityAttribute attribute) { return 0.0D; }
    @Shadow public EntityDimensions getDimensions(EntityPose pose) { return null; }
    @Shadow @Nullable public EntityAttributeInstance getAttributeInstance(EntityAttribute attribute) { return null; }
    @Shadow public boolean hasStatusEffect(StatusEffect effect) { return false; }
    @Shadow public float getHealth() { return 0.0F; }
    @Shadow public float getMaxHealth() { return 0.0F; }
    @Shadow public void heal(float amount) { }

    private static final UUID SLOW_FALLING_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    private static final EntityAttributeModifier SLOW_FALLING = new EntityAttributeModifier(SLOW_FALLING_ID, "Slow falling acceleration reduction", -0.07, EntityAttributeModifier.Operation.ADDITION);
    private static EntityAttributeInstance gravity;

    private LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void creo$createNewAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(AttributeRegistry.GENERIC_GRAVITY).add(AttributeRegistry.GENERIC_SWIM_SPEED).add(AttributeRegistry.GENERIC_REACH_DISTANCE).add(AttributeRegistry.GENERIC_NATURAL_REGENERATION);
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

    @ModifyVariable(method = "travel", at = @At("STORE"), ordinal = 0)
    private double creo$modifyGravityVariable(double d) {
        gravity = this.getAttributeInstance(AttributeRegistry.GENERIC_GRAVITY);
        if (this.hasStatusEffect(StatusEffects.SLOW_FALLING) && !gravity.hasModifier(SLOW_FALLING)) gravity.addTemporaryModifier(SLOW_FALLING);
        return gravity.getValue();
    }

    @Inject(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;", shift = At.Shift.BEFORE))
    private void creo$removeSlowFallingModifier(Vec3d movementInput, CallbackInfo ci) {
        if (gravity.hasModifier(SLOW_FALLING)) gravity.removeModifier(SLOW_FALLING);
    }

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void creo$applyNaturalRegeneration(CallbackInfo ci) {
        float amount = (float) this.getAttributeValue(AttributeRegistry.GENERIC_NATURAL_REGENERATION);
        if (this.world.getGameRules().getBoolean(GameRules.NATURAL_REGENERATION) && amount > 0.0F) {
            if (this.getHealth() < this.getMaxHealth() && this.age % 20 == 0) {
                this.heal(amount);
            }
        }
    }

    @Inject(method = "isInsideWall", at = @At("HEAD"), cancellable = true)
    private void creo$vexInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if (this.getType() == EntityType.VEX) {
            float f = this.getType().getDimensions().width * 0.8F;
            cir.setReturnValue(this.world.getBlockCollisions(this, Box.of(this.getEyePos(), f, 1.0E-6D, f), (state, pos) -> state.isIn(BlockTags.BLOCKS_VEX)).findAny().isPresent());
        }
    }
}