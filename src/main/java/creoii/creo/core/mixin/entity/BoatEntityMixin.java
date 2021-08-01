package creoii.creo.core.mixin.entity;

import creoii.creo.core.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    @Shadow private boolean pressingLeft;
    @Shadow private boolean pressingRight;
    @Shadow private boolean pressingForward;
    @Shadow private boolean pressingBack;
    @Shadow private float yawVelocity;

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "updatePaddles", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
    private void creo$applyBoatSpeed(Entity entity, Vec3d velocity) {
        float f = 0.0F;
        if (this.pressingLeft) {
            this.yawVelocity -= Constants.getBoatSpeed() * 25.0F;
        }

        if (this.pressingRight) {
            this.yawVelocity += Constants.getBoatSpeed() * 25.0F;
        }

        if (this.pressingRight != this.pressingLeft && !this.pressingForward && !this.pressingBack) {
            f += Constants.getBoatSpeed() / 8.0F;
        }

        if (this.pressingForward) {
            f += Constants.getBoatSpeed();
        }

        if (this.pressingBack) {
            f -= Constants.getBoatSpeed() / 8.0F;
        }
        this.setVelocity(this.getVelocity().add(MathHelper.sin(-this.getYaw() * 0.017453292F) * f, 0.0D, MathHelper.cos(this.getYaw() * 0.017453292F) * f));
    }
}