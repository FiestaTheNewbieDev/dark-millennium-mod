package fr.fiesta.dmm.client.model.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.fiesta.dmm.DMM;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author FiestaTheNewbieDev
 */
@OnlyIn(Dist.CLIENT)
public class BoltModel extends Model {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(DMM.MOD_ID, "bolt"), "main");

	private final ModelPart main;

	public BoltModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.main = root.getChild("main");
	}

	public static MeshDefinition createMesh() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.5F, 0.0F));
		return meshdefinition;
	}

	public static LayerDefinition createLayer() {
		return LayerDefinition.create(createMesh(), 16, 16);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}