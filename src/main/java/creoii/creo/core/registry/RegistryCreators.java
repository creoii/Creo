package creoii.creo.core.registry;

import creoii.creo.common.block.ButtonBlock;
import creoii.creo.common.block.LogBlock;
import creoii.creo.common.block.PressurePlateBlock;
import creoii.creo.common.block.StairsBlock;
import creoii.creo.common.entity.BoatEntity;
import creoii.creo.common.item.BoatItem;
import creoii.creo.core.util.RegistryUtil;
import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class RegistryCreators {
    public static BoatEntity.Boat createBoat(BoatEntity.Boat boat) {
        BoatEntity.BOATS.put(boat.getName(), boat);
        return boat;
    }

    public static void createWood(String namespace, String name) {
        Block stripped = RegistryUtil.createBlock(new Identifier(namespace, "stripped_" + name + "_log"), new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_log"), new LogBlock(stripped, AbstractBlock.Settings.copy(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
        Block strippedWood = RegistryUtil.createBlock(new Identifier(namespace, "stripped_" + name + "_wood"), new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_wood"), new LogBlock(strippedWood, AbstractBlock.Settings.copy(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
        Block planks = RegistryUtil.createBlock(new Identifier(namespace, name + "_planks"), new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_slab"), new SlabBlock(AbstractBlock.Settings.copy(planks)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_stairs"), new StairsBlock(planks.getDefaultState(), AbstractBlock.Settings.copy(planks)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_fence"), new FenceBlock(AbstractBlock.Settings.copy(planks)), ItemGroup.DECORATIONS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_fence_gate"), new FenceGateBlock(AbstractBlock.Settings.copy(planks)), ItemGroup.REDSTONE);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_button"), new ButtonBlock(true, 30, AbstractBlock.Settings.copy(planks)), ItemGroup.REDSTONE);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_pressure_plate"), new PressurePlateBlock(net.minecraft.block.PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(planks)), ItemGroup.REDSTONE);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_door"), new creoii.creo.common.block.DoorBlock(AbstractBlock.Settings.copy(planks)), ItemGroup.REDSTONE);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_trapdoor"), new creoii.creo.common.block.TrapdoorBlock(AbstractBlock.Settings.copy(planks)), ItemGroup.REDSTONE);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_sign"), new SignBlock(AbstractBlock.Settings.copy(Blocks.OAK_SIGN), SignType.OAK), ItemGroup.DECORATIONS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_wall_sign"), new WallSignBlock(AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN), SignType.OAK), null);
        Registry.register(Registry.ITEM, new Identifier(namespace, name + "_boat"), new BoatItem(createBoat(new BoatEntity.Boat(new Identifier(namespace, name), planks, null)), new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
    }

    public static void createStone(String namespace, String name, boolean hasBricks, boolean hasTiles, boolean hasMossy) {
        Block stone = RegistryUtil.createBlock(new Identifier(namespace, name), new Block(AbstractBlock.Settings.copy(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_slab"), new SlabBlock(AbstractBlock.Settings.copy(stone)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_stairs"), new StairsBlock(stone.getDefaultState(), AbstractBlock.Settings.copy(stone)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, name + "_wall"), new WallBlock(AbstractBlock.Settings.copy(stone)), ItemGroup.DECORATIONS);
        Block smooth = RegistryUtil.createBlock(new Identifier(namespace, "smooth_" + name), new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, "smooth_" + name + "_slab"), new SlabBlock(AbstractBlock.Settings.copy(smooth)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, "smooth_" + name + "_stairs"), new StairsBlock(smooth.getDefaultState(), AbstractBlock.Settings.copy(smooth)), ItemGroup.BUILDING_BLOCKS);
        RegistryUtil.createBlock(new Identifier(namespace, "smooth_" + name + "_wall"), new WallBlock(AbstractBlock.Settings.copy(smooth)), ItemGroup.DECORATIONS);

        if (hasBricks) {
            Block bricks = RegistryUtil.createBlock(new Identifier(namespace, name + "_bricks"), new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
            RegistryUtil.createBlock(new Identifier(namespace, name + "_brick_slab"), new SlabBlock(AbstractBlock.Settings.copy(bricks)), ItemGroup.BUILDING_BLOCKS);
            RegistryUtil.createBlock(new Identifier(namespace, name + "_brick_stairs"), new StairsBlock(bricks.getDefaultState(), AbstractBlock.Settings.copy(bricks)), ItemGroup.BUILDING_BLOCKS);
            RegistryUtil.createBlock(new Identifier(namespace, name + "_brick_wall"), new WallBlock(AbstractBlock.Settings.copy(bricks)), ItemGroup.DECORATIONS);
            RegistryUtil.createBlock(new Identifier(namespace, "chiseled_" + name + "_bricks"), new Block(AbstractBlock.Settings.copy(bricks)), ItemGroup.BUILDING_BLOCKS);
            RegistryUtil.createBlock(new Identifier(namespace, "cracked_" + name + "_bricks"), new Block(AbstractBlock.Settings.copy(bricks)), ItemGroup.BUILDING_BLOCKS);

            if (hasMossy) {
                Block mossyBricks = RegistryUtil.createBlock(new Identifier(namespace, "mossy_" + name + "_bricks"), new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
                RegistryUtil.createBlock(new Identifier(namespace, "mossy_" + name + "_brick_slab"), new SlabBlock(AbstractBlock.Settings.copy(mossyBricks)), ItemGroup.BUILDING_BLOCKS);
                RegistryUtil.createBlock(new Identifier(namespace, "mossy_" + name + "_brick_stairs"), new StairsBlock(mossyBricks.getDefaultState(), AbstractBlock.Settings.copy(mossyBricks)), ItemGroup.BUILDING_BLOCKS);
                RegistryUtil.createBlock(new Identifier(namespace, "mossy_" + name + "_brick_wall"), new WallBlock(AbstractBlock.Settings.copy(mossyBricks)), ItemGroup.DECORATIONS);
            }
        }

        if (hasTiles) {
            Block tiles = RegistryUtil.createBlock(new Identifier(namespace, name + "_tiles"), new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)), ItemGroup.BUILDING_BLOCKS);
            RegistryUtil.createBlock(new Identifier(namespace, name + "_tile_slab"), new SlabBlock(AbstractBlock.Settings.copy(tiles)), ItemGroup.BUILDING_BLOCKS);
            RegistryUtil.createBlock(new Identifier(namespace, name + "_tile_stairs"), new StairsBlock(tiles.getDefaultState(), AbstractBlock.Settings.copy(tiles)), ItemGroup.BUILDING_BLOCKS);
            RegistryUtil.createBlock(new Identifier(namespace, name + "_tile_wall"), new WallBlock(AbstractBlock.Settings.copy(tiles)), ItemGroup.DECORATIONS);
        }
    }

    public static void createArmorMaterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        EnumRecords.ArmorMaterial material = new EnumRecords.ArmorMaterial(name, durabilityMultiplier, protectionAmounts, enchantability, equipSound, toughness, knockbackResistance, repairIngredientSupplier);
        EnumRecords.ARMOR_MATERIALS.add(material);
        System.out.println(EnumRecords.ARMOR_MATERIALS);
    }

    public static void createBoatType(Identifier id, Block base) {
        EnumRecords.BoatType type = new EnumRecords.BoatType(base, id.getPath());
        EnumRecords.BOAT_TYPES.add(type);
        System.out.println(EnumRecords.BOAT_TYPES);
    }

    public static void createDyeColor(Identifier id, int woolId, int color, MapColor mapColor, int fireworkColor, int signColor) {
        EnumRecords.DyeColor dyeColor = new EnumRecords.DyeColor(woolId, id.getPath(), color, mapColor, fireworkColor, signColor);
        EnumRecords.DYES.add(dyeColor);
        System.out.println(EnumRecords.DYES);
    }

    public static void createEnchantmentRarity(String name, int weight) {
        EnumRecords.EnchantmentRarity rarity = new EnumRecords.EnchantmentRarity(name, weight);
        EnumRecords.ENCHANTMENT_RARITIES.add(rarity);
        System.out.println(EnumRecords.ENCHANTMENT_RARITIES);
    }

    public static void createItemRarity(String name, Formatting formatting) {
        EnumRecords.Rarity rarity = new EnumRecords.Rarity(name, formatting);
        EnumRecords.RARITIES.add(rarity);
        System.out.println(EnumRecords.RARITIES);
    }

    public static void createToolMaterial(String name, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        EnumRecords.ToolMaterial material = new EnumRecords.ToolMaterial(name, miningLevel, itemDurability, miningSpeed, attackDamage, enchantability, repairIngredient);
        EnumRecords.TOOL_MATERIALS.add(material);
        System.out.println(EnumRecords.TOOL_MATERIALS);
    }

    public static void createTropicalFishVariety(String name, int shape, int pattern) {
        EnumRecords.TropicalFishVariety variety = new EnumRecords.TropicalFishVariety(name, shape, pattern);
        EnumRecords.TROPICAL_FISH_VARIETIES.add(variety);
        System.out.println(EnumRecords.TROPICAL_FISH_VARIETIES);
    }

    public static void createBannerPattern(String name, String id, boolean hasPatternItem) {
        EnumRecords.BannerPattern pattern = new EnumRecords.BannerPattern(name, id, hasPatternItem);
        EnumRecords.BANNER_PATTERNS.add(pattern);
        System.out.println(EnumRecords.BANNER_PATTERNS);
    }
}