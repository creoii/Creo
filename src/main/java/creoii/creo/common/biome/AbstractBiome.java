package creoii.creo.common.biome;

import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.client.sound.MusicType;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;

public abstract class AbstractBiome {
    private final Identifier name;
    private final Biome biome;
    private final RegistryKey<Biome> key;

    public AbstractBiome(Identifier name) {
        this.name = name;
        this.biome = new Biome.Builder()
                .category(this.getCategory())
                .depth(this.getDepth())
                .downfall(this.getDownfall())
                .precipitation(this.getPrecipitation())
                .scale(this.getScale())
                .temperature(this.getTemperature())
                .effects(new BiomeEffects.Builder()
                        .fogColor(this.getFogColor())
                        .waterColor(this.getWaterColor())
                        .waterFogColor(this.getWaterFogColor())
                        .particleConfig(this.getParticle())
                        .moodSound(this.getMoodSound())
                        .loopSound(this.getLoopSound())
                        .additionsSound(this.getAdditionsSound())
                        .music(MusicType.createIngameMusic(this.getMusicSound()))
                        .build())
                .temperatureModifier(this.getTemperatureModifier())
                .generationSettings(this.getGenerationSettings())
                .spawnSettings(this.getSpawnSettings())
                .build();

        this.key = RegistryKey.of(Registry.BIOME_KEY, this.name);
    }

    public static void register(AbstractBiome biome) {
        Registry.register(BuiltinRegistries.BIOME, biome.getKey().getValue(), biome.getBiome());
    }

    public Identifier getName() {
        return name;
    }

    public Biome getBiome() {
        return biome;
    }

    public RegistryKey<Biome> getKey() {
        return key;
    }

    abstract int getWeight();

    abstract GenerationSettings getGenerationSettings();

    abstract SpawnSettings getSpawnSettings();

    public OverworldClimate[] getClimates() {
        return new OverworldClimate[] {};
    }

    public Biome.Category getCategory() {
        return Biome.Category.PLAINS;
    }

    public Biome.Precipitation getPrecipitation() {
        return Biome.Precipitation.RAIN;
    }

    public Biome.TemperatureModifier getTemperatureModifier() {
        return Biome.TemperatureModifier.NONE;
    }

    public float getTemperature() {
        return 0.7F;
    }

    public float getDownfall() {
        return 0.8F;
    }

    public float getDepth() {
        return 0.125F;
    }

    public float getScale() {
        return 0.05F;
    }

    public int getWaterColor() {
        return 4159204;
    }

    public int getWaterFogColor() {
        return 329011;
    }

    public int getFogColor() {
        return 12638463;
    }

    public BiomeParticleConfig getParticle() {
        return null;
    }

    public SoundEvent getLoopSound() {
        return null;
    }

    public BiomeMoodSound getMoodSound() {
        return null;
    }

    public BiomeAdditionsSound getAdditionsSound() {
        return null;
    }

    public SoundEvent getMusicSound() {
        return null;
    }
}