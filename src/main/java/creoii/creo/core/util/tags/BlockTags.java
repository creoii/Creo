package creoii.creo.core.util.tags;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BlockTags {
    public static final Tag<Block> AFFECTED_BY_GRAVITY = TagFactory.BLOCK.create(new Identifier(Creo.MOD_ID, "affected_by_gravity"));
    public static final Tag<Block> BLOCKS_VEX = TagFactory.BLOCK.create(new Identifier(Creo.MOD_ID, "blocks_vex"));
    public static final Tag<Block> PLAYER_IMMUNE = TagFactory.BLOCK.create(new Identifier(Creo.MOD_ID, "player_immune"));
    public static final Tag<Block> RAVAGER_BREAKABLE = TagFactory.BLOCK.create(new Identifier(Creo.MOD_ID, "ravager_breakable"));
    public static final Tag<Block> BOOSTS_ENCHANTS = TagFactory.BLOCK.create(new Identifier(Creo.MOD_ID, "boosts_enchants"));
    public static final Tag<Block> END_CRYSTAL_BASE_BLOCKS = TagFactory.BLOCK.create(new Identifier(Creo.MOD_ID, "end_crystal_base_blocks"));
}