package fr.fiesta.dmm;

import fr.fiesta.dmm.client.ModKeyBinds;
import fr.fiesta.dmm.client.gui.ModGui;
import fr.fiesta.dmm.client.renderer.ModItemProperties;
import fr.fiesta.dmm.client.renderer.entity.*;
import fr.fiesta.dmm.network.ModPackets;
import fr.fiesta.dmm.sounds.ModSounds;
import fr.fiesta.dmm.world.block.ModBlocks;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author FiestaTheNewbieDev
 */
@Mod(DMM.MOD_ID)
public class DMM {
    public static final String MOD_ID = "dmm";

    public DMM() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModSounds.SOUND_EVENTS.register(bus);
        ModEntityTypes.ENTITY_TYPES.register(bus);
    }

    private void setup(FMLCommonSetupEvent event) {
        ModPackets.register();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ModKeyBinds.init();
        ModItemProperties.init();
        ModEntityRenderers.init();
        ModGui.init();
    }
}