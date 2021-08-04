package creoii.creo.core.mixin.world;

import creoii.creo.core.util.data.Constants;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Shadow @Final private MinecraftServer server;

    /**
     * @author CreoII
     */
    @Overwrite
    public void setBlockBreakingInfo(int entityId, BlockPos pos, int progress) {
        this.server.getPlayerManager().getPlayerList().iterator().forEachRemaining((serverPlayerEntity -> {
            if (serverPlayerEntity != null && serverPlayerEntity.world instanceof ServerWorld && serverPlayerEntity.getId() != entityId) {
                double d = (double)pos.getX() - serverPlayerEntity.getX();
                double e = (double)pos.getY() - serverPlayerEntity.getY();
                double f = (double)pos.getZ() - serverPlayerEntity.getZ();
                if (d * d + e * e + f * f < 1024.0D) {
                    serverPlayerEntity.networkHandler.sendPacket(new BlockBreakingProgressS2CPacket(entityId, pos, Constants.getBreakingProgress(pos)));
                }
            }
        }));
    }
}