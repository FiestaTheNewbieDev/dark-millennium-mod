package fr.fiesta.dmm.client.renderer.entity.imperium.ogryn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author FiestaTheNewbieDev
 */
@OnlyIn(Dist.CLIENT)
public class OgrynItemInHandLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
    public OgrynItemInHandLayer(RenderLayerParent<T, M> p_117183_) {
        super(p_117183_);
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int p_117206_, T p_117207_, float p_117208_, float p_117209_, float p_117210_, float p_117211_, float p_117212_, float p_117213_) {
        boolean flag = p_117207_.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? p_117207_.getOffhandItem() : p_117207_.getMainHandItem();
        ItemStack itemstack1 = flag ? p_117207_.getMainHandItem() : p_117207_.getOffhandItem();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            poseStack.pushPose();
            if (this.getParentModel().young) {
                float f = 0.5F;
                poseStack.translate(0.0D, 0.75D, 0.0D);
                poseStack.scale(0.5F, 0.5F, 0.5F);
            }

            this.renderArmWithItem(p_117207_, itemstack1, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, bufferSource, p_117206_);
            this.renderArmWithItem(p_117207_, itemstack, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, bufferSource, p_117206_);
            poseStack.popPose();
        }
    }

    protected void renderArmWithItem(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType p_117187_, HumanoidArm arm, PoseStack poseStack, MultiBufferSource bufferSource, int p_117191_) {
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            this.getParentModel().translateToHand(arm, poseStack);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            boolean flag = arm == HumanoidArm.LEFT;
            poseStack.translate(0, 0.2D, -1D);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(entity, stack, p_117187_, flag, poseStack, bufferSource, p_117191_);
            poseStack.popPose();
        }
    }
}
