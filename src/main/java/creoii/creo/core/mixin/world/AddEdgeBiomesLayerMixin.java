package creoii.creo.core.mixin.world;

import creoii.creo.core.util.BiomeUtil;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
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
    private void sample(LayerRandomnessSource rand, int n, int e, int s, int w, int center, CallbackInfoReturnable<Integer> cir) {
        Random random = new Random();

        BiomeUtil.BIOME_BEACHES.forEach((key, value) -> {
            Biome biome = BuiltinRegistries.BIOME.get(key);
            Biome edge = BuiltinRegistries.BIOME.get(value.beach());

            if (center == BuiltinRegistries.BIOME.getRawId(biome)) {
                if (BiomeUtil.isShallowOcean(n) || BiomeUtil.isShallowOcean(e) || BiomeUtil.isShallowOcean(s) || BiomeUtil.isShallowOcean(w)) {
                    cir.setReturnValue(BuiltinRegistries.BIOME.getRawId(edge));
                }
            }
        });
    }
}