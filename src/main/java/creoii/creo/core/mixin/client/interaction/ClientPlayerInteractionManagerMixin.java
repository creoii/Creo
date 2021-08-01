package creoii.creo.core.mixin.client.interaction;

import creoii.creo.core.registry.AttributeRegistry;
import creoii.creo.core.util.Constants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;
    @Shadow private BlockPos currentBreakingPos;
    @Shadow private float currentBreakingProgress;
    @Shadow private boolean breakingBlock;
    @Shadow private GameMode gameMode;

    @Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
    private void creo$applyReachDistance(CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = client.player;
        if (player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE) > 4.5F || (player.isCreative() && player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE) > 5.0F)) {
            cir.setReturnValue(((float) player.getAttributeValue(AttributeRegistry.GENERIC_REACH_DISTANCE)));
        }
    }

    /*
    @Redirect(method = { "cancelBlockBreaking", "updateBlockBreakingProgress" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;setBlockBreakingInfo(ILnet/minecraft/util/math/BlockPos;I)V"))
    private void creo$pauseBlockBreaking(ClientWorld clientWorld, int entityId, BlockPos pos, int progress) {
        breakingBlock = true;
        progress = (int) this.currentBreakingProgress * 10;
        this.client.world.setBlockBreakingInfo(this.client.player.getId(), this.currentBreakingPos, progress);
        Constants.setCurrentBreakingProgress(this.currentBreakingPos, progress);
    }
     */

    @ModifyConstant(method = { "updateBlockBreakingProgress", "attackBlock" }, constant = @Constant(intValue = 5))
    private int creo$replaceBlockBreakingCooldown1(int i) {
        return Constants.getBlockBreakingCooldown();
    }

    @Inject(method = "breakBlock", at = @At("HEAD"))
    private void creo$removeBreakingProgress(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        //if (!this.client.player.isBlockBreakingRestricted(this.client.world, pos, this.gameMode)) Constants.removeCurrentBreakingProgress(pos);
    }
}