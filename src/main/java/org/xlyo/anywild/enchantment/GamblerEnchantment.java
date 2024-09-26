package org.xlyo.anywild.enchantment;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.xlyo.anywild.registry.ModDamageTypes;
import org.xlyo.anywild.util.MathUtil;

import java.util.Random;

/**
 * 附魔：赌徒
 * <br/>
 * 附魔等级：1~5
 * <br/>
 * 附魔效果：
 * 1. 攻击时触发，使用者有50%的几率直接杀死目标，或者有50%的几率直接杀死使用者。
 * 2. 随着附魔等级的增加，使用者死亡几率降低，被攻击者死亡几率增加。
 * 3. 幸运效果每一级增加使用者 5% 的存活概率，最大存活概率限制为 99%。
 */
public class GamblerEnchantment extends Enchantment {
    public GamblerEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMaxLevel() {
        return 5; // 定义最大附魔等级
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem().canPerformAction(stack, net.minecraftforge.common.ToolActions.SWORD_DIG) ||
                stack.getItem().canPerformAction(stack, net.minecraftforge.common.ToolActions.AXE_DIG);
    }

    /**
     * 攻击时触发的效果
     *
     * @param user   使用者
     * @param target 目标
     * @param level  附魔等级
     */
    public void onEntityDamaged(LivingEntity user, LivingEntity target, int level) {
        if (user == null || target == null) {
            return;
        }

        level = MathUtil.clamp(level, 1, getMaxLevel());

        // 基础概率50%，随着等级增加，玩家死亡几率降低，被攻击者死亡几率增加
        int deathChance = 50 + level; // 被攻击者的死亡概率 (基础 50% + 附魔等级)
        if (user.hasEffect(MobEffects.LUCK)) {
            deathChance = MathUtil.clamp(
                    deathChance + user.getEffect(MobEffects.LUCK).getAmplifier() * 5,
                    deathChance, 99);
        }
        if (user.hasEffect(MobEffects.UNLUCK)) {
            deathChance = MathUtil.clamp(
                    deathChance - user.getEffect(MobEffects.UNLUCK).getAmplifier() * 5,
                    1, deathChance);
        }

        // 生成1到100的随机数
        int gamblerEffect = new Random().nextInt(100) + 1;

        // 如果随机数小于被攻击者死亡概率，则目标直接死亡
        LivingEntity entity = gamblerEffect <= deathChance ? target : user;

        entity.hurt(ModDamageTypes.GAMBLER, Float.MAX_VALUE); // 直接杀死目标
        if (entity.isAlive() && !(entity instanceof Player)) {
            entity.setHealth(.1F);
            entity.hurt(ModDamageTypes.GAMBLER, Float.MAX_VALUE);
            entity.setHealth(0.0F);
        }
    }
}
