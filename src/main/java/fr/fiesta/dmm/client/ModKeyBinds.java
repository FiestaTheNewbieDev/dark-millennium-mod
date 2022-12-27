package fr.fiesta.dmm.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinds {
    public static final String KEY_CATEGORY_DMM = "key.category.dmm.dmm";
    public static final KeyMapping RELOAD = new KeyMapping("key.dmm.reload", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_DMM);

    public static void init() {
        ClientRegistry.registerKeyBinding(RELOAD);
    }
}
