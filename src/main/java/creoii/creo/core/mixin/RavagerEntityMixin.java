package creoii.creo.core.mixin;

import creoii.creo.core.util.Tags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RavagerEntity.class)
public class RavagerEntityMixin {
    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;breakBlock(Lnet/minecraft/util/math/BlockPos;ZLnet/minecraft/entity/Entity;)Z"))
    public boolean creo$ravagerBreakables(World world, BlockPos pos, boolean drop, Entity breakingEntity) {
        if (world.getBlockState(pos).isIn(Tags.Blocks.RAVAGER_BREAKABLE)) {
            return world.breakBlock(pos, drop, breakingEntity);
        }  else return false;
    }
}