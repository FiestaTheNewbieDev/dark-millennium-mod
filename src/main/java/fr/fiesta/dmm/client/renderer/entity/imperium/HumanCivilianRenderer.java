package fr.fiesta.dmm.client.renderer.entity.imperium;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.world.entity.imperium.human_civilian.HumanCivilianEntity;
import fr.fiesta.dmm.world.entity.imperium.human_civilian.Variant;
import net.minecraft.Util;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

/**
 * @author FiestaTheNewbieDev
 */
public class HumanCivilianRenderer extends HumanoidMobRenderer<HumanCivilianEntity, PlayerModel<HumanCivilianEntity>> {
    private static final Map<Variant, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(Variant.class), (p_114874_) -> {
        for (int i = 0; i <= 8; i = i + 1) {
            p_114874_.put(Variant.byId(i), new ResourceLocation(DMM.MOD_ID, "textures/entity/imperium/civilian/civilian_" + i + ".png"));
        }
    });

    public HumanCivilianRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), true), 1);
    }

    @Override
    public void render(HumanCivilianEntity entity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource bufferSource, int p_115460_) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
        super.render(entity, p_115456_, p_115457_, poseStack, bufferSource, p_115460_);
    }

    @NonNull
    @Override
    public ResourceLocation getTextureLocation(HumanCivilianEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}
