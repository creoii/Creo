package creoii.creo.core.registry;

import creoii.creo.common.entity.BoatEntity;
import creoii.creo.core.Creo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    public static final EntityType<BoatEntity> BOAT = FabricEntityTypeBuilder.<BoatEntity>create(SpawnGroup.CREATURE, BoatEntity::new).dimensions(EntityDimensions.fixed(1.375F, 0.5625F)).trackedUpdateRate(10).build();

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Creo.MOD_ID, "boat"), BOAT);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        EntityRendererRegistry.register(BOAT, BoatEntityRenderer::new);
    }
}