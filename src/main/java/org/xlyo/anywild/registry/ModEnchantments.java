package org.xlyo.anywild.registry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import org.xlyo.anywild.Anywild;
import org.xlyo.anywild.enchantment.GamblerEnchantment;

/**
 * 模组所有的附魔
 */
@Mod.EventBusSubscriber(modid = Anywild.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(
            ForgeRegistries.ENCHANTMENTS, Anywild.MODID);

    // 赌徒
    public static final RegistryObject<Enchantment> GAMBLER =
            ENCHANTMENTS.register("gambler",
                    () -> new GamblerEnchantment(
                            Enchantment.Rarity.RARE,
                            EnchantmentCategory.WEAPON,
                            EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
