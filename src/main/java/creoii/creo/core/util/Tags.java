package creoii.creo.core.util;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Tags {
    public static class Blocks {
        public static final Tag<Block> RAVAGER_BREAKABLE = TagRegistry.block(new Identifier(Creo.MOD_ID, "ravager_breakable"));
        public static final Tag<Block> BLOCKS_VEX = TagRegistry.block(new Identifier(Creo.MOD_ID, "blocks_vex"));
    }

    public static class Items {
        public static final Tag<Item> HOPPER_IMMUNE = TagRegistry.item(new Identifier(Creo.MOD_ID, "hopper_immune"));
    }
}