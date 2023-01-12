package fr.fiesta.dmm.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.world.item.gun.GunItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.IIngameOverlay;

public class GunHudOverlay {
    private final static int BAR_WIDTH = 100;
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DMM.MOD_ID, "textures/gui/weapons.png");

    public static IIngameOverlay HUD_GUN = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, 0);

        Player player = Minecraft.getInstance().player;

        if (player.getMainHandItem().getItem() instanceof GunItem gun) {
            ItemStack stack = player.getMainHandItem();
            int gap = BAR_WIDTH / gun.magSize;

            RenderSystem.setShaderTexture(0, TEXTURE_LOCATION);
            GuiComponent.blit(poseStack, (int)(width * 0.75), height - 12, 0, 0, BAR_WIDTH, 5, 256, 256);
            for (int i = 0; i < gun.magSize; i = i + 1) {
                if (GunItem.getAmmoCount(stack) == gun.magSize) {
                    GuiComponent.blit(poseStack, (int)(width * 0.75), height - 12, 0, 5, BAR_WIDTH, 10, 256, 256);
                } else if (GunItem.getAmmoCount(stack) >= i) {
                    GuiComponent.blit(poseStack, (int)(width * 0.75), height - 12, 0, 5, i * gap, 10, 256, 256);
                }
            }
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, GunItem.getAmmoCount(stack) +"/" + gun.magSize, (int)(width * 0.75), height - 21, 16777215);

        }

        if (player.getOffhandItem().getItem() instanceof GunItem gun) {
            ItemStack stack = player.getOffhandItem();
            int gap = BAR_WIDTH / gun.magSize;

            RenderSystem.setShaderTexture(0, TEXTURE_LOCATION);
            GuiComponent.blit(poseStack, (int)(width * 0.25) - BAR_WIDTH, height - 12, 0, 0, BAR_WIDTH, 5, 256, 256);
            for (int i = 0; i < gun.magSize; i = i + 1) {
                if (GunItem.getAmmoCount(stack) == gun.magSize) {
                    GuiComponent.blit(poseStack, (int)(width * 0.25) - BAR_WIDTH, height - 12, 0, 5, BAR_WIDTH, 10, 256, 256);
                } else if (GunItem.getAmmoCount(stack) >= i) {
                    GuiComponent.blit(poseStack, (int)(width * 0.25) - BAR_WIDTH, height - 12, 0, 5, i * gap, 10, 256, 256);
                }
            }
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, GunItem.getAmmoCount(stack) +"/" + gun.magSize, (int)(width * 0.25) - BAR_WIDTH, height - 21, 16777215);

        }
    });
}
