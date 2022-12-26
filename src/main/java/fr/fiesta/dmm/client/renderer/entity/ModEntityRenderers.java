package fr.fiesta.dmm.client.renderer.entity;

import fr.fiesta.dmm.world.entity.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class ModEntityRenderers {
    public static void init() {
        EntityRenderers.register(ModEntityTypes.LASER_BEAM.get(), LaserBeamRenderer::new);
        EntityRenderers.register(ModEntityTypes.BOLT.get(), BoltRenderer::new);
        EntityRenderers.register(ModEntityTypes.CULTIST.get(), CultistRenderer::new);
        EntityRenderers.register(ModEntityTypes.IMPERIAL_GUARD.get(), ImperialGuardRenderer::new);
    }
}
