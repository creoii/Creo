package creoii.creo.core.util;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class EntityTypeTags {
    public static final Tag<EntityType<?>> ZOMBIES = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "entities"));
    public static final Tag<EntityType<?>> MILKABLES = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "milkables"));
}