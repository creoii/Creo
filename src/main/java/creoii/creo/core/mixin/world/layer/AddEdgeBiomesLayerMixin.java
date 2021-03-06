package creoii.creo.core.mixin.world.layer;

import creoii.creo.core.util.BiomeUtil;
import creoii.creo.core.util.tags.BiomeTags;
import net.minecraft.world.biome.layer.AddEdgeBiomesLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(AddEdgeBiomesLayer.class)
public class AddEdgeBiomesLayerMixin {
    @Inject(at = @At("HEAD"), method = "sample", cancellable = true)
    private void creo$addEdgeBiomes(LayerRandomnessSource rand, int n, int e, int s, int w, int center, CallbackInfoReturnable<Integer> cir) {
        Random random = new Random();

        BiomeUtil.BIOME_BEACHES.forEach((key, value) -> {
            if (center == BiomeUtil.getId(key)) {
                if (BiomeUtil.isIn(n, BiomeTags.OCEAN) || BiomeUtil.isIn(e, BiomeTags.OCEAN) || BiomeUtil.isIn(s, BiomeTags.OCEAN) || BiomeUtil.isIn(w, BiomeTags.OCEAN)) {
                    cir.setReturnValue(BiomeUtil.getId(value.beach()));
                }
            }
        });

        BiomeUtil.BIOME_EDGES.forEach((key, value) -> {
            if (center == BiomeUtil.getId(key)) {
                if ((value.whitelisted(n) || value.whitelisted(e) || value.whitelisted(s) || value.whitelisted(w)) && (!value.blacklisted(e) || !value.blacklisted(n) || !value.blacklisted(s) || !value.blacklisted(w))) {
                    cir.setReturnValue(BiomeUtil.getId(value.edge()));
                }
            }
        });
    }
}