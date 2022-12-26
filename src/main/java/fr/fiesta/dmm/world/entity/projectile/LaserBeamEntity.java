package fr.fiesta.dmm.world.entity.projectile;

import fr.fiesta.dmm.sounds.ModSounds;
import fr.fiesta.dmm.world.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class LaserBeamEntity extends CustomProjectileEntity {
    private final static float SPEED = 3.5f;
    private BlockPos oldBlockPos;

    public LaserBeamEntity(EntityType<? extends LaserBeamEntity> type, Level level) {
        super(type, level);
    }

    public LaserBeamEntity(LivingEntity shooter, float attackDamage, Level level) {
        super(ModEntityTypes.LASER_BEAM.get(), shooter, attackDamage, SPEED, level);
        //this.oldBlockPos = new BlockPos(this.getOnPos().getX(), this.getOnPos().getY(), this.getOnPos().getZ());
    }

    /*
    @Override
    public void onProjectileTick() {
        if (this.level.getBlockState(this.oldBlockPos).getBlock() == Blocks.LIGHT) this.level.setBlock(this.oldBlockPos, Blocks.GLASS.defaultBlockState(), 1);
        if (this.level.getBlockState(this.getOnPos()).getMaterial() == Material.AIR) this.level.setBlock(this.getOnPos(), Blocks.LIGHT.defaultBlockState(), 1);
        this.oldBlockPos = new BlockPos(this.getOnPos().getX(), this.getOnPos().getY(), this.getOnPos().getZ());
        System.out.println(this.oldBlockPos);
    }
    */

    @Override
    public void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        this.level.playSound(null, this.blockPosition(), ModSounds.LASER_BEAM_IMPACT.get(), SoundSource.PLAYERS, 1f, 1f);
    }
}