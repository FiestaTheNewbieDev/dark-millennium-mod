package fr.fiesta.dmm.client.renderer.entity;

import fr.fiesta.dmm.client.renderer.entity.chaos.CultistRenderer;
import fr.fiesta.dmm.client.renderer.entity.imperium.HumanCivilianRenderer;
import fr.fiesta.dmm.client.renderer.entity.imperium.ImperialGuardRenderer;
import fr.fiesta.dmm.client.renderer.entity.imperium.ogryn.OgrynRenderer;
import fr.fiesta.dmm.client.renderer.entity.projectile.BoltRenderer;
import fr.fiesta.dmm.client.renderer.entity.projectile.LaserBeamRenderer;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;

/**
 * @author FiestaTheNewbieDev
 */
public class ModEntityRenderers {
    public static void init() {
        EntityRenderers.register(ModEntityTypes.LASER_BEAM.get(), LaserBeamRenderer::new);
        EntityRenderers.register(ModEntityTypes.BOLT.get(), BoltRenderer::new);
        EntityRenderers.register(ModEntityTypes.CULTIST.get(), CultistRenderer::new);
        EntityRenderers.register(ModEntityTypes.IMPERIAL_GUARD.get(), ImperialGuardRenderer::new);
        EntityRenderers.register(ModEntityTypes.OGRYN.get(), OgrynRenderer::new);
        EntityRenderers.register(ModEntityTypes.HUMAN_CIVILIAN.get(), HumanCivilianRenderer::new);
    }
}
