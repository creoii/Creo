package creoii.creo.core.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class OverrideBlockSettings {
    public Material material;
    public MapColor mapColor;
    public boolean collidable;
    public BlockSoundGroup soundGroup;
    public int luminance;
    public float resistance;
    public float hardness;
    public float bounciness;
    public boolean toolRequired;
    public boolean randomTicks;
    public float slipperiness;
    public float velocityMultiplier;
    public float jumpVelocityMultiplier;
    public float slideVelocity;
    public boolean opaque;
    public boolean allowsSpawning;
    public boolean solid;
    public boolean suffocation;
    public boolean blockVision;
    public boolean postProcess;
    public boolean emissiveLighting;
    public boolean dynamicBounds;

    public OverrideBlockSettings(Material material, MapColor mapColor) {
        this.material = material;
        this.mapColor = mapColor;
        this.collidable = true;
        this.soundGroup = BlockSoundGroup.STONE;
        this.luminance = 0;
        this.slipperiness = 0.6F;
        this.bounciness = 0.0F;
        this.velocityMultiplier = 1.0F;
        this.jumpVelocityMultiplier = 1.0F;
        this.slideVelocity = 0.0F;
        this.opaque = true;
        this.allowsSpawning = false;
        this.solid = this.material.blocksLight();
        this.suffocation = this.material.blocksMovement();
        this.blockVision = suffocation;
        this.postProcess = false;
        this.emissiveLighting = false;
    }

    public AbstractBlock.Settings toDefaultSettings() {
        AbstractBlock.Settings settings = AbstractBlock.Settings.of(this.material, this.mapColor);
        settings.strength(this.hardness, this.resistance);
        if (!this.collidable) settings.noCollision();
        if (this.randomTicks) settings.ticksRandomly();
        if (this.luminance > 0) settings.luminance((state) -> this.luminance);
        settings.sounds(this.soundGroup);
        settings.slipperiness(this.slipperiness);
        settings.velocityMultiplier(this.velocityMultiplier);
        settings.jumpVelocityMultiplier(this.jumpVelocityMultiplier);
        if (this.dynamicBounds) settings.dynamicBounds();
        if (!this.opaque) settings.nonOpaque();
        if (this.toolRequired) settings.requiresTool();
        return settings;
    }

    public static OverrideBlockSettings toOverrideSettings(Block block) {
        OverrideBlockSettings settings = new OverrideBlockSettings(block.getDefaultState().getMaterial(), block.getDefaultMapColor());
        settings.strength(block.getHardness(), block.getBlastResistance());
        if (block.hasRandomTicks(block.getDefaultState())) settings.ticksRandomly();
        settings.sounds(block.getSoundGroup(block.getDefaultState()));
        settings.slipperiness(block.getSlipperiness());
        settings.velocityMultiplier(block.getVelocityMultiplier());
        settings.jumpVelocityMultiplier(block.getVelocityMultiplier());
        return settings;
    }

    public OverrideBlockSettings noCollision() {
        this.collidable = false;
        this.opaque = false;
        return this;
    }

    public OverrideBlockSettings nonOpaque() {
        this.opaque = false;
        return this;
    }

    public OverrideBlockSettings slipperiness(float slipperiness) {
        this.slipperiness = slipperiness;
        return this;
    }

    public OverrideBlockSettings bounciness(float bounciness) {
        this.bounciness = bounciness;
        return this;
    }

    public OverrideBlockSettings velocityMultiplier(float velocityMultiplier) {
        this.velocityMultiplier = velocityMultiplier;
        return this;
    }

    public OverrideBlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
        this.jumpVelocityMultiplier = jumpVelocityMultiplier;
        return this;
    }

    public OverrideBlockSettings slideVelocity(float slideVelocity) {
        this.slideVelocity = slideVelocity;
        return this;
    }

    public OverrideBlockSettings sounds(BlockSoundGroup soundGroup) {
        this.soundGroup = soundGroup;
        return this;
    }

    public OverrideBlockSettings luminance(int luminance) {
        this.luminance = luminance;
        return this;
    }

    public OverrideBlockSettings strength(float hardness, float resistance) {
        return this.hardness(hardness).resistance(resistance);
    }

    public OverrideBlockSettings breakInstantly() {
        return this.strength(0.0F);
    }

    public OverrideBlockSettings strength(float strength) {
        this.strength(strength, strength);
        return this;
    }

    public OverrideBlockSettings ticksRandomly() {
        this.randomTicks = true;
        return this;
    }

    public OverrideBlockSettings dynamicBounds() {
        this.dynamicBounds = true;
        return this;
    }

    public OverrideBlockSettings allowsSpawning(boolean allowsSpawning) {
        this.allowsSpawning = allowsSpawning;
        return this;
    }

    public OverrideBlockSettings solidBlock(boolean solid) {
        this.solid = solid;
        return this;
    }

    public OverrideBlockSettings suffocates(boolean suffocates) {
        this.suffocation = suffocates;
        return this;
    }

    public OverrideBlockSettings blockVision(boolean blockVision) {
        this.blockVision = blockVision;
        return this;
    }

    public OverrideBlockSettings postProcess(boolean postProcess) {
        this.postProcess = postProcess;
        return this;
    }

    public OverrideBlockSettings emissiveLighting(boolean emissive) {
        this.emissiveLighting = emissive;
        return this;
    }

    public OverrideBlockSettings requiresTool() {
        this.toolRequired = true;
        return this;
    }

    public OverrideBlockSettings mapColor(MapColor color) {
        this.mapColor = color;
        return this;
    }

    public OverrideBlockSettings hardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public OverrideBlockSettings resistance(float resistance) {
        this.resistance = Math.max(0.0F, resistance);
        return this;
    }
}