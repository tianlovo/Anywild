package org.xlyo.anywild.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.xlyo.anywild.Anywild;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Anywild.MODID);

    private ModBlocks() {}

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    // 垃圾回收器
    public static final RegistryObject<Block> GARBAGE_COLLECTOR =
            BLOCKS.register("garbage_collector",
                    () -> new Block(BlockBehaviour.Properties.of()
                            .strength(3.5f)
                            .sound(SoundType.METAL)));
}
