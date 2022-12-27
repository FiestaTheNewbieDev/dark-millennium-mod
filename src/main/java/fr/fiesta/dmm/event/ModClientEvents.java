package fr.fiesta.dmm.event;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.client.ModKeyBinds;
import fr.fiesta.dmm.client.model.entity.BoltModel;
import fr.fiesta.dmm.client.model.entity.CultistModel;
import fr.fiesta.dmm.client.model.entity.ImperialGuardModel;
import fr.fiesta.dmm.client.model.entity.LaserBeamModel;
import fr.fiesta.dmm.network.ModPackets;
import fr.fiesta.dmm.network.packet.ReloadC2SPacket;
import fr.fiesta.dmm.world.entity.chaos.CultistEntity;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
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
            event.put(ModEntityTypes.IMPERIAL_GUARD.get(), CultistEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(BoltModel.BOLT_LAYER, BoltModel::createLayer);
            event.registerLayerDefinition(LaserBeamModel.LASER_BEAM_LAYER, LaserBeamModel::createLayer);
            event.registerLayerDefinition(CultistModel.CULTIST_LAYER, CultistModel::createBodyLayer);
            event.registerLayerDefinition(ImperialGuardModel.IMPERIAL_GUARD_LAYER, ImperialGuardModel::createBodyLayer);
        }
    }
}
