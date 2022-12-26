package fr.fiesta.dmm.world.item.gun;

import fr.fiesta.dmm.sounds.ModSounds;
import fr.fiesta.dmm.world.item.MagazineItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author FiestaTheNewbieDev
 */
public class GunItem extends Item {
    public final float attackDamage;
    public final int magSize;
    public final MagazineItem magazine;
    public final int reloadTime;
    public final int fireCooldown;
    public final int fireRate;

    public GunItem(float attackDamage, MagazineItem magazine, int reloadTime, int fireCooldown, Properties properties) {
        super(properties.stacksTo(1));
        this.attackDamage = attackDamage;
        this.magSize = magazine.capacity;
        this.magazine = magazine;
        this.reloadTime = reloadTime;
        this.fireCooldown = fireCooldown;
        this.fireRate = (int) (60 / (0.05 * fireCooldown));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (stack.hasTag()) {
            int ammo = stack.getTag().getInt("ammoCount");
            tooltip.add(new TranslatableComponent("tooltip.dmm.weapons.ammo", ammo, this.magSize));
        }
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip." + this.getRegistryName() + ".desc"));
            tooltip.add(new TranslatableComponent("tooltip.dmm.weapons.damage", this.attackDamage));
            tooltip.add(new TranslatableComponent("tooltip.dmm.weapons.fire_rate", this.fireRate));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.dmm.press_shift"));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        CompoundTag tag = new CompoundTag();
        if (!stack.hasTag()) {
            tag.putInt("ammoCount", 0);
            tag.putBoolean("hasMag", false);
            stack.setTag(tag);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (canFire(stack)) {
            this.fire(level, player, stack);
        } else {
            if (player.isCreative()) this.reload(stack, player);
            else {
                if (level.isClientSide) player.sendMessage(new TranslatableComponent("tooltip.dmm.weapons.no_ammo"), player.getUUID());
                player.getLevel().playSound(null, player.blockPosition(), ModSounds.LOCK.get(), SoundSource.PLAYERS, 1f, 1f);
                this.reload(stack, player);
            }
        }
        return super.use(level, player, hand);
    }

    /**
     * Check if the gun can fire
     * @param stack
     * @return
     */
    private static boolean canFire(ItemStack stack) {
        return stack.getTag().getInt("ammoCount") > 0;
    }

    public void fire(Level level, Player player, ItemStack stack) {
        stack.getTag().putInt("ammoCount", stack.getTag().getInt("ammoCount") - 1);
        player.getCooldowns().addCooldown(this, this.fireCooldown);
    }

    /**
     * Gun ammunition reloading procedure
     * @param stack
     * @param player
     */
    public void reload(ItemStack stack, Player player) {
        if (player.isCreative()) {
            player.getCooldowns().addCooldown(this, this.reloadTime);
            stack.getTag().putInt("ammoCount", this.magSize);
            stack.getTag().putBoolean("hasMag", true);
            player.getLevel().playSound(null, player.blockPosition(), ModSounds.RELOAD.get(), SoundSource.PLAYERS, 1f, 1f);
        } else {
            if (!player.getInventory().isEmpty() && stack.getTag().getBoolean("hasMag")) {
                ItemStack oldMag = new ItemStack(magazine.asItem());
                CompoundTag tag = new CompoundTag();
                tag.putInt("ammoCount", stack.getTag().getInt("ammoCount"));
                oldMag.setTag(tag);
                player.getInventory().add(oldMag);
                stack.getTag().putInt("ammoCount", 0);
                stack.getTag().putBoolean("hasMag", false);
            } else if (!stack.getTag().getBoolean("hasMag") && containsMag(player.getInventory(), magazine.getDefaultInstance())) {
                player.getCooldowns().addCooldown(this, this.reloadTime);
                ItemStack newMag = player.getInventory().getItem(findSlotWithMag(player.getInventory(), magazine.getDefaultInstance()));
                stack.getTag().putInt("ammoCount", newMag.getTag().getInt("ammoCount"));
                player.getInventory().removeItem(newMag);
                stack.getTag().putBoolean("hasMag", true);
                player.getLevel().playSound(null, player.blockPosition(), ModSounds.RELOAD.get(), SoundSource.PLAYERS, 1f, 1f);
            }
        }
    }

    /**
     * Check if inventory contains a magazine with at least one ammo
     * @param inventory
     * @param stack
     * @return
     */
    public static boolean containsMag(Inventory inventory, ItemStack stack) {
        for (int i = 0; i < inventory.items.size(); ++i) {
            if (!inventory.items.isEmpty() && ItemStack.isSame(stack, inventory.items.get(i)) && inventory.items.get(i).getTag().getInt("ammoCount") > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the inventory slot index of a magazine with at least one ammo
     * @param inventory
     * @param stack
     * @return
     */
    private static int findSlotWithMag(Inventory inventory, ItemStack stack) {
        for (int i = 0; i < inventory.items.size(); ++i) {
            if (!inventory.items.isEmpty() && ItemStack.isSame(stack, inventory.items.get(i)) && inventory.items.get(i).getTag().getInt("ammoCount") > 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, Level level, BlockPos pos, Player  player) {
        return false;
    }
}
