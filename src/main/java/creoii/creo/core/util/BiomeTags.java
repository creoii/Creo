package creoii.creo.core.util;

import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.RequiredTagListRegistry;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public final class BiomeTags {
    public static RequiredTagList<Biome> REQUIRED_TAGS = RequiredTagListRegistry.register(Registry.BIOME_KEY, "tags/biomes");;
    public static final Tag<Biome> ANIMAL = biome("animal");
    public static final Tag<Biome> BADLANDS = biome("badlands");
    public static final Tag<Biome> BEACH = biome("beach");
    public static final Tag<Biome> BIRCH = biome("birch");
    public static final Tag<Biome> COLD = biome("cold");
    public static final Tag<Biome> DARK_OAK = biome("dark_oak");
    public static final Tag<Biome> DEEP_OCEAN = biome("deep_ocean");
    public static final Tag<Biome> EDGE = biome("edge");
    public static final Tag<Biome> FOREST = biome("forest");
    public static final Tag<Biome> FLOWER_FOREST = biome("flower_forest");
    public static final Tag<Biome> FROZEN = biome("frozen");
    public static final Tag<Biome> HILLS = biome("hills");
    public static final Tag<Biome> ICE = biome("ice");
    public static final Tag<Biome> ICE_PLAINS = biome("ice_plains");
    public static final Tag<Biome> JUNGLE = biome("jungle");
    public static final Tag<Biome> LAKES = biome("lakes");
    public static final Tag<Biome> LUKEWARM = biome("lukewarm");
    public static final Tag<Biome> MEGA = biome("mega");
    public static final Tag<Biome> MONSTER = biome("monster");
    public static final Tag<Biome> MOOSHROOM = biome("mooshroom");
    public static final Tag<Biome> MODIFIED = biome("modified");
    public static final Tag<Biome> MOUNTAIN = biome("mountain");
    public static final Tag<Biome> NETHER = biome("nether");
    public static final Tag<Biome> OCEAN = biome("ocean");
    public static final Tag<Biome> PLAINS = biome("plains");
    public static final Tag<Biome> PLATEAU = biome("plateau");
    public static final Tag<Biome> RIVER = biome("river");
    public static final Tag<Biome> ROOFED = biome("roofed");
    public static final Tag<Biome> SAVANNA = biome("savanna");
    public static final Tag<Biome> SHALLOW_OCEAN = biome("shallow_ocean");
    public static final Tag<Biome> SHORE = biome("shore");
    public static final Tag<Biome> STONE = biome("stone");
    public static final Tag<Biome> SWAMP = biome("swamp");
    public static final Tag<Biome> TAIGA = biome("taiga");
    public static final Tag<Biome> THE_END = biome("the_end");
    public static final Tag<Biome> UNDERGROUND = biome("underground");
    public static final Tag<Biome> WARM = biome("warm");

    public static Tag.Identified<Biome> biome(String name) {
        return REQUIRED_TAGS.add(name);
    }

    public static TagGroup<Biome> getTagGroup() {
        return REQUIRED_TAGS.getGroup();
    }

    public static void load() {}
}