package org.xlyo.anywild.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.xlyo.anywild.Anywild;
import org.xlyo.anywild.object.entity.block.GarbageCollectorEntity;

/**
 * 模组所有的方块实体
 */
public class ModBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Anywild.MODID);

    private ModBlockEntities() {
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    // 垃圾回收器
    public static final RegistryObject<BlockEntityType<GarbageCollectorEntity>> GARBAGE_COLLECTOR_ENTITY =
            BLOCK_ENTITIES.register("garbage_collector",
                    () -> BlockEntityType.Builder.of(
                                    GarbageCollectorEntity::new, ModBlocks.GARBAGE_COLLECTOR.get())
                            .build(null));
}
