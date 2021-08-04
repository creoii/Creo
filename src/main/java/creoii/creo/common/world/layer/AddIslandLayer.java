package creoii.creo.common.world.layer;

import creoii.creo.core.util.BiomeUtil;
import creoii.creo.core.util.tags.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.layer.type.DiagonalCrossSamplingLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;

public enum AddIslandLayer implements DiagonalCrossSamplingLayer {
    DEFAULT(new BiomeUtil.IslandBiome(BiomeKeys.BAMBOO_JUNGLE, 10));

    private final BiomeUtil.IslandBiome island;

    private AddIslandLayer(BiomeUtil.IslandBiome island) {
        this.island = island;
    }

    public int sample(LayerRandomnessSource context, int sw, int se, int ne, int nw, int center) {
        return BiomeUtil.isIn(center, BiomeTags.SHALLOW_OCEAN) && BiomeUtil.isIn(nw, BiomeTags.SHALLOW_OCEAN) && BiomeUtil.isIn(sw, BiomeTags.SHALLOW_OCEAN) && BiomeUtil.isIn(ne, BiomeTags.SHALLOW_OCEAN) && BiomeUtil.isIn(se, BiomeTags.SHALLOW_OCEAN) && context.nextInt(island.chance()) == 0 ? BiomeUtil.getId(island.biome()) : center;
    }
}