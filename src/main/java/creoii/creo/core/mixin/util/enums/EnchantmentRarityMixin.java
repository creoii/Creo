package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Enchantment.Rarity.class)
public abstract class EnchantmentRarityMixin {
    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static Enchantment.Rarity[] field_9092;

    @Invoker(value = "<init>", remap = false)
    @SuppressWarnings("InvokerTarget")
    private static Enchantment.Rarity init(String enumName, int ordinal, int weight) {
        throw new AssertionError();
    }

    static {
        ArrayList<Enchantment.Rarity> values =  new ArrayList<>(Arrays.asList(field_9092));
        Enchantment.Rarity last = values.get(values.size() - 1);

        EnumRecords.ENCHANTMENT_RARITIES.forEach((rarity) -> {
            values.add(init(rarity.name().toUpperCase(), last.ordinal() + 1, rarity.weight()));
        });

        field_9092 = values.toArray(new Enchantment.Rarity[0]);
    }
}