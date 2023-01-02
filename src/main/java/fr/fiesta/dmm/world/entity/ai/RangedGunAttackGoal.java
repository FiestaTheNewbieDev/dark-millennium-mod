package fr.fiesta.dmm.world.entity.ai;

import fr.fiesta.dmm.world.item.gun.GunItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.RangedAttackMob;

import java.util.EnumSet;

public class RangedGunAttackGoal<T extends Mob & RangedGunAttackMob> extends Goal {
    private final T mob;
    private final double speedModifier;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    private int cooldownTimer;
    private int fireCooldown;

    public <M extends Animal & RangedAttackMob> RangedGunAttackGoal(M mob, double speedModifier, float attackRadiusSqr){
        this((T) mob, speedModifier, attackRadiusSqr);
    }

    public RangedGunAttackGoal(T mob, double speedModifier, float attackRadiusSqr) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.attackRadiusSqr = attackRadiusSqr * attackRadiusSqr;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setFireCooldown(int fireCooldown) {
        this.cooldownTimer = 0;
        switch (this.mob.getLevel().getDifficulty()) {
            case HARD:
                this.fireCooldown = fireCooldown;
                break;
            case NORMAL:
                this.fireCooldown = fireCooldown * 3;
                break;
            case EASY:
                this.fireCooldown = fireCooldown * 6;
                break;
            case PEACEFUL:
                this.fireCooldown = Integer.MAX_VALUE;
                break;
        }
    }

    @Override
    public boolean canUse() {
        return this.mob.getTarget() == null ? false : this.isHoldingGun();
    }

    protected boolean isHoldingGun() {
        return this.mob.isHolding(itemStack -> itemStack.getItem() instanceof GunItem);
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone()) && this.isHoldingGun();
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.mob.stopUsingItem();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            double d0 = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight(target);
            boolean flag1 = this.seeTime > 0;
            if (flag != flag1) {
                this.seeTime = 0;
            }

            if (flag) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                this.mob.getNavigation().stop();
                ++this.strafingTime;
            } else {
                this.mob.getNavigation().moveTo(target, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
                    this.strafingBackwards = true;
                }

                this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.mob.lookAt(target, 30.0F, 30.0F);
            } else {
                this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }

            if (this.cooldownTimer <= 0) {
                this.mob.fire();
                this.cooldownTimer = fireCooldown;
            } else {
                this.cooldownTimer = this.cooldownTimer - 1;
            }
        }
    }
}
