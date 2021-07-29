package creoii.creo.core.util;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BlockTags {
    public static final Tag<Block> BLOCKS_VEX = TagRegistry.block(new Identifier(Creo.MOD_ID, "blocks_vex"));
    public static final Tag<Block> RAVAGER_BREAKABLE = TagRegistry.block(new Identifier(Creo.MOD_ID, "ravager_breakable"));
    public static final Tag<Block> AFFECTED_BY_GRAVITY = TagRegistry.block(new Identifier(Creo.MOD_ID, "affected_by_gravity"));
}