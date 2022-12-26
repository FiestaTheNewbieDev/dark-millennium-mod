package fr.fiesta.dmm.world.entity.projectile;

import fr.fiesta.dmm.sounds.ModSounds;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class BoltEntity extends CustomProjectileEntity {
    private static final float SPEED = 3.5f;

    public BoltEntity(EntityType<? extends CustomProjectileEntity> type, Level level) {
        super(type, level);
    }

    public BoltEntity(LivingEntity shooter, float attackDamage, Level level) {
        super(ModEntityTypes.BOLT.get(), shooter, attackDamage, SPEED, level);
    }

    @Override
    public void onProjectileTick() {
        this.level.addParticle(ParticleTypes.SMOKE, this.getX() - this.getDeltaMovement().x, this.getY() - this.getDeltaMovement().y, this.getZ() - this.getDeltaMovement().z, 0, 0, 0);
    }

    @Override
    public void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        this.level.playSound(null, this.blockPosition(), ModSounds.BOLT_IMPACT.get(), SoundSource.PLAYERS, 1f, 1f);
    }

    @Override
    public boolean canBreakGlass() {
        return true;
    }
}
