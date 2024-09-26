package org.xlyo.anywild.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.xlyo.anywild.Anywild;
import org.xlyo.anywild.enchantment.GamblerEnchantment;
import org.xlyo.anywild.registry.ModEnchantments;

@Mod.EventBusSubscriber(modid = Anywild.MODID)
public class ModEventHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            ItemStack weapon = attacker.getMainHandItem();
            int level = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.GAMBLER.get(), weapon);
            LivingEntity target = event.getEntity();

            if (level > 0) {
                // 调用自定义附魔效果
                ((GamblerEnchantment) ModEnchantments.GAMBLER.get())
                        .onEntityDamaged(attacker, target, level);
            }
        }
    }
}
