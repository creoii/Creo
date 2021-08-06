package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public abstract class ToolMaterialsMixin {
    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    private static ToolMaterials create(String enumName, int ordinal, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static ToolMaterials[] field_8926;

    public void register() {
        ArrayList<ToolMaterials> values =  new ArrayList<>(Arrays.asList(field_8926));
        EnumRecords.TOOL_MATERIALS.forEach((material) -> {
            values.add(create(material.name().toUpperCase(), values.size() + 1, material.miningLevel(), material.itemDurability(), material.miningSpeed(), material.attackDamage(), material.enchantability(), material.repairIngredient()));
        });

        field_8926 = values.toArray(new ToolMaterials[0]);
    }
}