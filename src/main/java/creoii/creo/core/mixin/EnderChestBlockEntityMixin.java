package creoii.creo.core.mixin;

import creoii.creo.core.registry.AttributeRegistry;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderChestBlockEntity.class)
public class EnderChestBlockEntityMixin {
    @ModifyConstant(method = "canPlayerUse", constant = @Constant(doubleValue = 64.0))
    private static double getActualReachDistance(double reach, PlayerEntity player) {
        double d = player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE);
        return d * d;
    }
}