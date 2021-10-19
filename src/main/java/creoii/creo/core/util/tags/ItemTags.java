package creoii.creo.core.util.tags;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ItemTags {
    public static final Tag<Item> HOPPER_IMMUNE = TagFactory.ITEM.create(new Identifier(Creo.MOD_ID, "hopper_immune"));
    public static final Tag<Item> EXPLOSION_IMMUNE = TagFactory.ITEM.create(new Identifier(Creo.MOD_ID, "explosion_immune"));
    public static final Tag<Item> CACTUS_IMMUNE = TagFactory.ITEM.create(new Identifier(Creo.MOD_ID, "cactus_immune"));
    public static final Tag<Item> FIREPROOF = TagFactory.ITEM.create(new Identifier(Creo.MOD_ID, "fireproof"));
    public static final Tag<Item> UNFRAMEABLE = TagFactory.ITEM.create(new Identifier(Creo.MOD_ID, "unframeable"));
    public static final Tag<Item> SHEARS_SHEEP = TagFactory.ITEM.create(new Identifier(Creo.MOD_ID, "shears_sheep"));
    public static final Tag<Item> GLINTED = TagFactory.ITEM.create(new Identifier(Creo.MOD_ID, "glinted"));
}