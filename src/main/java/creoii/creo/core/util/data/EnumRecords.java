package creoii.creo.core.util.data;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EnumRecords {
    public static List<DyeColor> DYES = new ArrayList<>();
    public static List<BoatType> BOAT_TYPES = new ArrayList<>();
    public static List<Rarity> RARITIES = new ArrayList<>();
    public static List<EnchantmentRarity> ENCHANTMENT_RARITIES = new ArrayList<>();
    public static List<ToolMaterial> TOOL_MATERIALS = new ArrayList<>();
    public static List<ArmorMaterial> ARMOR_MATERIALS = new ArrayList<>();
    public static List<TropicalFishVariety> TROPICAL_FISH_VARIETIES = new ArrayList<>();

    public static record DyeColor(int woolId, String name, int color, MapColor mapColor, int fireworkColor, int signColor) {}
    public static record BoatType(Block base, String name) {}
    public static record Rarity(String name, Formatting formatting) {}
    public static record EnchantmentRarity(String name, int weight) {}
    public static record ToolMaterial(String name, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {}
    public static record ArmorMaterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {}
    public static record TropicalFishVariety(String name, int shape, int pattern) {}
}