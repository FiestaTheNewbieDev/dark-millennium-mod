package fr.fiesta.dmm.world.entity.imperium.imperial_guard;

import fr.fiesta.dmm.world.entity.chaos.ChaosEntity;
import fr.fiesta.dmm.world.entity.imperium.ImperiumEntity;
import fr.fiesta.dmm.world.entity.imperium.human_civilian.HumanCivilianEntity;
import fr.fiesta.dmm.world.entity.imperium.human_civilian.Variant;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ImperialGuardEntity extends ImperiumEntity {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(HumanCivilianEntity.class, EntityDataSerializers.INT);

    public ImperialGuardEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.CHAINSWORD.get()));
        Variant variant = Util.getRandom(Variant.values(), this.random);
        this.setVariant(variant);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ChaosEntity.class, true));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0f)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3F);
    }



    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setTypeVariant(tag.getInt("Variant"));
    }
    @Override
    public boolean canHoldItem(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canTakeItem(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob p_146744_) {
        return null;
    }

    private void setTypeVariant(int id) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, id);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(Variant variant) {
        this.setTypeVariant(variant.getId());
    }

    public Variant getVariant() {
        return Variant.byId(this.getTypeVariant());
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        super.getHurtSound(damageSource);
        return SoundEvents.PLAYER_HURT;
    }
}
