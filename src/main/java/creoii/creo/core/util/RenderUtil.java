package creoii.creo.core.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class RenderUtil {
    public static Entity playerRenderAs = MinecraftClient.getInstance().player;

    public static void renderPlayerAs(Entity entity) {
        playerRenderAs = entity;
    }

    public static void resetPlayerRenderAs() {
        playerRenderAs = MinecraftClient.getInstance().player;
    }

    public static void setWindowTitle(String title) {
        MinecraftClient.getInstance().getWindow().setTitle(title);
    }
}