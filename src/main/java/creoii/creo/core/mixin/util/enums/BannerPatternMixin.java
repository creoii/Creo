package creoii.creo.core.mixin.util.enums;

import creoii.creo.core.util.data.EnumRecords;
import net.minecraft.block.entity.BannerPattern;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BannerPattern.class)
public abstract class BannerPatternMixin {
    @Invoker("<init>")
    @SuppressWarnings("InvokerTarget")
    private static BannerPattern create(String enumName, int ordinal, String name, String id, boolean hasPatternItem) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable @SuppressWarnings("ShadowTarget") private static BannerPattern[] field_11833;

    static {
        ArrayList<BannerPattern> values =  new ArrayList<>(Arrays.asList(field_11833));
        EnumRecords.BANNER_PATTERNS.forEach((pattern) -> {
            values.add(create(pattern.name().toUpperCase(), values.size() + 1, pattern.name(), pattern.id(), pattern.hasPatternItem()));
        });

        field_11833 = values.toArray(new BannerPattern[0]);
    }
}