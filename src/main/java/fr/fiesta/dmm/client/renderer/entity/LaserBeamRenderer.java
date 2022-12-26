package fr.fiesta.dmm.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.client.model.entity.LaserBeamModel;
import fr.fiesta.dmm.world.entity.projectile.LaserBeamEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LaserBeamRenderer extends EntityRenderer<LaserBeamEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DMM.MOD_ID, "textures/entity/laser_beam.png");

    private final LaserBeamModel model;

    public LaserBeamRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new LaserBeamModel(context.bakeLayer(LaserBeamModel.LASER_BEAM_LAYER));
    }

    @Override
    public void render(LaserBeamEntity entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        poseStack.pushPose();
        poseStack.translate(0, -1.35f, 0);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entity.yRotO + 180));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(entity.xRotO));
        VertexConsumer vertexconsumer = bufferSource.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
    }

    public ResourceLocation getTextureLocation(LaserBeamEntity entity) {
        return TEXTURE_LOCATION;
    }
}