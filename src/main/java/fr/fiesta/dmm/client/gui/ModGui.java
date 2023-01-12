package fr.fiesta.dmm.client.gui;

import net.minecraftforge.client.gui.OverlayRegistry;

public class ModGui {
    public static void init() {
        OverlayRegistry.registerOverlayAbove(null, "gun_hud", GunHudOverlay.HUD_GUN);
    }
}
