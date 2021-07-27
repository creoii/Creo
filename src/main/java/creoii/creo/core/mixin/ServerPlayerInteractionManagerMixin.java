package creoii.creo.core.mixin;

import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerPlayerInteractionManager.class)
public interface ServerPlayerInteractionManagerMixin {
    @Accessor("blockBreakingProgress")
    public int getBlockBreakingProgress();
}