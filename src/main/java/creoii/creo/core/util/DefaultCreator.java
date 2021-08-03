package creoii.creo.core.util;

import creoii.creo.common.block.ButtonBlock;
import creoii.creo.common.block.LogBlock;
import creoii.creo.common.block.PressurePlateBlock;
import creoii.creo.common.block.StairsBlock;
import creoii.creo.common.entity.BoatEntity;
import creoii.creo.common.item.BoatItem;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;

public class DefaultCreator {
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

    public static void createDye(String namespace, String name, int color, MapColor mapColor, int fireworkColor, int signColor) {
    }
}