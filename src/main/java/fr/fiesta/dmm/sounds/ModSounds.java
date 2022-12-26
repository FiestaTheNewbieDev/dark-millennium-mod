package fr.fiesta.dmm.sounds;

import fr.fiesta.dmm.DMM;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DMM.MOD_ID);

    public static final RegistryObject<SoundEvent> LASER_BEAM_IMPACT = createSoundEvent("laser_beam_impact");
    public static final RegistryObject<SoundEvent> BOLT_IMPACT = createSoundEvent("bolt_impact");

    public static final RegistryObject<SoundEvent> LASPISTOL_FIRE = createSoundEvent("laspistol_fire");
    public static final RegistryObject<SoundEvent> LASGUN_FIRE = createSoundEvent("lasgun_fire");
    public static final RegistryObject<SoundEvent> BOLT_PISTOL_FIRE = createSoundEvent("bolt_pistol_fire");
    public static final RegistryObject<SoundEvent> RELOAD = createSoundEvent("reload");
    public static final RegistryObject<SoundEvent> LOCK = createSoundEvent("lock");

    public static final RegistryObject<SoundEvent> SWING = createSoundEvent("swing");
    public static final RegistryObject<SoundEvent> CHAINSWORD_SWING = createSoundEvent("chainsword_swing");
    public static final RegistryObject<SoundEvent> POWER_SWORD_IMPACT = createSoundEvent("power_sword_impact");

    private static RegistryObject<SoundEvent> createSoundEvent(String soundID) {
        return SOUND_EVENTS.register(soundID, () -> new SoundEvent(new ResourceLocation(DMM.MOD_ID, soundID)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
