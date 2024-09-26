package org.xlyo.anywild.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.xlyo.anywild.entity.projectile.ExplosiveArrowEntity;
import org.xlyo.anywild.registry.ModEntityTypes;

public class ExplosiveArrowItem extends ArrowItem {
    public ExplosiveArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public AbstractArrow createArrow(@NotNull Level world,
                                     @NotNull ItemStack stack,
                                     @NotNull LivingEntity shooter) {
        // 创建并返回自定义的爆炸箭实体
        return new ExplosiveArrowEntity(ModEntityTypes.EXPLOSIVE_ARROW.get(), shooter, world);
    }

    @Override
    public boolean isInfinite(
            @NotNull ItemStack stack,
            @NotNull ItemStack bow,
            @NotNull Player player) {
        int enchant = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant > 0 && this.getClass() == ExplosiveArrowItem.class;
    }
}
