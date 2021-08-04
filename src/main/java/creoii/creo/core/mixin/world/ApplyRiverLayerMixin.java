package creoii.creo.core.mixin.world;

import creoii.creo.core.util.tags.BiomeTags;
import creoii.creo.core.util.BiomeUtil;
import net.minecraft.world.biome.layer.ApplyRiverLayer;
import net.minecraft.world.biome.layer.util.IdentityCoordinateTransformer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import net.minecraft.world.biome.layer.util.LayerSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ApplyRiverLayer.class)
public class ApplyRiverLayerMixin implements IdentityCoordinateTransformer {
    @Inject(at = @At("HEAD"), method = "sample", cancellable = true)
    private void creo$addRiverBiomes(LayerRandomnessSource context, LayerSampler sampler1, LayerSampler sampler2, int x, int z, CallbackInfoReturnable<Integer> cir) {
        BiomeUtil.BIOME_RIVERS.forEach((key, value) -> {
            int i = sampler1.sample(this.transformX(x), this.transformZ(z));
            if (!BiomeUtil.isIn(i, BiomeTags.OCEAN) && !BiomeUtil.isIn(i, BiomeTags.BEACH) && !BiomeUtil.isIn(i, BiomeTags.SHORE)) {
                // REPLACES ENTIRE BIOME????????? WHYYY
                //if (i == BiomeUtil.getId(key)) cir.setReturnValue(BiomeUtil.getId(value.river()));
            }
        });
    }
}