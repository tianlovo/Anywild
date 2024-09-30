package org.xlyo.anywild.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.xlyo.anywild.Anywild;

/**
 * 创造物品栏
 */
public class ModCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Anywild.MODID);

    private ModCreativeTabs() {}

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> ANYWILD_TAB =
            CREATIVE_MODE_TABS.register("anywild_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.anywild_tab"))
                            .icon(() -> ModItems.GARBAGE_COLLECTOR.get().getDefaultInstance())
                            .displayItems(((itemDisplayParameters, output) -> {
                                // @todo 这里添加物品到创造物品栏
                                output.accept(ModItems.GARBAGE_COLLECTOR.get());
                            }))
                            .build());
}
