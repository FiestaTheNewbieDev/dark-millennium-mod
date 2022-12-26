package fr.fiesta.dmm.client.model.entity;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.world.entity.chaos.CultistEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class CultistModel extends HumanoidModel<CultistEntity> {
    public static final String BODY = "body";
    public static final ModelLayerLocation CULTIST_LAYER = new ModelLayerLocation(new ResourceLocation(DMM.MOD_ID, "cultist"), BODY);

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = createMesh(CubeDeformation.NONE, 0);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public CultistModel(ModelPart part) {
        super(part);
    }
}
