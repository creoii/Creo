package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BoatEntity.Type.class)
public class BoatEntityTypeMixin {
    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static BoatEntity.Type[] field_7724;

    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    private static BoatEntity.Type create(String nameId, int id, Block base, String name) {
        throw new AssertionError();
    }

    static {
        ArrayList<BoatEntity.Type> values =  new ArrayList<>(Arrays.asList(field_7724));
        BoatEntity.Type last = values.get(values.size() - 1);

        EnumRecords.BOAT_TYPES.forEach((type) -> {
            values.add(create(type.name().toUpperCase(), last.ordinal() + 1, type.base(), type.name()));
        });

        field_7724 = values.toArray(new BoatEntity.Type[0]);
    }
}