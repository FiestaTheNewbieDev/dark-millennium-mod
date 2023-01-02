package fr.fiesta.dmm.client.renderer.entity.imperium.ogryn;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.client.model.entity.imperium.OgrynModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OgrynRenderer<T extends Mob, M extends OgrynModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DMM.MOD_ID, "textures/entity/imperium/ogryn/ogryn_0.png");

    public OgrynRenderer(EntityRendererProvider.Context context) {
        super(context,(M)new OgrynModel<T>(context.bakeLayer(OgrynModel.LAYER)), 1);
        this.addLayer(new OgrynItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }
}
