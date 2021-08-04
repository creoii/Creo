package creoii.creo.core.mixin.world;

import creoii.creo.core.util.BiomeTags;
import creoii.creo.core.util.BiomeUtil;
import net.minecraft.world.biome.layer.AddIslandLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AddIslandLayer.class)
public class AddIslandLayerMixin {
    @Inject(method = "sample", at = @At("HEAD"), cancellable = true)
    private void creo$addIslandBiomes(LayerRandomnessSource context, int n, int e, int s, int w, int center, CallbackInfoReturnable<Integer> cir) {
        BiomeUtil.ISLAND_BIOMES.forEach((biome, chance) -> {
            cir.setReturnValue(BiomeUtil.isIn(center, BiomeTags.OCEAN) && BiomeUtil.isIn(n, BiomeTags.OCEAN) && BiomeUtil.isIn(e, BiomeTags.OCEAN) && BiomeUtil.isIn(w, BiomeTags.OCEAN) && BiomeUtil.isIn(s, BiomeTags.OCEAN) && context.nextInt(chance) == 0 ? BiomeUtil.getId(biome) : center);
        });
    }
}