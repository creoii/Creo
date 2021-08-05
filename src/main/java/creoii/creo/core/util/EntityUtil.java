package creoii.creo.core.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityUtil {
    public static Map<EntityType<?>, List<DamageSource>> ENTITY_IMMUNITIES = new HashMap<>();

    public static void addImmunityToEntity(EntityType<?> entity, DamageSource source) {
        if (ENTITY_IMMUNITIES.containsKey(entity)) {
            ENTITY_IMMUNITIES.get(entity).add(source);
        } else ENTITY_IMMUNITIES.put(entity, List.of(source));
    }

    public static void addImmunitiesToEntity(EntityType<?> entity, List<DamageSource> sources) {
        if (ENTITY_IMMUNITIES.containsKey(entity)) {
            sources.forEach((source -> ENTITY_IMMUNITIES.get(entity).add(source)));
        } else ENTITY_IMMUNITIES.put(entity, sources);
    }
}