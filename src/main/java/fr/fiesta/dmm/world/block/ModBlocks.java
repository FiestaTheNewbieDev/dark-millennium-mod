package fr.fiesta.dmm.world.block;

import fr.fiesta.dmm.DMM;
import fr.fiesta.dmm.utils.ModCreativeModeTabs;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DMM.MOD_ID);

    public static final RegistryObject<Block> EMPEROR_IDOL = createBlock("emperor_idol", () -> new EmperorIdol(5, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), ModCreativeModeTabs.DMM_BLOCKS);

    private static <T extends Block> RegistryObject<T> createBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        createBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> createBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        createBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> createBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<Item> createBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
