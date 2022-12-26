package fr.fiesta.dmm.world.entity;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.world.entity.chaos.CultistEntity;
import fr.fiesta.dmm.world.entity.imperium.ImperialGuardEntity;
import fr.fiesta.dmm.world.entity.projectile.BoltEntity;
import fr.fiesta.dmm.world.entity.projectile.LaserBeamEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, DMM.MOD_ID);

    public static final RegistryObject<EntityType<LaserBeamEntity>> LASER_BEAM = ENTITY_TYPES.register("laser_beam", () -> EntityType.Builder.<LaserBeamEntity>of(LaserBeamEntity::new, MobCategory.MISC)
            .sized(0.15f, 0.15f)
            .build(new ResourceLocation(DMM.MOD_ID, "laser_beam").toString()));
    public static final RegistryObject<EntityType<BoltEntity>> BOLT = ENTITY_TYPES.register("bolt", () -> EntityType.Builder.<BoltEntity>of(BoltEntity::new, MobCategory.MISC)
            .sized(0.1f, 0.1f)
            .build(new ResourceLocation(DMM.MOD_ID, "bolt").toString()));

    public static final RegistryObject<EntityType<CultistEntity>> CULTIST = ENTITY_TYPES.register("cultist", () -> EntityType.Builder.of(CultistEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 1.8f)
            .build(new ResourceLocation(DMM.MOD_ID, "cultist").toString()));
    public static final RegistryObject<EntityType<ImperialGuardEntity>> IMPERIAL_GUARD = ENTITY_TYPES.register("imperial_guard", () -> EntityType.Builder.of(ImperialGuardEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 1.8f)
            .build(new ResourceLocation(DMM.MOD_ID, "imperial_guard").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
