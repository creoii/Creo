package creoii.creo.core.util;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ItemTags {
    public static final Tag<Item> HOPPER_IMMUNE = TagRegistry.item(new Identifier(Creo.MOD_ID, "hopper_immune"));
}