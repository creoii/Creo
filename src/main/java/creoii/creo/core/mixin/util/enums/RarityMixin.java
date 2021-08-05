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
    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static Rarity[] field_8905;

    @Invoker(value = "<init>", remap = false)
    @SuppressWarnings("InvokerTarget")
    private static Rarity init(String enumName, int ordinal, Formatting formatting) {
        throw new AssertionError();
    }

    static {
        ArrayList<Rarity> values =  new ArrayList<>(Arrays.asList(field_8905));
        Rarity last = values.get(values.size() - 1);

        EnumRecords.RARITIES.forEach((rarity) -> {
            values.add(init(rarity.name().toUpperCase(), last.ordinal() + 1, rarity.formatting()));
        });

        field_8905 = values.toArray(new Rarity[0]);
    }
}