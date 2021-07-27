package creoii.creo.core.mixin;

import creoii.creo.core.registry.AttributeRegistry;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = { AbstractFurnaceBlockEntity.class, BrewingStandBlockEntity.class, LootableContainerBlockEntity.class, PlayerInventory.class, StorageMinecartEntity.class })
public class LootableContainerBlockEntityMixin {
    @ModifyConstant(method = "canPlayerUse(Lnet/minecraft/entity/player/PlayerEntity;)Z", constant = @Constant(doubleValue = 64.0))
    private static double getActualReachDistance(double reach, PlayerEntity player) {
        double d = player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE);
        return d * d;
    }
}