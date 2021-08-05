package creoii.creo.core.util;

import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeUtil {
    public static Map<RegistryKey<Biome>, BiomeBeach> BIOME_BEACHES = new HashMap<>();
    public static Map<RegistryKey<Biome>, BiomeEdge> BIOME_EDGES = new HashMap<>();
    public static Map<RegistryKey<Biome>, BiomeVariant> BIOME_VARIANTS = new HashMap<>();
    public static Map<RegistryKey<Biome>, RegistryKey<Biome>> BIOME_RIVERS = new HashMap<>();
    public static Map<RegistryKey<Biome>, Integer> ISLAND_BIOMES = new HashMap<>();

    public static Biome toBiome(RegistryKey<Biome> key) {
        return BuiltinRegistries.BIOME.get(key);
    }

    public static Biome toBiome(int id) {
        return BuiltinRegistries.BIOME.get(id);
    }

    public static RegistryKey<Biome> toKey(Biome biome) {
        return BuiltinRegistries.BIOME.getKey(biome).get();
    }

    public static RegistryKey<Biome> toKey(int id) {
        return toKey(toBiome(id));
    }

    public static int getId(Biome biome) {
        return BuiltinRegistries.BIOME.getRawId(biome);
    }

    public static int getId(RegistryKey<Biome> key) {
        return getId(BuiltinRegistries.BIOME.get(key));
    }

    public static boolean isIn(Biome biome, Tag<Biome> tag) {
        return tag.contains(biome);
    }

    public static boolean isIn(int biomeId, Tag<Biome> tag) {
        return tag.contains(BuiltinRegistries.BIOME.get(biomeId));
    }

    /**
     * {@link net.fabricmc.fabric.api.biome.v1.OverworldBiomes}
     */
    public static void addBeachToBiome(RegistryKey<Biome> center, BiomeBeach beach) {
        BIOME_BEACHES.put(center, beach);
    }

    /**
     * {@link net.fabricmc.fabric.api.biome.v1.OverworldBiomes}
     */
    public static void addEdgeToBiome(RegistryKey<Biome> center, BiomeEdge edge) {
        BIOME_EDGES.put(center, edge);
    }

    /**
     * {@link net.fabricmc.fabric.api.biome.v1.OverworldBiomes}
     * Seems to not work? Idk
     */
    public static void addVariantToBiome(RegistryKey<Biome> original, BiomeVariant variant) {
        BIOME_VARIANTS.put(original, variant);
    }

    @SuppressWarnings("deprecation")
    public static void addRiverToBiome(RegistryKey<Biome> biome, RegistryKey<Biome> river) {
        OverworldBiomes.setRiverBiome(biome, river);
    }

    @SuppressWarnings("deprecation")
    public static void makeBiomeIsland(RegistryKey<Biome> biome, double chance) {
        OverworldBiomes.addBiomeVariant(BiomeKeys.DEEP_OCEAN, biome, chance);
    }

    public record BiomeBeach(RegistryKey<Biome> beach, int weight) {}
    public record BiomeEdge(RegistryKey<Biome> edge, List<RegistryKey<Biome>> whitelist, List<RegistryKey<Biome>> blacklist) {
        public boolean blacklisted(int b) {
            return blacklist.contains(toKey(b));
        }

        public boolean whitelisted(int b) {
            return whitelist.contains(toKey(b));
        }
    }
    public record BiomeVariant(RegistryKey<Biome> original, int weight) {}
    public record IslandBiome(RegistryKey<Biome> biome, int chance) {}
}