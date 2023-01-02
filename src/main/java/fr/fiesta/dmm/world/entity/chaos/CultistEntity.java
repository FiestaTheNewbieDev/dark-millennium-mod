package fr.fiesta.dmm.world.entity.chaos;

import fr.fiesta.dmm.world.entity.ai.RangedGunAttackGoal;
import fr.fiesta.dmm.world.entity.ai.RangedGunAttackMob;
import fr.fiesta.dmm.world.entity.imperium.ImperiumEntity;
import fr.fiesta.dmm.world.entity.imperium.imperial_guard.ImperialGuardEntity;
import fr.fiesta.dmm.world.item.ModItems;
import fr.fiesta.dmm.world.item.gun.GunItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CultistEntity extends ChaosEntity implements RangedGunAttackMob {
    private final MeleeAttackGoal MELEE_ATTACK_GOAL = new MeleeAttackGoal(this,1, true) {
        public void stop() {
            super.stop();
            CultistEntity.this.setAggressive(false);
        }

        public void start() {
            super.start();
            CultistEntity.this.setAggressive(true);
        }
    };
    private final RangedGunAttackGoal<CultistEntity> RANGED_GUN_ATTACK_GOAL = new RangedGunAttackGoal<CultistEntity>(this, 1.0D, 15.0F);

    public CultistEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.randomWeapon();
        this.reassessWeaponGoal();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ImperiumEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Monster.class, true));
        super.registerGoals();
    }

    public void randomWeapon() {
        int max = 3;
        int random = (int)(Math.random() * (max) + 1);
        switch (random) {
            case 1:
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.CHAINSWORD.get()));
                break;
            case 2:
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.LASPISTOL.get()));
                break;
            case 3:
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.LASGUN.get()));
                break;
        }
    }

    public void reassessWeaponGoal() {
        this.goalSelector.removeGoal(RANGED_GUN_ATTACK_GOAL);
        this.goalSelector.removeGoal(MELEE_ATTACK_GOAL);
        if (this.level != null && !this.level.isClientSide) {
            ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof GunItem));
            if (itemStack.getItem() instanceof GunItem gun) {
                int i = 20;
                if (this.level.getDifficulty() != Difficulty.HARD) {
                    i = 40;
                }

                this.RANGED_GUN_ATTACK_GOAL.setFireCooldown(gun.fireCooldown);
                this.goalSelector.addGoal(0, RANGED_GUN_ATTACK_GOAL);
            } else {
                this.goalSelector.addGoal(0, MELEE_ATTACK_GOAL);
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0f)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.reassessWeaponGoal();
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        super.setItemSlot(slot, stack);
        if (!this.level.isClientSide) {
            this.reassessWeaponGoal();
        }
    }

    @Override
    public boolean canHoldItem(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canTakeItem(ItemStack stack) {
        return true;
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.PILLAGER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        super.getHurtSound(damageSource);
        return SoundEvents.PILLAGER_HURT;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    public void fire() {
        ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof GunItem));
        if (itemstack.getItem() instanceof GunItem gun) {
            gun.fire(this.getLevel(), this, null);
        }
    }
}
