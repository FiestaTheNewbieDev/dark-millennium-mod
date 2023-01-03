package fr.fiesta.dmm.event;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.corruption.PlayerCorruption;
import fr.fiesta.dmm.corruption.PlayerCorruptionProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author FiestaTheNewbieDev
 */
@Mod.EventBusSubscriber(modid = DMM.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerCorruptionProvider.PLAYER_CORRUPTION).isPresent()) {
                event.addCapability(new ResourceLocation(DMM.MOD_ID, "properties"), new PlayerCorruptionProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerCorruptionProvider.PLAYER_CORRUPTION).ifPresent(oldStore -> {
                event.getPlayer().getCapability(PlayerCorruptionProvider.PLAYER_CORRUPTION).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                    newStore.addCorruption(10);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerCorruption.class);
    }
}
