package fr.fiesta.dmm.world.item.gun;

import fr.fiesta.dmm.sounds.ModSounds;
import fr.fiesta.dmm.world.entity.projectile.BoltEntity;
import fr.fiesta.dmm.world.item.ModItems;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoltPistol extends GunItem {
    public BoltPistol(float attackDamage, int reloadTime, int fireCooldown, Properties properties) {
        super(attackDamage, ModItems.BOLT_PISTOL_MAGAZINE.get(), reloadTime, fireCooldown, properties);
    }

    @Override
    public void fire(Level level, LivingEntity shooter, ItemStack stack) {
        BoltEntity bolt = new BoltEntity(shooter, this.attackDamage, level);
        if(!level.isClientSide) level.addFreshEntity(bolt);
        level.playSound(null, shooter.blockPosition(), ModSounds.BOLT_PISTOL_FIRE.get(), SoundSource.PLAYERS, 1f, 1f);
        super.fire(level, shooter, stack);
    }
}
