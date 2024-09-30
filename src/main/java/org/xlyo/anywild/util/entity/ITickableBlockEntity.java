package org.xlyo.anywild.util.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

/**
 * 工具类，可以使BlockEntity拥有tick方法
 */
public interface ITickableBlockEntity {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level) {
        return level.isClientSide() ? null :
                (_level, _pos, _state, _blockEntity) -> ((ITickableBlockEntity)_blockEntity).tick();
    }
}
