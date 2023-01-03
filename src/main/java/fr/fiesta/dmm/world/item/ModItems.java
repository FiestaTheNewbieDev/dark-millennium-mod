package fr.fiesta.dmm.world.item;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.utils.ModCreativeModeTabs;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
import fr.fiesta.dmm.world.item.gun.BoltPistol;
import fr.fiesta.dmm.world.item.gun.Lasgun;
import fr.fiesta.dmm.world.item.gun.Laspistol;
import fr.fiesta.dmm.world.item.power_weapon.PowerWeapon;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author FiestaTheNewbieDev
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DMM.MOD_ID);
    public static final RegistryObject<MagazineItem> LASPISTOL_POWER_PACK = ITEMS.register("laspistol_power_pack", () -> new MagazineItem(10, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS)));
    public static final RegistryObject<Item> LASPISTOL = ITEMS.register("laspistol", () -> new Laspistol(7, 50, 10, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS)));

    public static final RegistryObject<MagazineItem> LASGUN_POWER_PACK = ITEMS.register("lasgun_power_pack", () -> new MagazineItem(50, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> LASGUN = ITEMS.register("lasgun", () -> new Lasgun(10, 100, 5, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS)));

    public static final RegistryObject<MagazineItem> BOLT_PISTOL_MAGAZINE = ITEMS.register("bolt_pistol_magazine", () -> new MagazineItem(10, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> BOLT_PISTOL = ITEMS.register("bolt_pistol", () -> new BoltPistol(20, 50, 10, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS)));

    public static final RegistryObject<Item> CHAINSWORD = ITEMS.register("chainsword", () -> new Chainsword(9, -2.4f, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS)));
    public static final RegistryObject<Item> POWER_SWORD = ITEMS.register("power_sword", () -> new PowerWeapon(9, -2.4f, 1000, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS)));

    public static final RegistryObject<Item> AUSPEX = ITEMS.register("auspex", () -> new Auspex(50, 20, new Item.Properties().tab(ModCreativeModeTabs.DMM_ITEMS)));

    public static final RegistryObject<Item> CULTIST_SPAWN_EGG = ITEMS.register("cultist_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.CULTIST, 582900, 606060, new Item.Properties().tab(ModCreativeModeTabs.DMM_ENTITIES)));
    public static final RegistryObject<Item> IMPERIAL_GUARD_SPAWN_EGG = ITEMS.register("imperial_guard_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.IMPERIAL_GUARD, 606060, 568203, new Item.Properties().tab(ModCreativeModeTabs.DMM_ENTITIES)));
    public static final RegistryObject<Item> OGRYN_SPAWN_EGG = ITEMS.register("ogryn_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.OGRYN, 0xc8ad7f, 5009705, new Item.Properties().tab(ModCreativeModeTabs.DMM_ENTITIES)));
    public static final RegistryObject<Item> HUMAN_CIVILIAN_SPAWN_EGG = ITEMS.register("human_civilian_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.HUMAN_CIVILIAN, 0xa91101, 0x1e7fcb, new Item.Properties().tab(ModCreativeModeTabs.DMM_ENTITIES)));
}