package org.xlyo.anywild.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.xlyo.anywild.Anywild;
import org.xlyo.anywild.item.ExplosiveArrowItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Anywild.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // 注册爆炸箭
    public static final RegistryObject<Item> EXPLOSIVE_ARROW =
            ITEMS.register("explosive_arrow",
            () -> new ExplosiveArrowItem(
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT))); // 设置在战斗物品栏中
}
