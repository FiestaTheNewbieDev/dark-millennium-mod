package fr.fiesta.dmm.world.item;

import fr.fiesta.dmm.data.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Auspex extends Item {
    private final int MAX_REACH = 100;
    private final int detectionReach;
    private final int cooldown;
    private List<Entity> list;

    public Auspex(int reach, int cooldown, Item.Properties properties) {
        super(properties.stacksTo(1));
        this.detectionReach = reach;
        this.cooldown = cooldown;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip." + this.getRegistryName() + ".desc"));
            tooltip.add(new TranslatableComponent("tooltip.dmm.auspex.reach", this.detectionReach));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.dmm.press_shift"));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        this.detection(level, player);
        this.printList(level, player);
        player.getCooldowns().addCooldown(this, this.cooldown);
        return super.use(level, player, hand);
    }

    private void printList(Level level, Player player) {
        if (this.list.size() == 0) {
            if (level.isClientSide)
                player.sendMessage(new TranslatableComponent("tooltip.dmm.auspex.no_detection"), player.getUUID());
        } else {
            for (int i = 0; i < this.list.size(); i = i + 1) {
                int distance = (int) Math.sqrt(Math.pow((player.getX() - this.list.get(i).getX()), 2) + Math.pow((player.getY() - this.list.get(i).getY()), 2) + Math.pow((player.getZ() - this.list.get(i).getZ()), 2));
                if (level.isClientSide)
                    player.sendMessage(new TranslatableComponent("tooltip.dmm.auspex.detection", distance), player.getUUID());
            }
        }
    }

    private void detection(Level level, Player player) {
        AABB detectionZone = new AABB(new Vec3(player.getX() - (this.detectionReach), player.getY() - (this.detectionReach), player.getZ() - (this.detectionReach)), new Vec3(player.getX() + (this.detectionReach), player.getY() + (this.detectionReach), player.getZ() + (this.detectionReach)));
        this.list = level.getEntities(player, detectionZone);
        if (this.list.size() > 0) {
            for (int i = 0; i < this.list.size(); i = i + 1) {
                int distance = (int) Math.sqrt(Math.pow((player.getX() - this.list.get(i).getX()), 2) + Math.pow((player.getY() - this.list.get(i).getY()), 2) + Math.pow((player.getZ() - this.list.get(i).getZ()), 2));
                boolean tag = Registry.ENTITY_TYPE.getHolderOrThrow(Registry.ENTITY_TYPE.getResourceKey(this.list.get(i).getType()).get()).is(ModTags.EntityTypes.NO_ENERGY);
                if (distance > this.detectionReach || tag) {
                    this.list.remove(i);
                }
            }
        }
    }
}
