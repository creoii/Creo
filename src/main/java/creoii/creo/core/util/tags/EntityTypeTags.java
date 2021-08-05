package creoii.creo.core.util.tags;

import creoii.creo.core.Creo;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class EntityTypeTags {
    public static final Tag<EntityType<?>> ZOMBIES = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "zombies"));
    public static final Tag<EntityType<?>> SPIDERS = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "spiders"));
    public static final Tag<EntityType<?>> MILKABLES = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "milkables"));
    public static final Tag<EntityType<?>> VEHICLES = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "vehicles"));
    public static final Tag<EntityType<?>> PROJECTILES_PASS_THROUGH = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "projectiles_pass_through"));
    public static final Tag<EntityType<?>> IMMOVABLE_BY_FLUIDS = TagRegistry.entityType(new Identifier(Creo.MOD_ID, "immovable_by_fluids"));
}