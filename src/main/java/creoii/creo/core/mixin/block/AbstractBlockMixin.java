package creoii.creo.core.mixin.block;

import creoii.creo.core.util.BlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "scheduledTick", at = @At("HEAD"))
    private void creo$affectedByGravityTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (state.isIn(BlockTags.AFFECTED_BY_GRAVITY) && !(state.getBlock() instanceof FallingBlock)) {
            if (FallingBlock.canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
                FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos));
                world.spawnEntity(fallingBlockEntity);
            }
        }
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"), cancellable = true)
    private void creo$affectedByGravityNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (state.isIn(BlockTags.AFFECTED_BY_GRAVITY) && !(state.getBlock() instanceof FallingBlock)) {
            world.getBlockTickScheduler().schedule(pos, state.getBlock(), 2);
            cir.setReturnValue(state.getStateForNeighborUpdate(direction, neighborState, world, pos, neighborPos));
        }
    }

    @Inject(method = "onBlockAdded", at = @At("HEAD"))
    private void creo$affectedByGravityAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        if (state.isIn(BlockTags.AFFECTED_BY_GRAVITY) && !(state.getBlock() instanceof FallingBlock)) {
            world.getBlockTickScheduler().schedule(pos, state.getBlock(), 2);
        }
    }
}