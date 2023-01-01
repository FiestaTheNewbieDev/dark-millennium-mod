package fr.fiesta.dmm.event;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.client.ModKeyBinds;
import fr.fiesta.dmm.client.model.entity.imperium.OgrynModel;
import fr.fiesta.dmm.client.model.entity.projectile.BoltModel;
import fr.fiesta.dmm.client.model.entity.projectile.LaserBeamModel;
import fr.fiesta.dmm.network.ModPackets;
import fr.fiesta.dmm.network.packet.ReloadC2SPacket;
import fr.fiesta.dmm.world.entity.chaos.CultistEntity;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
import fr.fiesta.dmm.world.entity.imperium.imperial_guard.ImperialGuardEntity;
import fr.fiesta.dmm.world.entity.imperium.human_civilian.HumanCivilianEntity;
import fr.fiesta.dmm.world.entity.imperium.OgrynEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModClientEvents {
    @Mod.EventBusSubscriber(modid = DMM.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (ModKeyBinds.RELOAD.consumeClick()) ModPackets.sendToServer(new ReloadC2SPacket());
        }
    }

    @Mod.EventBusSubscriber(modid = DMM.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onAttributeCreate(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.CULTIST.get(), CultistEntity.createAttributes().build());
            event.put(ModEntityTypes.IMPERIAL_GUARD.get(), ImperialGuardEntity.createAttributes().build());
            event.put(ModEntityTypes.OGRYN.get(), OgrynEntity.createAttributes().build());
            event.put(ModEntityTypes.HUMAN_CIVILIAN.get(), HumanCivilianEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(BoltModel.LAYER, BoltModel::createLayer);
            event.registerLayerDefinition(LaserBeamModel.LAYER, LaserBeamModel::createLayer);
            event.registerLayerDefinition(OgrynModel.LAYER, OgrynModel::createLayer);
        }
    }
}
