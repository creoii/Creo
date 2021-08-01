package creoii.creo.core.registry;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.registry.Registry;

public class AttributeRegistry {
    public static final EntityAttribute GENERIC_REACH_DISTANCE = new ClampedEntityAttribute("attribute.name.generic.reach_distance", 5.0D, 0.0D, 1024.0D).setTracked(true);
    public static final EntityAttribute GENERIC_GRAVITY = new ClampedEntityAttribute("attribute.name.generic.gravity", 0.08D, -8.0D, 8.0D).setTracked(true);
    public static final EntityAttribute GENERIC_SWIM_SPEED = new ClampedEntityAttribute("attribute.name.generic.swim_speed", 1.0D, 0.0D, 1024.0D).setTracked(true);
    public static final EntityAttribute GENERIC_NATURAL_REGENERATION = new ClampedEntityAttribute("attribute.name.generic.natural_regeneration", 0.0F, 0.0F, 1024.0F).setTracked(true);
    public static final EntityAttribute GENERIC_MAX_AIR = new ClampedEntityAttribute("attribute.name.generic.max_air", 300, 0, 1024).setTracked(true);
    public static final EntityAttribute PLAYER_MAX_HUNGER = new ClampedEntityAttribute("attribute.name.player.max_hunger", 20, 0, 1024).setTracked(true);

    public static void register() {
        Registry.register(Registry.ATTRIBUTE, "generic.reach_distance", GENERIC_REACH_DISTANCE);
        Registry.register(Registry.ATTRIBUTE, "generic.gravity", GENERIC_GRAVITY);
        Registry.register(Registry.ATTRIBUTE, "generic.swim_speed", GENERIC_SWIM_SPEED);
        Registry.register(Registry.ATTRIBUTE, "generic.natural_regeneration", GENERIC_NATURAL_REGENERATION);
        Registry.register(Registry.ATTRIBUTE, "generic.max_air", GENERIC_MAX_AIR);
        Registry.register(Registry.ATTRIBUTE, "player.max_hunger", PLAYER_MAX_HUNGER);
    }
}