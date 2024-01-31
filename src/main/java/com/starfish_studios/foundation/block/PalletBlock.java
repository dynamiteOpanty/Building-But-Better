package com.starfish_studios.foundation.block;

import com.starfish_studios.foundation.block.properties.FoundationBlockStateProperties;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static org.apache.logging.log4j.Level.getLevel;

public class PalletBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    protected static final VoxelShape BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0);
    protected static final VoxelShape TOP_AABB = Block.box(0.0, 10.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0, 0.0, 10.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape EAST_AABB = Block.box(0.0, 0.0, 0.0, 6.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 6.0);
    protected static final VoxelShape WEST_AABB = Block.box(10.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    protected static final VoxelShape BOTTOM_AABB_LAYER_ONE = Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);
    protected static final VoxelShape BOTTOM_AABB_LAYER_TWO = Block.box(0.0, 0.3, 0.0, 16.0, 6.0, 16.0);
    protected static final VoxelShape TOP_AABB_LAYER_ONE = Block.box(0.0, 10.0, 0.0, 16.0, 13.0, 16.0);
    protected static final VoxelShape TOP_AABB_LAYER_TWO = Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape NORTH_AABB_LAYER_ONE = Block.box(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape NORTH_AABB_LAYER_TWO = Block.box(0.0, 0.0, 10.0, 16.0, 13.0, 16.0);

    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final BooleanProperty LAYER_ONE = FoundationBlockStateProperties.LAYER_ONE;
    public static final BooleanProperty LAYER_TWO = FoundationBlockStateProperties.LAYER_TWO;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PalletBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(HALF, Half.BOTTOM)
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
                .setValue(LAYER_ONE, true)
                .setValue(LAYER_TWO, true));
    }

    public VoxelShape getInteractionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (blockState.getValue(HALF) == Half.BOTTOM) {
            assert Minecraft.getInstance().hitResult != null;
            if (Minecraft.getInstance().hitResult.getLocation().y - (double)blockPos.getY() < 0.25) {
                return BOTTOM_AABB_LAYER_ONE;
            } else {
                return BOTTOM_AABB_LAYER_TWO;
            }
        }

        return super.getShape(blockState, blockGetter, blockPos, collisionContext);
    }


    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (blockState.getValue(OPEN)) {
            return switch (blockState.getValue(FACING)) {
                case NORTH -> NORTH_AABB;
                case SOUTH -> SOUTH_AABB;
                case WEST -> WEST_AABB;
                case EAST -> EAST_AABB;
                default -> BOTTOM_AABB;
            };
        } else if (blockState.getValue(HALF) == Half.TOP) {
            return TOP_AABB;
        } else return BOTTOM_AABB;
    }

    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    protected void playSound(@Nullable Player player, Level level, BlockPos blockPos, boolean bl) {
        level.playSound(player, blockPos, bl ? SoundEvents.WOODEN_TRAPDOOR_OPEN : SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.gameEvent(player, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).is(ItemTags.AXES)) {
            if (blockHitResult.getLocation().z - (double)blockPos.getZ() > 0.5 && blockState.getValue(FACING) == Direction.NORTH) {
                if (blockHitResult.getLocation().z - (double)blockPos.getZ() < 0.75) {
                    blockState = blockState.cycle(LAYER_ONE);
                } else {
                    blockState = blockState.cycle(LAYER_TWO);
                }
                if (!blockState.getValue(LAYER_ONE) && !blockState.getValue(LAYER_TWO)) {
                    blockState = blockState.setValue(LAYER_ONE, true).setValue(LAYER_TWO, true);
                }
                level.setBlock(blockPos, blockState, 2);
                level.playSound(player, blockPos, Blocks.SCAFFOLDING.getSoundType(level.getBlockState(blockPos)).getPlaceSound(), player.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else if (blockHitResult.getLocation().z - (double)blockPos.getZ() < 0.5 && blockState.getValue(FACING) == Direction.SOUTH) {
                if (blockHitResult.getLocation().z - (double)blockPos.getZ() > 0.25) {
                    blockState = blockState.cycle(LAYER_ONE);
                } else {
                    blockState = blockState.cycle(LAYER_TWO);
                }
                if (!blockState.getValue(LAYER_ONE) && !blockState.getValue(LAYER_TWO)) {
                    blockState = blockState.setValue(LAYER_ONE, true).setValue(LAYER_TWO, true);
                }
                level.setBlock(blockPos, blockState, 2);
                level.playSound(player, blockPos, Blocks.SCAFFOLDING.getSoundType(level.getBlockState(blockPos)).getPlaceSound(), player.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else if (blockHitResult.getLocation().x - (double)blockPos.getX() > 0.5 && blockState.getValue(FACING) == Direction.WEST) {
                if (blockHitResult.getLocation().x - (double) blockPos.getX() < 0.75) {
                    blockState = blockState.cycle(LAYER_ONE);
                } else {
                    blockState = blockState.cycle(LAYER_TWO);
                }
                if (!blockState.getValue(LAYER_ONE) && !blockState.getValue(LAYER_TWO)) {
                    blockState = blockState.setValue(LAYER_ONE, true).setValue(LAYER_TWO, true);
                }
                level.setBlock(blockPos, blockState, 2);
                level.playSound(player, blockPos, Blocks.SCAFFOLDING.getSoundType(level.getBlockState(blockPos)).getPlaceSound(), player.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else if (blockHitResult.getLocation().x - (double)blockPos.getX() < 0.5 && blockState.getValue(FACING) == Direction.EAST) {
                if (blockHitResult.getLocation().x - (double)blockPos.getX() > 0.25) {
                    blockState = blockState.cycle(LAYER_ONE);
                } else {
                    blockState = blockState.cycle(LAYER_TWO);
                }
                if (!blockState.getValue(LAYER_ONE) && !blockState.getValue(LAYER_TWO)) {
                    blockState = blockState.setValue(LAYER_ONE, true).setValue(LAYER_TWO, true);
                }
                level.setBlock(blockPos, blockState, 2);
                level.playSound(player, blockPos, Blocks.SCAFFOLDING.getSoundType(level.getBlockState(blockPos)).getPlaceSound(), player.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else if (blockHitResult.getLocation().y - (double)blockPos.getY() < 0.5 && blockState.getValue(HALF) == Half.BOTTOM) {
                if (blockHitResult.getLocation().y - (double)blockPos.getY() < 0.25) {
                    blockState = blockState.cycle(LAYER_ONE);
                } else {
                    blockState = blockState.cycle(LAYER_TWO);
                }
                if (!blockState.getValue(LAYER_ONE) && !blockState.getValue(LAYER_TWO)) {
                    blockState = blockState.setValue(LAYER_ONE, true).setValue(LAYER_TWO, true);
                }
                level.setBlock(blockPos, blockState, 2);
                level.playSound(player, blockPos, Blocks.SCAFFOLDING.getSoundType(level.getBlockState(blockPos)).getPlaceSound(), player.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } if (blockHitResult.getLocation().y - (double)blockPos.getY() > 0.5 && blockState.getValue(HALF) == Half.TOP) {
                if (blockHitResult.getLocation().y - (double)blockPos.getY() < 0.75) {
                    blockState = blockState.cycle(LAYER_ONE);
                } else {
                    blockState = blockState.cycle(LAYER_TWO);
                }
                if (!blockState.getValue(LAYER_ONE) && !blockState.getValue(LAYER_TWO)) {
                    blockState = blockState.setValue(LAYER_ONE, true).setValue(LAYER_TWO, true);
                }
                level.setBlock(blockPos, blockState, 2);
                level.playSound(player, blockPos, Blocks.SCAFFOLDING.getSoundType(level.getBlockState(blockPos)).getPlaceSound(), player.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
         else if (player.isCrouching()) {
            blockState = blockState.cycle(OPEN);
            level.setBlock(blockPos, blockState, 2);
            if (blockState.getValue(WATERLOGGED)) {
                level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }
            this.playSound(player, level, blockPos, blockState.getValue(OPEN));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.defaultBlockState();
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        Direction direction = blockPlaceContext.getClickedFace();
        if (!blockPlaceContext.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
            blockState = blockState.setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(HALF, blockPlaceContext.getClickLocation().y - (double)blockPlaceContext.getClickedPos().getY() > 0.5 ? Half.TOP : Half.BOTTOM);
        } else {
            blockState = blockState.setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(HALF, direction == Direction.UP ? Half.BOTTOM : Half.TOP);
        }

        return blockState.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, WATERLOGGED, OPEN, LAYER_ONE, LAYER_TWO);
    }
}