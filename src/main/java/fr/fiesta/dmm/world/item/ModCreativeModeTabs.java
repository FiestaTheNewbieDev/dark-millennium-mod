package fr.fiesta.dmm.world.item;

import fr.fiesta.dmm.world.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab DMM_ITEMS = new CreativeModeTab("dmm_items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BOLT_PISTOL.get());
        }
    };

    public static final CreativeModeTab DMM_BLOCKS = new CreativeModeTab("dmm_blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.EMPEROR_IDOL.get());
        }
    };

}
