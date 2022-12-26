package fr.fiesta.dmm.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.client.model.entity.CultistModel;
import fr.fiesta.dmm.world.entity.chaos.CultistEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.nullness.qual.NonNull;


public class CultistRenderer extends HumanoidMobRenderer<CultistEntity, CultistModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DMM.MOD_ID, "textures/entity/cultist.png");

    public CultistRenderer(EntityRendererProvider.Context context) {
        super(context, new CultistModel(context.bakeLayer(CultistModel.CULTIST_LAYER)), 1);
    }

    @Override
    public void render(CultistEntity entity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource bufferSource, int p_115460_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
        super.render(entity, p_115456_, p_115457_, poseStack, bufferSource, p_115460_);
    }

    @NonNull
    @Override
    public ResourceLocation getTextureLocation(CultistEntity entity) {
        return TEXTURE;
    }
}
