package creoii.creo.core.util.tags;

import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.RequiredTagListRegistry;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public final class BiomeTags {
    public static RequiredTagList<Biome> REQUIRED_TAGS;
    public static Tag<Biome> ANIMAL;
    public static Tag<Biome> BADLANDS;
    public static Tag<Biome> BEACH;
    public static Tag<Biome> BIRCH;
    public static Tag<Biome> COLD;
    public static Tag<Biome> DARK_OAK;
    public static Tag<Biome> DEEP_OCEAN;
    public static Tag<Biome> EDGE;
    public static Tag<Biome> FOREST;
    public static Tag<Biome> FLOWER_FOREST;
    public static Tag<Biome> FROZEN;
    public static Tag<Biome> HILLS;
    public static Tag<Biome> ICE;
    public static Tag<Biome> ICE_PLAINS;
    public static Tag<Biome> JUNGLE;
    public static Tag<Biome> LAKES;
    public static Tag<Biome> LUKEWARM;
    public static Tag<Biome> MEGA;
    public static Tag<Biome> MONSTER;
    public static Tag<Biome> MOOSHROOM;
    public static Tag<Biome> MODIFIED;
    public static Tag<Biome> MOUNTAIN;
    public static Tag<Biome> NETHER;
    public static Tag<Biome> OCEAN;
    public static Tag<Biome> PLAINS;
    public static Tag<Biome> PLATEAU;
    public static Tag<Biome> RIVER;
    public static Tag<Biome> ROOFED;
    public static Tag<Biome> SAVANNA;
    public static Tag<Biome> SHALLOW_OCEAN;
    public static Tag<Biome> SHORE;
    public static Tag<Biome> STONE;
    public static Tag<Biome> SWAMP;
    public static Tag<Biome> TAIGA;
    public static Tag<Biome> THE_END;
    public static Tag<Biome> UNDERGROUND;
    public static Tag<Biome> WARM;

    public static Tag.Identified<Biome> biome(String name) {
        return REQUIRED_TAGS.add(name);
    }

    public static TagGroup<Biome> getTagGroup() {
        return REQUIRED_TAGS.getGroup();
    }

    static {
        REQUIRED_TAGS = RequiredTagListRegistry.register(Registry.BIOME_KEY, "tags/biomes");
        ANIMAL = biome("animal");
        BADLANDS = biome("badlands");
        BEACH = biome("beach");
        BIRCH = biome("birch");
        COLD = biome("cold");
        DARK_OAK = biome("dark_oak");
        DEEP_OCEAN = biome("deep_ocean");
        EDGE = biome("edge");
        FOREST = biome("forest");
        FLOWER_FOREST = biome("flower_forest");
        FROZEN = biome("frozen");
        HILLS = biome("hills");
        ICE = biome("ice");
        ICE_PLAINS = biome("ice_plains");
        JUNGLE = biome("jungle");
        LAKES = biome("lakes");
        LUKEWARM = biome("lukewarm");
        MEGA = biome("mega");
        MONSTER = biome("monster");
        MOOSHROOM = biome("mooshroom");
        MODIFIED = biome("modified");
        MOUNTAIN = biome("mountain");
        NETHER = biome("nether");
        OCEAN = biome("ocean");
        PLAINS = biome("plains");
        PLATEAU = biome("plateau");
        RIVER = biome("river");
        ROOFED = biome("roofed");
        SAVANNA = biome("savanna");
        SHALLOW_OCEAN = biome("shallow_ocean");
        SHORE = biome("shore");
        STONE = biome("stone");
        SWAMP = biome("swamp");
        TAIGA = biome("taiga");
        THE_END = biome("the_end");
        UNDERGROUND = biome("underground");
        WARM = biome("warm");
    }
}