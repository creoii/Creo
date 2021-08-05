package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

@Mixin(ArmorMaterials.class)
public abstract class ArmorMaterialsMixin {
    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static ArmorMaterials[] field_7888;

    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    static ArmorMaterials invokeInit(String enumName, int ordinal, String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        throw new AssertionError();
    }

    static {
        ArrayList<ArmorMaterials> values =  new ArrayList<>(Arrays.asList(field_7888));
        ArmorMaterials last = values.get(values.size() - 1);

        EnumRecords.ARMOR_MATERIALS.forEach((material) -> {
            values.add(invokeInit(material.name().toUpperCase(), last.ordinal() + 1, material.name(), material.durabilityMultiplier(), material.protectionAmounts(), material.enchantability(), material.equipSound(), material.toughness(), material.knockbackResistance(), material.repairIngredientSupplier()));
        });

        field_7888 = values.toArray(new ArmorMaterials[0]);
    }
}