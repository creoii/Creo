package creoii.creo.core.mixin.entity;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.util.BlockTags;
import creoii.creo.core.util.EntityTypeTags;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
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
    @Shadow protected int computeFallDamage(float fallDistance, float damageMultiplier) { return 0; }
    @Shadow public abstract AttributeContainer getAttributes();

    private static final UUID SLOW_FALLING_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    private static final EntityAttributeModifier SLOW_FALLING = new EntityAttributeModifier(SLOW_FALLING_ID, "Slow falling acceleration reduction", -0.07, EntityAttributeModifier.Operation.ADDITION);
    private static EntityAttributeInstance gravity;

    private LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void creo$createNewAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(AttributeRegistry.GENERIC_GRAVITY).add(AttributeRegistry.GENERIC_SWIM_SPEED).add(AttributeRegistry.GENERIC_REACH_DISTANCE).add(AttributeRegistry.GENERIC_NATURAL_REGENERATION).add(AttributeRegistry.GENERIC_MAX_AIR);
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

    @Redirect(method = "handleFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;computeFallDamage(FF)I"))
    private int creo$handleFallDamageGravity(LivingEntity entity, float fallDistance, float damageMultiplier) {
        return this.computeFallDamage(fallDistance * (float) (entity.getAttributeValue(AttributeRegistry.GENERIC_GRAVITY) * 12.5F), damageMultiplier);
    }

    @Override
    public void onKilledOther(ServerWorld world, LivingEntity other) {
        super.onKilledOther(world, other);
        if (this.getType().isIn(EntityTypeTags.ZOMBIES)) {
            if ((world.getDifficulty() == Difficulty.NORMAL || world.getDifficulty() == Difficulty.HARD) && other instanceof VillagerEntity villagerEntity) {
                if (world.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
                    return;
                }

                ZombieVillagerEntity zombieVillagerEntity = villagerEntity.convertTo(EntityType.ZOMBIE_VILLAGER, false);
                zombieVillagerEntity.initialize(world, world.getLocalDifficulty(zombieVillagerEntity.getBlockPos()), SpawnReason.CONVERSION, new ZombieEntity.ZombieData(false, true), null);
                zombieVillagerEntity.setVillagerData(villagerEntity.getVillagerData());
                zombieVillagerEntity.setGossipData(villagerEntity.getGossip().serialize(NbtOps.INSTANCE).getValue());
                zombieVillagerEntity.setOfferData(villagerEntity.getOffers().toNbt());
                zombieVillagerEntity.setXp(villagerEntity.getExperience());
                if (!this.isSilent()) {
                    world.syncWorldEvent(null, 1026, this.getBlockPos(), 0);
                }
            }
        }
    }

    @Override
    public int getMaxAir() {
        return this.getAttributes() != null ? (int) this.getAttributeValue(AttributeRegistry.GENERIC_MAX_AIR) : 300;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (this.getType().isIn(EntityTypeTags.VEHICLES)) {
            if (player.shouldCancelInteraction()) return ActionResult.PASS;
            return this.world.isClient ? ActionResult.PASS : player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
        } return ActionResult.PASS;
    }
}