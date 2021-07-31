package creoii.creo.core.mixin.entity;

import creoii.creo.core.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {
    public AbstractMinecartEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getMaxOffRailSpeed", at = @At("HEAD"), cancellable = true)
    protected void getMaxOffRailSpeed(CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(this.isTouchingWater() ? Constants.MINECART_SPEED : Constants.MINECART_SPEED * 2.0D);
    }
}