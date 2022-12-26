package fr.fiesta.dmm.world.item;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.utils.ModCreativeModeTabs;
import fr.fiesta.dmm.world.item.gun.BoltPistol;
import fr.fiesta.dmm.world.item.gun.Lasgun;
import fr.fiesta.dmm.world.item.gun.Laspistol;
import fr.fiesta.dmm.world.item.power_weapon.PowerWeapon;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
}
