package creoii.creo.core.mixin.tag;

import com.google.common.collect.ImmutableSet;
import creoii.creo.core.util.tags.BiomeTags;
import creoii.creo.core.util.tags.EnchantmentTags;
import net.minecraft.tag.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(RequiredTagListRegistry.class)
public class RequiredTagListRegistryMixin {
    @Inject(method = "getBuiltinTags", at = @At("RETURN"), cancellable = true)
    private static void creo$addBiomeTagList(CallbackInfoReturnable<Set<RequiredTagList<?>>> cir) {
        cir.setReturnValue(ImmutableSet.of(BlockTags.REQUIRED_TAGS, ItemTags.REQUIRED_TAGS, FluidTags.REQUIRED_TAGS, EntityTypeTags.REQUIRED_TAGS, GameEventTags.REQUIRED_TAGS, BiomeTags.REQUIRED_TAGS, EnchantmentTags.REQUIRED_TAGS));
    }
}