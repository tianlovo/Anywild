package org.xlyo.anywild.object.entity.block;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.xlyo.anywild.Anywild;
import org.xlyo.anywild.registry.ModBlockEntities;
import org.xlyo.anywild.registry.ModBlocks;
import org.xlyo.anywild.util.entity.ITickableBlockEntity;

public class GarbageCollectorEntity extends BlockEntity implements ITickableBlockEntity {
    /**
     * 清除物品的速率(Tick)，默认20Tick
     */
    private int clearRate;
    private static final String CLEAR_RATE_STR = "ClearRate";

    public GarbageCollectorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GARBAGE_COLLECTOR_ENTITY.get(), pos, state);

        clearRate = 20;
    }

    public int getClearRate() {
        return clearRate;
    }

    public void setClearRate(int clearRate) {
        this.clearRate = clearRate;
        setChanged();
    }

    public boolean getActivate() {
        return getBlockState().getValue(BlockStateProperties.POWERED);
    }

    public void setActivate(boolean activate) {
        getBlockState().setValue(BlockStateProperties.POWERED, activate);
        setChanged();
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);

        CompoundTag anywildNbtData = nbt.getCompound(Anywild.MODID);
        clearRate = anywildNbtData.getInt(CLEAR_RATE_STR);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var anywildNbtData = new CompoundTag();
        anywildNbtData.putInt(CLEAR_RATE_STR, clearRate);
        nbt.put(Anywild.MODID, anywildNbtData);
    }

    @Override
    public void tick() {
        if (level == null || level.isClientSide()) return;
        // 红石未激活 或 速率限制
        if (!getActivate() || level.getGameTime() % clearRate != 0) return;
        clearNearbyContainers(level, getBlockPos(), getBlockState());
    }

    private void clearNearbyContainers(Level world, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(BlockStateProperties.FACING);

        for (Direction direction : Direction.values()) {
            if (direction == facing) continue;
            BlockPos neighborPos = pos.relative(direction);
            BlockEntity neighborEntity = world.getBlockEntity(neighborPos);
            if (!(neighborEntity instanceof Container container)) continue;
            container.clearContent(); // 清空容器内容
        }
    }
}
