package creoii.creo.core.mixin.world;

import creoii.creo.core.util.data.Constants;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Shadow @Final private WorldRenderer worldRenderer;

    @Inject(method = "setBlockBreakingInfo", at = @At("TAIL"))
    private void creo$pauseBlockBreaking(int entityId, BlockPos pos, int progress, CallbackInfo ci) {
        this.worldRenderer.setBlockBreakingInfo(entityId, pos, Constants.getBreakingProgress(pos));
    }
}