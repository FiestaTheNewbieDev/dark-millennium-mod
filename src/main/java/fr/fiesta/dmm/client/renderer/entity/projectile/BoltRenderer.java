package fr.fiesta.dmm.client.renderer.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.client.model.entity.projectile.BoltModel;
import fr.fiesta.dmm.world.entity.projectile.BoltEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoltRenderer extends EntityRenderer<BoltEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DMM.MOD_ID, "textures/entity/projectile/bolt.png");

    private final BoltModel model;

    public BoltRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BoltModel(context.bakeLayer(BoltModel.BOLT_LAYER));
    }

    @Override
    public void render(BoltEntity entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        poseStack.pushPose();
        poseStack.translate(0, -1.425f, 0);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entity.yRotO + 180));
        ///poseStack.mulPose(Vector3f.XP.rotationDegrees(entity.xRotO));
        VertexConsumer vertexconsumer = bufferSource.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
    }

    public ResourceLocation getTextureLocation(BoltEntity entity) {
        return TEXTURE_LOCATION;
    }
}