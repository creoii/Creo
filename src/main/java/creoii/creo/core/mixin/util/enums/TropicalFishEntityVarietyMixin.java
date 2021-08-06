package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.ExtendedEnum;
import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.entity.passive.TropicalFishEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(TropicalFishEntity.Variety.class)
public abstract class TropicalFishEntityVarietyMixin {
    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    public static TropicalFishEntity.Variety create(String enumName, int ordinal, int shape, int pattern) {
        throw new AssertionError();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void registerInit(String enumName, int ordinal, int shape, int pattern, CallbackInfo ci) {
        registerValues();
    }

    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static TropicalFishEntity.Variety[] field_6886;

    @Shadow
    public static TropicalFishEntity.Variety[] values() {
        return new TropicalFishEntity.Variety[0];
    }

    private void registerValues() {
        EnumRecords.TROPICAL_FISH_VARIETIES.forEach((variety) -> Arrays.stream(values()).toList().add(create(variety.name().toUpperCase(), values().length + 1, variety.shape(), variety.pattern())));
        field_6886 = Arrays.stream(values()).toList().toArray(new TropicalFishEntity.Variety[0]);
    }

    static {
        //EnumRecords.TROPICAL_FISH_VARIETIES.forEach((variety) -> Arrays.stream(values()).toList().add(create(variety.name().toUpperCase(), values().length + 1, variety.shape(), variety.pattern())));
        //field_6886 = Arrays.stream(values()).toList().toArray(new TropicalFishEntity.Variety[0]);
    }
}