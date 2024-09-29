package org.xlyo.anywild.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.xlyo.anywild.Anywild;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Anywild.MODID);

    private ModItems() {}

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // 垃圾回收器
    public static final RegistryObject<Item> GARBAGE_COLLECTOR =
            ITEMS.register("garbage_collector",
                    () -> new BlockItem(ModBlocks.GARBAGE_COLLECTOR.get(), new Item.Properties()));
}
