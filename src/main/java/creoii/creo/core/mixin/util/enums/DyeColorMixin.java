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
    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static DyeColor[] field_7959;

    @Invoker(value = "<init>", remap = false)
    @SuppressWarnings("InvokerTarget")
    private static DyeColor init(String enumName, int ordinal, int woolId, String name, int color, MapColor mapColor, int fireworkColor, int signColor) {
        throw new AssertionError();
    }

    static {
        ArrayList<DyeColor> values =  new ArrayList<>(Arrays.asList(field_7959));
        DyeColor last = values.get(values.size() - 1);

        EnumRecords.DYES.forEach((dye) -> {
            values.add(init(dye.name().toUpperCase(), last.ordinal() + 1, dye.woolId(), dye.name(), dye.color(), dye.mapColor(), dye.fireworkColor(), dye.signColor()));
        });

        field_7959 = values.toArray(new DyeColor[0]);
    }
}