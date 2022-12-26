package fr.fiesta.dmm;

import fr.fiesta.dmm.client.ModKeyBinds;
import fr.fiesta.dmm.client.renderer.entity.*;
import fr.fiesta.dmm.network.ModPackets;
import fr.fiesta.dmm.sounds.ModSounds;
import fr.fiesta.dmm.world.block.ModBlocks;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.POWER_SWORD.get(), new ResourceLocation(MOD_ID, "enable"), (stack, level, player, id) -> {
                if (stack.hasTag()) {
                    if (stack.getTag().getBoolean("enable")) {
                        return 0;
                    }
                }
                return -1;
            });
            ItemProperties.register(ModItems.LASGUN.get(), new ResourceLocation(MOD_ID, "has_mag"), (stack, level, player, id) -> {
                if (stack.hasTag()) {
                    if (!stack.getTag().getBoolean("hasMag")) {
                        return 0;
                    }
                }
                return -1;
            });
            ItemProperties.register(ModItems.LASPISTOL.get(), new ResourceLocation(MOD_ID, "has_mag"), (stack, level, player, id) -> {
                if (stack.hasTag()) {
                    if (!stack.getTag().getBoolean("hasMag")) {
                        return 0;
                    }
                }
                return -1;
            });
            ItemProperties.register(ModItems.BOLT_PISTOL.get(), new ResourceLocation(MOD_ID, "has_mag"), (stack, level, player, id) -> {
                if (stack.hasTag()) {
                    if (!stack.getTag().getBoolean("hasMag")) {
                        return 0;
                    }
                }
                return -1;
            });
        });
        ModKeyBinds.init();
        ModEntityRenderers.init();
    }
}