package creoii.creo.core.mixin.entity;

import creoii.creo.core.util.EntityUtil;
import creoii.creo.core.util.data.Constants;
import creoii.creo.core.util.tags.EntityTypeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract EntityType<?> getType();

    @Inject(method = "isInvulnerableTo", at = @At("HEAD"), cancellable = true)
    private void creo$applyInvulnerabilities(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (EntityUtil.ENTITY_IMMUNITIES.containsKey(getType())) {
            cir.setReturnValue(EntityUtil.ENTITY_IMMUNITIES.get(getType()).contains(source));
        }
    }

    @Inject(method = "getDefaultNetherPortalCooldown", at = @At("RETURN"), cancellable = true)
    private void creo$replaceNetherPortalCooldown(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Constants.getNetherPortalCooldown());
    }

    @Inject(method = "getSafeFallDistance", at = @At("RETURN"), cancellable = true)
    private void creo$replaceSafeFallDistance(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Constants.getSafeFallDistance());
    }

    @Inject(method = "isPushedByFluids", at = @At("RETURN"), cancellable = true)
    private void creo$applyFluidImmovables(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!getType().isIn(EntityTypeTags.IMMOVABLE_BY_FLUIDS));
    }
}