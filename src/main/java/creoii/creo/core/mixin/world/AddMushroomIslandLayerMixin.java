package creoii.creo.core.mixin.world;

import creoii.creo.core.util.tags.BiomeTags;
import creoii.creo.core.util.BiomeUtil;
import net.minecraft.world.biome.layer.AddMushroomIslandLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AddMushroomIslandLayer.class)
public class AddMushroomIslandLayerMixin {
    @Inject(method = "sample", at = @At("HEAD"), cancellable = true)
    private void creo$addIslandBiomes(LayerRandomnessSource context, int sw, int se, int ne, int nw, int center, CallbackInfoReturnable<Integer> cir) {
        BiomeUtil.ISLAND_BIOMES.forEach((biome, chance) -> {
            cir.setReturnValue(BiomeUtil.isIn(center, BiomeTags.DEEP_OCEAN) && BiomeUtil.isIn(nw, BiomeTags.DEEP_OCEAN) && BiomeUtil.isIn(sw, BiomeTags.DEEP_OCEAN) && BiomeUtil.isIn(ne, BiomeTags.DEEP_OCEAN) && BiomeUtil.isIn(se, BiomeTags.DEEP_OCEAN) && context.nextInt(chance) == 0 ? BiomeUtil.getId(biome) : center);
        });
    }
}