package creoii.creo.core.mixin.block;

import creoii.creo.core.util.BlockUtil;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock {
    @Shadow protected abstract Block asBlock();

    public BlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getBlastResistance", at = @At("RETURN"), cancellable = true)
    private void creo$replaceBlastResistance(CallbackInfoReturnable<Float> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.asBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.asBlock()).resistance);
        }
    }

    @Inject(method = "getJumpVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    private void creo$replaceJumpVelocity(CallbackInfoReturnable<Float> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.asBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.asBlock()).jumpVelocityMultiplier);
        }
    }

    @Inject(method = "getVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    private void creo$replaceVelocity(CallbackInfoReturnable<Float> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.asBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.asBlock()).velocityMultiplier);
        }
    }

    @Inject(method = "getSlipperiness", at = @At("RETURN"), cancellable = true)
    private void creo$replaceSlipperiness(CallbackInfoReturnable<Float> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.asBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.asBlock()).slipperiness);
        }
    }

    @Inject(method = "hasDynamicBounds", at = @At("RETURN"), cancellable = true)
    private void creo$replaceDynamicBounds(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.asBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.asBlock()).dynamicBounds);
        }
    }

    @Inject(method = "hasRandomTicks", at = @At("RETURN"), cancellable = true)
    private void creo$replaceRandomTicks(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.asBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.asBlock()).randomTicks);
        }
    }
}