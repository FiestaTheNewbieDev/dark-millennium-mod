package fr.fiesta.dmm.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.client.model.entity.ImperialGuardModel;
import fr.fiesta.dmm.world.entity.chaos.CultistEntity;
import fr.fiesta.dmm.world.entity.imperium.ImperialGuardEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.nullness.qual.NonNull;


public class ImperialGuardRenderer extends HumanoidMobRenderer<ImperialGuardEntity, ImperialGuardModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DMM.MOD_ID, "textures/entity/imperial_guard.png");

    public ImperialGuardRenderer(EntityRendererProvider.Context context) {
        super(context, new ImperialGuardModel(context.bakeLayer(ImperialGuardModel.IMPERIAL_GUARD_LAYER)), 1);
    }

    @Override
    public void render(ImperialGuardEntity entity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource bufferSource, int p_115460_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
        super.render(entity, p_115456_, p_115457_, poseStack, bufferSource, p_115460_);
    }

    @NonNull
    @Override
    public ResourceLocation getTextureLocation(ImperialGuardEntity entity) {
        return TEXTURE;
    }
}
