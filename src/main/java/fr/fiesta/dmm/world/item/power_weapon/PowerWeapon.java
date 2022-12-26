package fr.fiesta.dmm.world.item.power_weapon;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import fr.fiesta.dmm.sounds.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PowerWeapon extends Item {
    private final float attackDamage;
    private final int capacity;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public PowerWeapon(int attackDamage, float attackSpeed, int capacity, Properties properties) {
        super(properties.stacksTo(1));
        this.attackDamage = attackDamage;
        this.capacity = capacity;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (stack.hasTag()) {
            int energy = stack.getTag().getInt("energy");
            tooltip.add(new TranslatableComponent("tooltip.dmm.power_sword.energy", energy, this.capacity));
        }
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip." + this.getRegistryName() + ".desc"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.dmm.press_shift"));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity player, int p_41407_, boolean p_41408_) {
        if (!stack.hasTag()) {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("enable", false);
            tag.putInt("energy", this.capacity);
            stack.setTag(tag);
        } else {
            if (stack.getTag().getBoolean("enable")) {
                if (stack.getTag().getInt("energy") <= 0) {
                    stack.getTag().putBoolean("enable", false);
                } else {
                    if(!((Player)player).isCreative()) {
                        stack.getTag().putInt("energy", stack.getTag().getInt("energy") - 1);
                    }
                }
            } else {
                if (stack.getTag().getInt("energy") < this.capacity) {
                    stack.getTag().putInt("energy", stack.getTag().getInt("energy") + 1);
                }
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getTag().getBoolean("enable")) {
            target.hurt(DamageSource.GENERIC, this.attackDamage * 2);
            target.setLastHurtByPlayer((Player) attacker);
            attacker.level.playSound(null, attacker.blockPosition(), ModSounds.POWER_SWORD_IMPACT.get(), SoundSource.PLAYERS, 1f, 1f);
        }
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getTag().getInt("energy") > 0) {
            stack.getTag().putBoolean("enable", !stack.getTag().getBoolean("enable"));
        }

        return super.use(level, player, hand);
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity player) {
        player.getLevel().playSound(null, player.blockPosition(), ModSounds.SWING.get(), SoundSource.PLAYERS, 1f, 1f);
        return super.onEntitySwing(stack, player);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }
}
