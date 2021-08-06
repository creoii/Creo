package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.block.MapColor;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(DyeColor.class)
public class DyeColorMixin {
    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    private static DyeColor create(String enumName, int ordinal, int woolId, String name, int color, MapColor mapColor, int fireworkColor, int signColor) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static DyeColor[] field_7959;

    public void register() {
        ArrayList<DyeColor> values =  new ArrayList<>(Arrays.asList(field_7959));
        EnumRecords.DYES.forEach((dye) -> {
            values.add(create(dye.name().toUpperCase(), values.size() + 1, dye.woolId(), dye.name(), dye.color(), dye.mapColor(), dye.fireworkColor(), dye.signColor()));
        });

        field_7959 = values.toArray(new DyeColor[0]);
    }
}