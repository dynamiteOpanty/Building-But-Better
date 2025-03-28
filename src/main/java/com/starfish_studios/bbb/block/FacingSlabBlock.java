package com.starfish_studios.bbb.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FacingSlabBlock extends SlabBlock {
    public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;


    public static final VoxelShape SLAB_BOTTOM_UP = Block.box(0, 0, 0, 16, 8, 16);
    public static final VoxelShape SLAB_BOTTOM_DOWN = Block.box(0, 8, 0, 16, 16, 16);
    public static final VoxelShape SLAB_BOTTOM_NORTH = Block.box(0, 0, 8, 16, 16, 16);
    public static final VoxelShape SLAB_BOTTOM_EAST = Block.box(0, 0, 0, 8, 16, 16);
    public static final VoxelShape SLAB_BOTTOM_SOUTH = Block.box(0, 0, 0, 16, 16, 8);
    public static final VoxelShape SLAB_BOTTOM_WEST = Block.box(8, 0, 0, 16, 16, 16);

    public static final VoxelShape SLAB_TOP_UP = Block.box(0, 8, 0, 16, 16, 16);
    public static final VoxelShape SLAB_TOP_DOWN = Block.box(0, 0, 0, 16, 8, 16);
    public static final VoxelShape SLAB_TOP_NORTH = Block.box(0, 0, 8, 16, 16, 16);
    public static final VoxelShape SLAB_TOP_EAST = Block.box(8, 0, 0, 16, 16, 16);
    public static final VoxelShape SLAB_TOP_SOUTH = Block.box(0, 0, 0, 16, 16, 8);
    public static final VoxelShape SLAB_TOP_WEST = Block.box(0, 0, 0, 8, 16, 16);

    public static final VoxelShape SLAB_DOUBLE = Block.box(0, 0, 0, 16, 16, 16);


    public FacingSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TYPE, SlabType.BOTTOM)
                .setValue(FACING, Direction.UP)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(TYPE)) {
            case BOTTOM -> switch (state.getValue(FACING)) {
                case NORTH -> SLAB_BOTTOM_NORTH;
                case SOUTH -> SLAB_BOTTOM_SOUTH;
                case EAST -> SLAB_BOTTOM_EAST;
                case WEST -> SLAB_BOTTOM_WEST;
                case UP -> SLAB_BOTTOM_UP;
                case DOWN -> SLAB_BOTTOM_DOWN;
            };
            case TOP -> switch (state.getValue(FACING)) {
                case NORTH -> SLAB_TOP_NORTH;
                case SOUTH -> SLAB_TOP_SOUTH;
                case EAST -> SLAB_TOP_EAST;
                case WEST -> SLAB_TOP_WEST;
                case UP -> SLAB_TOP_UP;
                case DOWN -> SLAB_TOP_DOWN;
            };
            case DOUBLE -> SLAB_DOUBLE;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());

        if (blockState.is(this) && blockState.getValue(TYPE) != SlabType.DOUBLE) {
            return blockState.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false);
        } else {
            FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
            return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        if (blockState.getValue(FACING) != blockPlaceContext.getClickedFace()) return false;
        return blockPlaceContext.getItemInHand().is(this.asItem()) && blockState.getValue(TYPE) != SlabType.DOUBLE || super.canBeReplaced(blockState, blockPlaceContext);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinitionBuilder) {
        stateDefinitionBuilder.add(TYPE, FACING, WATERLOGGED);
    }
}
