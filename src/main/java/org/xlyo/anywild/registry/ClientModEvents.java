package org.xlyo.anywild.registry;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.xlyo.anywild.Anywild;
import org.xlyo.anywild.client.renderer.ExplosiveArrowRenderer;

@Mod.EventBusSubscriber(modid = Anywild.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    // 在客户端设置事件中注册渲染器
    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntityTypes.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
    }
}
