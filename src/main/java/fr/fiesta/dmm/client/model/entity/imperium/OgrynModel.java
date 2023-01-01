package fr.fiesta.dmm.client.model.entity.imperium;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.fiesta.dmm.DMM;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class OgrynModel<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(DMM.MOD_ID, "ogryn"), "main");

    public final ModelPart head;
    public final ModelPart hat;
    public final ModelPart body;
    public final ModelPart jacket;
    public final ModelPart rightArm;
    public final ModelPart rightSleeve;
    public final ModelPart leftArm;
    public final ModelPart leftSleeve;
    public final ModelPart rightLeg;
    public final ModelPart rightPants;
    public final ModelPart leftLeg;
    public final ModelPart leftPants;
    public OgrynModel.ArmPose rightArmPose = OgrynModel.ArmPose.EMPTY;
    public OgrynModel.ArmPose leftArmPose = OgrynModel.ArmPose.EMPTY;
    public boolean crouching;
    public float swimAmount;

    public OgrynModel(ModelPart modelPart) {
        this(modelPart, RenderType::entityCutoutNoCull);
    }

    public OgrynModel(ModelPart modelPart, Function<ResourceLocation, RenderType> function) {
        super(function, true, 0, 0, 0, 0,0);
        this.head = modelPart.getChild("head");
        this.hat = modelPart.getChild("hat");
        this.body = modelPart.getChild("body");
        this.jacket = modelPart.getChild("jacket");
        this.rightArm = modelPart.getChild("rightArm");
        this.rightSleeve = modelPart.getChild("rightSleeve");
        this.leftArm = modelPart.getChild("leftArm");
        this.leftSleeve = modelPart.getChild("leftSleeve");
        this.rightLeg = modelPart.getChild("rightLeg");
        this.rightPants = modelPart.getChild("rightPants");
        this.leftLeg = modelPart.getChild("leftLeg");
        this.leftPants = modelPart.getChild("leftPants");
    }

    public static MeshDefinition createMesh(CubeDeformation cubeDeformation, float p_170827_) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partdefinition = meshDefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(42, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));
        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 26).addBox(-5.5F, -10.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(36, 40).addBox(-8.0F, -9.0F, -4.0F, 16.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(76, 30).addBox(-7.0F, 1.0F, -3.0F, 14.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
        PartDefinition jacket = partdefinition.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -9.5F, -4.5F, 17.0F, 17.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 74).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, -7.0F, 0.0F));
        PartDefinition rightSleeve = partdefinition.addOrReplaceChild("rightSleeve", CubeListBuilder.create().texOffs(28, 58).addBox(-3.5F, -0.5F, -3.5F, 7.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, -7.0F, 0.0F));
        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(78, 76).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, -7.0F, 0.0F));
        PartDefinition leftSleeve = partdefinition.addOrReplaceChild("leftSleeve", CubeListBuilder.create().texOffs(0, 48).addBox(-3.5F, -0.5F, -3.5F, 7.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, -7.0F, 0.0F));
        PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(50, 82).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.0F, 0.0F));
        PartDefinition rightPants = partdefinition.addOrReplaceChild("rightPants", CubeListBuilder.create().texOffs(56, 58).addBox(-3.5F, -0.5F, -3.5F, 7.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.0F, 0.0F));
        PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(24, 84).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 0.0F));
        PartDefinition leftPants = partdefinition.addOrReplaceChild("leftPants", CubeListBuilder.create().texOffs(72, 0).addBox(-3.5F, -0.5F, -3.5F, 7.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 0.0F));
        return meshDefinition;
    }

    public static LayerDefinition createLayer() {
        return LayerDefinition.create(createMesh(CubeDeformation.NONE, 1), 128, 128);
    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        jacket.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightSleeve.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftSleeve.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightPants.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftPants.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.jacket, this.rightArm, this.rightSleeve, this.leftArm, this.leftSleeve, this.rightLeg, this.rightPants, this.leftLeg, this.leftPants, this.hat);
    }

    public void setupAnim(T entity, float p_102867_, float p_102868_, float p_102869_, float p_102870_, float p_102871_) {
        boolean fallingFlag = entity.getFallFlyingTicks() > 4;
        boolean swimmingFlag = entity.isVisuallySwimming();
        this.head.yRot = p_102870_ * ((float)Math.PI / 180F);
        if (fallingFlag) {
            this.head.xRot = (-(float)Math.PI / 4F);
        } else if(this.swimAmount > 0) {
            if (swimmingFlag) {
                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-(float)Math.PI / 4F));
            } else {
                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, p_102871_ * ((float)Math.PI / 180F));
            }
        } else {
            this.head.xRot = p_102871_ * ((float)Math.PI / 180F);
        }

        this.body.yRot = 0F;
        this.rightArm.z = 0F;
        this.rightArm.x = -11F;
        this.leftArm.z = 0F;
        this.leftArm.x = 11F;
        float f = 1.0F;
        if (fallingFlag) {
            f = (float)entity.getDeltaMovement().lengthSqr();
            f /= 0.2F;
            f *= f * f;
        }
        if (f < 1.0F) {
            f = 1.0F;
        }

        this.rightArm.xRot = Mth.cos(p_102867_ * 0.6662F + (float)Math.PI) * 2.0F * p_102868_ * 0.5F / f;
        this.leftArm.xRot = Mth.cos(p_102867_ * 0.6662F) * 2.0F * p_102868_ * 0.5F / f;
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.rightLeg.xRot = Mth.cos(p_102867_ * 0.6662F) * 1.4F * p_102868_ / f;
        this.leftLeg.xRot = Mth.cos(p_102867_ * 0.6662F + (float)Math.PI) * 1.4F * p_102868_ / f;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
        this.rightLeg.zRot = 0.0F;
        this.leftLeg.zRot = 0.0F;
        if (this.riding) {
            this.rightArm.xRot += (-(float)Math.PI / 5F);
            this.leftArm.xRot += (-(float)Math.PI / 5F);
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = ((float)Math.PI / 10F);
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (-(float)Math.PI / 10F);
            this.leftLeg.zRot = -0.07853982F;
        }

        this.rightArm.yRot = 0.0F;
        this.leftArm.yRot = 0.0F;
        boolean flag2 = entity.getMainArm() == HumanoidArm.RIGHT;
        if (entity.isUsingItem()) {
            boolean flag3 = entity.getUsedItemHand() == InteractionHand.MAIN_HAND;
            if (flag3 == flag2) {
                this.poseRightArm(entity);
            } else {
                this.poseLeftArm(entity);
            }
        } else {
            boolean flag4 = flag2 ? this.leftArmPose.isTwoHanded() : this.rightArmPose.isTwoHanded();
            if (flag2 != flag4) {
                this.poseLeftArm(entity);
                this.poseRightArm(entity);
            } else {
                this.poseRightArm(entity);
                this.poseLeftArm(entity);
            }
        }

        this.setupAttackAnimation(entity, p_102869_);
        if (this.crouching) {
            this.body.xRot = 0.5F;
            this.rightArm.xRot += 0.4F;
            this.leftArm.xRot += 0.4F;
            this.rightLeg.z =2.1F;
            this.leftLeg.z = 2.1F;
            this.rightLeg.y = 8.2F;
            this.leftLeg.y = 8.2F;
            this.head.z = -3.5F;
            this.head.y = -2.5F;
            this.body.y = 4.2F;
            this.leftArm.y = -3.8F;
            this.leftArm.z = -3.5F;
            this.rightArm.y = -3.8F;
            this.rightArm.z = -3.5F;
        } else {
            this.body.xRot = 0.0F;
            this.rightLeg.z = 0.1F;
            this.leftLeg.z = 0.1F;
            this.rightLeg.y = 8.0F;
            this.leftLeg.y = 8.0F;
            this.head.z = 0.0F;
            this.head.y = -8.0F;
            this.body.y = 1.0F;
            this.leftArm.y = -8.0F;
            this.leftArm.z = 0.0F;
            this.rightArm.y = -8.0F;
            this.rightArm.z = 0.0F;
        }

        if (this.rightArmPose != ArmPose.SPYGLASS) {
            AnimationUtils.bobModelPart(this.rightArm, p_102869_, 1.0F);
        }

        if (this.leftArmPose != ArmPose.SPYGLASS) {
            AnimationUtils.bobModelPart(this.leftArm, p_102869_, -1.0F);
        }

        if (this.swimAmount > 0.0F) {
            float f5 = p_102867_ % 26.0F;
            HumanoidArm humanoidarm = this.getAttackArm(entity);
            float f1 = humanoidarm == HumanoidArm.RIGHT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
            float f2 = humanoidarm == HumanoidArm.LEFT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
            if (!entity.isUsingItem()) {
                this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, 0.0F);
                this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, 0.0F);
                this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, (float)Math.PI);
                this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, (float)Math.PI);
                this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, (float)Math.PI + 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
                this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, (float)Math.PI - 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
            } else if (f5 >= 14.0F && f5 < 22.0F) {
                float f6 = (f5 - 14.0F) / 8.0F;
                this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, ((float)Math.PI / 2F) * f6);
                this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, ((float)Math.PI / 2F) * f6);
                this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, (float)Math.PI);
                this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, (float)Math.PI);
                this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, 5.012389F - 1.8707964F * f6);
                this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, 1.2707963F + 1.8707964F * f6);
            } else if (f5 >= 22.0F && f5 < 26.0F) {
                float f3 = (f5 - 22.0F) / 4.0F;
                this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f3);
                this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f3);
                this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, (float)Math.PI);
                this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, (float)Math.PI);
                this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, (float)Math.PI);
                this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, (float)Math.PI);
            }


            float f7 = 0.3F;
            float f4 = 0.33333334F;
            this.leftLeg.xRot = Mth.lerp(this.swimAmount, this.leftLeg.xRot, 0.3F * Mth.cos(p_102867_ * 0.33333334F + (float)Math.PI));
            this.rightLeg.xRot = Mth.lerp(this.swimAmount, this.rightLeg.xRot, 0.3F * Mth.cos(p_102867_ * 0.33333334F));
        }

        this.hat.copyFrom(this.head);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.jacket.copyFrom(this.body);
    }

    private void poseRightArm(T entity) {
        switch(this.rightArmPose) {
            case EMPTY:
                this.rightArm.yRot = 0.0F;
                break;
            case BLOCK:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.9424779F;
                this.rightArm.yRot = (-(float)Math.PI / 6F);
                break;
            case ITEM:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - ((float)Math.PI / 10F);
                this.rightArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float)Math.PI;
                this.rightArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot;
                this.leftArm.yRot = 0.1F + this.head.yRot + 0.4F;
                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, entity, true);
                break;
            case CROSSBOW_HOLD:
                AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
                break;
            case SPYGLASS:
                this.rightArm.xRot = Mth.clamp(this.head.xRot - 1.9198622F - (entity.isCrouching() ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.rightArm.yRot = this.head.yRot - 0.2617994F;
        }
    }

    private void poseLeftArm(T entity) {
        switch(this.leftArmPose) {
            case EMPTY:
                this.leftArm.yRot = 0.0F;
                break;
            case BLOCK:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 0.9424779F;
                this.leftArm.yRot = ((float)Math.PI / 6F);
                break;
            case ITEM:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - ((float)Math.PI / 10F);
                this.leftArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float)Math.PI;
                this.leftArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot - 0.4F;
                this.leftArm.yRot = 0.1F + this.head.yRot;
                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, entity, false);
                break;
            case CROSSBOW_HOLD:
                AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, false);
                break;
            case SPYGLASS:
                this.leftArm.xRot = Mth.clamp(this.head.xRot - 1.9198622F - (entity.isCrouching() ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.leftArm.yRot = this.head.yRot + 0.2617994F;
        }
    }

    protected void setupAttackAnimation(T entity, float p_102859_) {
        if (!(this.attackTime <= 0.0F)) {
            HumanoidArm humanoidarm = this.getAttackArm(entity);
            ModelPart modelpart = this.getArm(humanoidarm);
            float f = this.attackTime;
            this.body.yRot = Mth.sin(Mth.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
            if (humanoidarm == HumanoidArm.LEFT) {
                this.body.yRot *= -1.0F;
            }

            this.rightArm.z = Mth.sin(this.body.yRot) * 5.0F;
            this.rightArm.x = -Mth.cos(this.body.yRot) * 5.0F;
            this.leftArm.z = -Mth.sin(this.body.yRot) * 5.0F;
            this.leftArm.x = Mth.cos(this.body.yRot) * 5.0F;
            this.rightArm.yRot += this.body.yRot;
            this.leftArm.yRot += this.body.yRot;
            this.leftArm.xRot += this.body.yRot;
            f = 1.0F - this.attackTime;
            f *= f;
            f *= f;
            f = 1.0F - f;
            float f1 = Mth.sin(f * (float)Math.PI);
            float f2 = Mth.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
            modelpart.xRot -= f1 * 1.2F + f2;
            modelpart.yRot += this.body.yRot * 2.0F;
            modelpart.zRot += Mth.sin(this.attackTime * (float)Math.PI) * -0.4F;
        }
    }

    protected float rotlerpRad(float p_102836_, float p_102837_, float p_102838_) {
        float f = (p_102838_ - p_102837_) % ((float)Math.PI * 2F);
        if (f < -(float)Math.PI) {
            f += ((float)Math.PI * 2F);
        }

        if (f >= (float)Math.PI) {
            f -= ((float)Math.PI * 2F);
        }

        return p_102837_ + p_102836_ * f;
    }

    private float quadraticArmUpdate(float p_102834_) {
        return -65.0F * p_102834_ + p_102834_ * p_102834_;
    }

    public void copyPropertiesTo(OgrynModel<T> model) {
        super.copyPropertiesTo(model);
        model.leftArmPose = this.leftArmPose;
        model.rightArmPose = this.rightArmPose;
        model.crouching = this.crouching;
        model.head.copyFrom(this.head);
        model.hat.copyFrom(this.hat);
        model.body.copyFrom(this.body);
        model.rightArm.copyFrom(this.rightArm);
        model.leftArm.copyFrom(this.leftArm);
        model.rightLeg.copyFrom(this.rightLeg);
        model.leftLeg.copyFrom(this.leftLeg);
    }

    public void setAllVisible(boolean isVisible) {
        this.head.visible = isVisible;
        this.hat.visible = isVisible;
        this.body.visible = isVisible;
        this.rightArm.visible = isVisible;
        this.leftArm.visible = isVisible;
        this.rightLeg.visible = isVisible;
        this.leftLeg.visible = isVisible;
    }

    public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
        this.getArm(arm).translateAndRotate(poseStack);
    }

    protected ModelPart getArm(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public ModelPart getHead() {
        return this.head;
    }


    private HumanoidArm getAttackArm(T entity) {
        HumanoidArm humanoidarm = entity.getMainArm();
        return entity.swingingArm == InteractionHand.MAIN_HAND ? humanoidarm : humanoidarm.getOpposite();
    }

    @OnlyIn(Dist.CLIENT)
    public static enum ArmPose {
        EMPTY(false),
        ITEM(false),
        BLOCK(false),
        BOW_AND_ARROW(true),
        THROW_SPEAR(false),
        CROSSBOW_CHARGE(true),
        CROSSBOW_HOLD(true),
        SPYGLASS(false);

        private final boolean twoHanded;

        private ArmPose(boolean p_102896_) {
            this.twoHanded = p_102896_;
        }

        public boolean isTwoHanded() {
            return this.twoHanded;
        }
    }
}
