package fr.fiesta.dmm.world.entity.projectile;

import fr.fiesta.dmm.utils.ModDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.Collection;
import java.util.UUID;

/**
 * @author FiestaTheNewbieDev
 */
public abstract class AbstractProjectileEntity extends Projectile {
    private UUID shooterId;
    private LivingEntity shooter;
    private float baseDamage;
    private float speed;

    public AbstractProjectileEntity(EntityType<? extends  Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public AbstractProjectileEntity(EntityType<? extends Projectile> entityType, LivingEntity shooter, float attackDamage, float speed, Level level) {
        this(entityType, level);
        this.shooterId = shooter.getUUID();
        this.shooter = shooter;
        this.baseDamage = attackDamage;
        this.speed = speed;

        Vec3 direction = this.getDirection(shooter);
        this.setDeltaMovement(direction.x * speed, direction.y * speed, direction.z * speed);
        this.updateHeading();

        double posX = shooter.xOld + (shooter.getX() - shooter.xOld) / 2.0;
        double posY = shooter.yOld + (shooter.getY() - shooter.yOld) / 2.0 + (shooter.getEyeHeight() * 0.8);
        double posZ = shooter.zOld + (shooter.getZ() - shooter.zOld) / 2.0;
        this.setPos(posX, posY, posZ);
    }

    @Override
    public void tick() {
        Entity entity = this.getOwner();
        if (this.level.isClientSide || (entity == null || !entity.isRemoved()) && this.level.hasChunkAt(this.blockPosition())) {
            super.tick();
            if (this.touchingUnloadedChunk()) {
                this.destroy();
            }
            HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
            }
        }
        this.updateHeading();
        this.onProjectileTick();

        double nextPosX = this.getX() + this.getDeltaMovement().x();
        double nextPosY = this.getY() + this.getDeltaMovement().y();
        double nextPosZ = this.getZ() + this.getDeltaMovement().z();
        this.setPos(nextPosX, nextPosY, nextPosZ);
    }

    public void onProjectileTick() {
    }

    @Override
    public void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity target = hitResult.getEntity();
        if (!(target instanceof AbstractProjectileEntity)) {
            if(!this.level.isClientSide) {
                if (this.shooter instanceof Player) ((LivingEntity)target).setLastHurtByPlayer((Player)this.shooter);
                ((LivingEntity)target).setLastHurtByMob(this.shooter);
                if (target instanceof Creeper) {
                    explodeCreeper((Creeper)target);
                } else {
                    target.hurt(ModDamageSource.GUN, this.baseDamage);
                }
            }
        }
        this.destroy();
    }

    @Override
    public void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        BlockPos pos = hitResult.getBlockPos();
        if(!this.level.isClientSide) {
            if (this.canBreakGlass()) {
                if (this.level.getBlockState(pos).getMaterial() == Material.GLASS) {
                    this.level.destroyBlock(pos, false);
                } else {
                    this.destroy();
                }
            }
            else {
                this.destroy();
            }
        }
    }

    private Vec3 getDirection(LivingEntity shooter) {
        return this.getVectorFromRotation(shooter.getXRot(), shooter.getYHeadRot());
    }

    private void updateHeading() {
        double horizontalDistance = this.getDeltaMovement().horizontalDistance();
        this.setYRot((float) (Mth.atan2(this.getDeltaMovement().x(), this.getDeltaMovement().z()) * (180D / Math.PI)));
        this.setXRot((float) (Mth.atan2(this.getDeltaMovement().y(), horizontalDistance) * (180D / Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    private Vec3 getVectorFromRotation(float pitch, float yaw) {
        float f = Mth.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = Mth.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = -Mth.cos(-pitch * 0.017453292F);
        float f3 = Mth.sin(-pitch * 0.017453292F);
        return new Vec3( (f1 * f2), f3, (f * f2));
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }

    /**
     * If true, the projectile will be able to break glass blocks, else it will not
     * Need to be overridden to change the return statement
     * @return
     */
    public boolean canBreakGlass() {
        return false;
    }

    /**
     * If true, the projectile will explode explosive entities like creeper on impact, else it will not
     * @return
     */
    public boolean canExplodeExplosiveEntities() {
        return true;
    }

    /**
     * Sub-method to discard entity
     * Allows to add effects at the destruction of the entity when overridden
     */
    public void destroy() {
        this.discard();
    }

    /**
     * Simulates a creeper explosion
     * @param creeper
     */
    private static void explodeCreeper(Creeper creeper) {
        int explosionRadius = 3;
        if (!creeper.level.isClientSide) {
            Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(creeper.level, creeper) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            float f = creeper.isPowered() ? 2.0F : 1.0F;
            creeper.level.explode(creeper, creeper.getX(), creeper.getY(), creeper.getZ(), (float)explosionRadius * f, explosion$blockinteraction);
            creeper.discard();
            spawnLingeringCloud(creeper);
        }
    }

    private static void spawnLingeringCloud(Creeper creeper) {
        Collection<MobEffectInstance> collection = creeper.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(creeper.level, creeper.getX(), creeper.getY(), creeper.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
            for(MobEffectInstance mobeffectinstance : collection) {
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            }
            creeper.level.addFreshEntity(areaeffectcloud);
        }
    }
}
