package fr.fiesta.dmm.utils;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.world.block.ModBlocks;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/**
 * @author FiestaTheNewbieDev
 */
public class ModCreativeModeTabs {

    public static final CreativeModeTab DMM_ITEMS = new CreativeModeTab(DMM.MOD_ID + ".items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BOLT_PISTOL.get());
        }
    };

    public static final CreativeModeTab DMM_BLOCKS = new CreativeModeTab(DMM.MOD_ID + ".blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.EMPEROR_IDOL.get());
        }
    };

    public static final CreativeModeTab DMM_ENTITIES = new CreativeModeTab(DMM.MOD_ID + ".entities") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CULTIST_SPAWN_EGG.get());
        }
    };
}
