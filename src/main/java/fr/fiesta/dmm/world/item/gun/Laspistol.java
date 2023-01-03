package fr.fiesta.dmm.world.item.gun;

import fr.fiesta.dmm.sounds.ModSounds;
import fr.fiesta.dmm.world.entity.projectile.LaserBeamEntity;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @author FiestaTheNewbieDev
 */
public class Laspistol extends GunItem {
    public Laspistol(float attackDamage, int reloadTime, int fireCooldown, Properties properties) {
        super(attackDamage, ModItems.LASPISTOL_POWER_PACK.get(), reloadTime, fireCooldown, properties);
    }

    @Override
    public void fire(Level level, LivingEntity shooter, ItemStack stack) {
        LaserBeamEntity laserBeam = new LaserBeamEntity(shooter, this.attackDamage, level);
        if(!level.isClientSide) level.addFreshEntity(laserBeam);
        level.playSound(null, shooter.blockPosition(), ModSounds.LASPISTOL_FIRE.get(), SoundSource.PLAYERS, 1f, 1f);
        super.fire(level, shooter, stack);
    }
}
