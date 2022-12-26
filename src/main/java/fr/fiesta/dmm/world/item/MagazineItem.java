package fr.fiesta.dmm.world.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author FiestaTheNewbieDev
 */
public class MagazineItem extends Item {
    public final int capacity;

    public MagazineItem(int capacity, Properties properties) {
        super(properties.stacksTo(1));
        this.capacity = capacity;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (stack.hasTag()) {
            int ammo = stack.getTag().getInt("ammoCount");
            tooltip.add(new TranslatableComponent("tooltip.dmm.weapons.ammo", ammo, this.capacity));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        CompoundTag tag = new CompoundTag();
        if (!stack.hasTag()) {
            tag.putInt("ammoCount", capacity);
            stack.setTag(tag);
        } else {
            if (stack.getTag().getInt("ammoCount") < 0) stack.getTag().putInt("ammoCount", 0);
            else if (stack.getTag().getInt("ammoCount") > capacity) stack.getTag().putInt("ammoCount", capacity);
        }
    }
}
