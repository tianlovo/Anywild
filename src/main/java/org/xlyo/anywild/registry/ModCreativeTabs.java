package org.xlyo.anywild.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTabs {
    public static final CreativeModeTab ANYWILD_TAB = new CreativeModeTab("anywild_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.EXPLOSIVE_ARROW.get());
        }
    };
}
