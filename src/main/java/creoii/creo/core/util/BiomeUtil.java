package creoii.creo.core.util;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BiomeUtil {
    public static Map<RegistryKey<Biome>, BiomeBeach> BIOME_BEACHES = new HashMap<>();
    public static Map<RegistryKey<Biome>, BiomeEdge> BIOME_EDGES = new HashMap<>();
    public static Map<RegistryKey<Biome>, BiomeVariant> BIOME_VARIANTS = new HashMap<>();
    public static Map<RegistryKey<Biome>, IslandBiome> ISLAND_BIOMES = new HashMap<>();

    public static boolean isOcean(int id) {
        return id == 44 || id == 45 || id == 0 || id == 46 || id == 10 || id == 47 || id == 48 || id == 24 || id == 49 || id == 50;
    }

    public static boolean isShallowOcean(int id) {
        return id == 44 || id == 45 || id == 0 || id == 46 || id == 10;
    }

    public static boolean isWooded(int id) {
        return id == 4 || id == 5;
    }

    public boolean isBadlands(int id) {
        return id == 37 || id == 38 || id == 39 || id == 165 || id == 166 || id == 167;
    }

    public static void addBeachToBiome(RegistryKey<Biome> center, BiomeBeach beach) {
        BIOME_BEACHES.put(center, beach);
    }

    public static void addEdgeToBiome(RegistryKey<Biome> center, BiomeEdge edge) {
        BIOME_EDGES.put(center, edge);
    }

    public static void addVariantToBiome(RegistryKey<Biome> original, BiomeVariant variant) {
        BIOME_VARIANTS.put(original, variant);
    }

    public static void makeBiomeIsland(RegistryKey<Biome> biome, IslandBiome island) {
        ISLAND_BIOMES.put(biome, island);
    }

    public record BiomeBeach(RegistryKey<Biome> beach, int weight) {}
    public record BiomeEdge(RegistryKey<Biome> edge, RegistryKey<Biome> nextTo, Optional<RegistryKey<Biome>> blacklist) {}
    public record BiomeVariant(RegistryKey<Biome> original, int weight) {}
    public record IslandBiome(RegistryKey<Biome> biome) {}
}
