package creoii.creo.core.mixin;

import creoii.creo.core.util.ItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @Inject(method = "onEntityCollided", at = @At("HEAD"), cancellable = true)
    private static void creo$hopperImmunes(World world, BlockPos pos, BlockState state, Entity entity, HopperBlockEntity blockEntity, CallbackInfo ci) {
        if (entity instanceof ItemEntity) {
            if (((ItemEntity) entity).getStack().isIn(ItemTags.HOPPER_IMMUNE)) ci.cancel();
        }
    }
}