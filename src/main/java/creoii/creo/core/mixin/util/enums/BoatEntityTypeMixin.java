package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BoatEntity.Type.class)
public class BoatEntityTypeMixin {
    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    static BoatEntity.Type create(String enumName, int ordinal, Block baseBlock, String name) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static BoatEntity.Type[] field_7724;

    private static void register() {
        ArrayList<BoatEntity.Type> values =  new ArrayList<>(Arrays.asList(field_7724));
        EnumRecords.BOAT_TYPES.forEach((type) -> {
            values.add(create(type.name().toUpperCase(), values.size() + 1, type.base(), type.name()));
        });

        field_7724 = values.toArray(new BoatEntity.Type[0]);
    }
}