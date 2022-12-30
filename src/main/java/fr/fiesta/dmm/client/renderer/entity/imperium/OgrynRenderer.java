package fr.fiesta.dmm.client.renderer.entity.imperium;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.fiesta.dmm.world.entity.imperium.OgrynEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.nullness.qual.NonNull;

public class OgrynRenderer extends HumanoidMobRenderer<OgrynEntity, PlayerModel<OgrynEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft:textures/entity/steve.png");

    public OgrynRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 1);
    }

    @Override
    public void render(OgrynEntity entity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource bufferSource, int p_115460_) {
        poseStack.scale(1.25F, 1.25F, 1.25F);
        super.render(entity, p_115456_, p_115457_, poseStack, bufferSource, p_115460_);
    }

    @NonNull
    @Override
    public ResourceLocation getTextureLocation(OgrynEntity entity) {
        return TEXTURE;
    }
}
