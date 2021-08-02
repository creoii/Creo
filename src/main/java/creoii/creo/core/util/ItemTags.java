package creoii.creo.core.util;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ItemTags {
    public static final Tag<Item> HOPPER_IMMUNE = TagRegistry.item(new Identifier(Creo.MOD_ID, "hopper_immune"));
    public static final Tag<Item> EXPLOSION_IMMUNE = TagRegistry.item(new Identifier(Creo.MOD_ID, "explosion_immune"));
    public static final Tag<Item> CACTUS_IMMUNE = TagRegistry.item(new Identifier(Creo.MOD_ID, "cactus_immune"));
    public static final Tag<Item> UNFRAMEABLE = TagRegistry.item(new Identifier(Creo.MOD_ID, "unframeable"));
}