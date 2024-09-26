package org.xlyo.anywild.client.renderer;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.xlyo.anywild.Anywild;
import org.xlyo.anywild.entity.projectile.ExplosiveArrowEntity;

@OnlyIn(Dist.CLIENT)
public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrowEntity> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    Anywild.MODID,
                    "textures/entity/projectiles/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ExplosiveArrowEntity entity) {
        return TEXTURE;
    }
}
