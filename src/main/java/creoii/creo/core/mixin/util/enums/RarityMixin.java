package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Rarity.class)
public abstract class RarityMixin {
    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    private static Rarity create(String enumName, int ordinal, Formatting formatting) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static Rarity[] field_8905;

    static  {
        ArrayList<Rarity> values =  new ArrayList<>(Arrays.asList(field_8905));
        EnumRecords.RARITIES.forEach((rarity) -> {
            values.add(create(rarity.name().toUpperCase(), values.size() + 1, rarity.formatting()));
        });

        field_8905 = values.toArray(new Rarity[0]);
    }
}