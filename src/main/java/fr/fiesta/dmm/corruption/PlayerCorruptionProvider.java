package fr.fiesta.dmm.corruption;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCorruptionProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerCorruption> PLAYER_CORRUPTION = CapabilityManager.get(new CapabilityToken<PlayerCorruption>() {
    });

    private PlayerCorruption corruption = null;
    private final LazyOptional<PlayerCorruption> optional = LazyOptional.of(this::createPlayerCorruption);

    private PlayerCorruption createPlayerCorruption() {
        if (this.corruption == null) {
            this.corruption = new PlayerCorruption();
        }
        return this.corruption;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_CORRUPTION) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerCorruption().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        createPlayerCorruption().loadNBTData(tag);
    }
}
