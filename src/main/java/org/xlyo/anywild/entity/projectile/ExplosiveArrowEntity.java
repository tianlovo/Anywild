package org.xlyo.anywild.entity.projectile;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.xlyo.anywild.registry.ModItems;

public class ExplosiveArrowEntity extends AbstractArrow {
    private static final Logger LOGGER = LogUtils.getLogger();

    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);

        // 检查是否在服务器端执行
        if (!this.level.isClientSide) {
            // 在箭头命中后产生爆炸，爆炸威力设为1个TNT的威力(4.0F)
            this.level.explode(
                    this,
                    this.getX(), this.getY(), this.getZ(),
                    4.0F,
                    Explosion.BlockInteraction.BREAK);

            // 移除箭头实体
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        // 检查是否落在地面
        if (this.inGround) {
            // 如果箭头落在地面也爆炸
            this.level.explode(
                    this,
                    this.getX(), this.getY(), this.getZ(),
                    4.0F,
                    Explosion.BlockInteraction.BREAK);
            this.discard();
        }
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        // 如果玩家开启了捡回箭的功能，可以设置返回箭物品
        return new ItemStack(ModItems.EXPLOSIVE_ARROW.get());
    }
}
