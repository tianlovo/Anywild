package org.xlyo.anywild.object.block;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xlyo.anywild.object.entity.block.GarbageCollectorEntity;
import org.xlyo.anywild.registry.ModBlockEntities;
import org.xlyo.anywild.util.entity.ITickableBlockEntity;

/**
 * 方块：垃圾回收器
 */
public class GarbageCollector extends Block implements EntityBlock {
    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public GarbageCollector() {
        super(BlockBehaviour.Properties.of()
                .strength(3.5f)
                .sound(SoundType.METAL));

        // 默认面朝南方，红石未激活
        registerDefaultState(getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false));
    }

    /// 方块位置与放置
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    /// 实体绑定与使用
    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return ModBlockEntities.GARBAGE_COLLECTOR_ENTITY.get().create(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    @NotNull
    public InteractionResult use(@NotNull BlockState blockState,
                                 @NotNull Level level,
                                 @NotNull BlockPos blockPos,
                                 @NotNull Player player,
                                 @NotNull InteractionHand interactionHand,
                                 @NotNull BlockHitResult blockHitResult) {
        // @todo 右键方块打开管理界面
        if (!level.isClientSide() &&
                interactionHand == InteractionHand.MAIN_HAND &&
                level.getBlockEntity(blockPos) instanceof GarbageCollectorEntity garbageCollector) {
            LogUtils.getLogger().info("clear rate: {}", garbageCollector.getClearRate());
            garbageCollector.setClearRate(114);
            LogUtils.getLogger().info("clear rate: {}", garbageCollector.getClearRate());
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    /// Tick
    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            @NotNull Level level,
            @NotNull BlockState blockState,
            @NotNull BlockEntityType<T> blockEntityType) {
        return ITickableBlockEntity.getTickerHelper(level);
    }
}
