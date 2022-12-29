package fr.fiesta.dmm.client.renderer;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
    public static void init() {
        ItemProperties.register(ModItems.POWER_SWORD.get(), new ResourceLocation(DMM.MOD_ID, "enable"), (stack, level, player, id) -> {
            if (stack.hasTag()) {
                if (stack.getTag().getBoolean("enable")) {
                    return 0;
                }
            }
            return -1;
        });
        ItemProperties.register(ModItems.LASGUN.get(), new ResourceLocation(DMM.MOD_ID, "has_mag"), (stack, level, player, id) -> {
            if (stack.hasTag()) {
                if (!stack.getTag().getBoolean("hasMag")) {
                    return 0;
                }
            }
            return -1;
        });
        ItemProperties.register(ModItems.LASPISTOL.get(), new ResourceLocation(DMM.MOD_ID, "has_mag"), (stack, level, player, id) -> {
            if (stack.hasTag()) {
                if (!stack.getTag().getBoolean("hasMag")) {
                    return 0;
                }
            }
            return -1;
        });
        ItemProperties.register(ModItems.BOLT_PISTOL.get(), new ResourceLocation(DMM.MOD_ID, "has_mag"), (stack, level, player, id) -> {
            if (stack.hasTag()) {
                if (!stack.getTag().getBoolean("hasMag")) {
                    return 0;
                }
            }
            return -1;
        });
    }
}
