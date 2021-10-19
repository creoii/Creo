package creoii.creo.core.registry;

import creoii.creo.common.world.decorator.HangingLeavesTreeDecorator;
import creoii.creo.core.mixin.world.TreeDecoratorTypeMixin;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class DecoratorRegistry {
    public static TreeDecoratorType<?> HANGING_LEAVES_DECORATOR = TreeDecoratorTypeMixin.callRegister("hanging_leaves_decorator", HangingLeavesTreeDecorator.CODEC);
}