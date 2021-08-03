package creoii.creo.core.mixin.block;

import creoii.creo.core.util.BlockUtil;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow public abstract Block getBlock();

    @Inject(method = "getMaterial", at = @At("RETURN"), cancellable = true)
    private void creo$replaceMaterial(CallbackInfoReturnable<Material> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).material);
        }
    }

    @Inject(method = "getLuminance", at = @At("RETURN"), cancellable = true)
    private void creo$replaceLuminance(CallbackInfoReturnable<Integer> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).luminance);
        }
    }

    @Inject(method = "allowsSpawning", at = @At("RETURN"), cancellable = true)
    private void creo$replaceSpawning(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).allowsSpawning);
        }
    }

    @Inject(method = "getHardness", at = @At("RETURN"), cancellable = true)
    private void creo$replaceHardness(CallbackInfoReturnable<Float> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).hardness);
        }
    }

    @Inject(method = "getMapColor", at = @At("RETURN"), cancellable = true)
    private void creo$replaceMapColor(CallbackInfoReturnable<MapColor> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).mapColor);
        }
    }

    @Inject(method = "hasEmissiveLighting", at = @At("RETURN"), cancellable = true)
    private void creo$replaceEmissiveLighting(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).emissiveLighting);
        }
    }

    @Inject(method = "isSolidBlock", at = @At("RETURN"), cancellable = true)
    private void creo$replaceSolidBlock(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).solid);
        }
    }

    @Inject(method = "isOpaque", at = @At("RETURN"), cancellable = true)
    private void creo$replaceOpaque(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).opaque);
        }
    }

    @Inject(method = "shouldSuffocate", at = @At("RETURN"), cancellable = true)
    private void creo$replaceSuffocates(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).suffocation);
        }
    }

    @Inject(method = "shouldBlockVision", at = @At("RETURN"), cancellable = true)
    private void creo$replaceBlocksVision(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).blockVision);
        }
    }

    @Inject(method = "shouldPostProcess", at = @At("RETURN"), cancellable = true)
    private void creo$replacePostProcess(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).postProcess);
        }
    }

    @Inject(method = "getSoundGroup", at = @At("RETURN"), cancellable = true)
    private void creo$replaceSoundGroup(CallbackInfoReturnable<BlockSoundGroup> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).soundGroup);
        }
    }

    @Inject(method = "isToolRequired", at = @At("RETURN"), cancellable = true)
    private void creo$replaceToolRequired(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(this.getBlock())) {
            cir.setReturnValue(BlockUtil.getOrDefaultSettings(this.getBlock()).toolRequired);
        }
    }
}