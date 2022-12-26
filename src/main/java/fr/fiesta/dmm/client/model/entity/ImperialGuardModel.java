package fr.fiesta.dmm.client.model.entity;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.world.entity.imperium.ImperialGuardEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class ImperialGuardModel extends HumanoidModel<ImperialGuardEntity> {
    public static final String BODY = "body";
    public static final ModelLayerLocation IMPERIAL_GUARD_LAYER = new ModelLayerLocation(new ResourceLocation(DMM.MOD_ID, "imperial_guard"), BODY);

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = createMesh(CubeDeformation.NONE, 0);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public ImperialGuardModel(ModelPart part) {
        super(part);
    }
}
