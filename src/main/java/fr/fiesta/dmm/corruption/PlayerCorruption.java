package fr.fiesta.dmm.corruption;

import net.minecraft.nbt.CompoundTag;

public class PlayerCorruption {
    private int corruption;
    private static final int MIN_CORRUPTION = -1000;
    private static final int NEUTRAL_CORRUPTION = 0;
    private static final int MAX_CORRUPTION = 1000;

    public PlayerCorruption() {
        this.corruption = NEUTRAL_CORRUPTION;
    }

    public int getCorruption() {
        return this.corruption;
    }

    public void addCorruption(int n) {
        this.corruption = Math.min(this.corruption + n, MAX_CORRUPTION);
    }

    public void subCorruption(int n) {
        this.corruption = Math.max(this.corruption - n, MIN_CORRUPTION);
    }

    public void copyFrom(PlayerCorruption source) {
        this.corruption = source.corruption;
    }

    public void saveNBTData(CompoundTag tag) {
        tag.putInt("corruption", this.corruption);
    }

    public void loadNBTData(CompoundTag tag) {
        this.corruption = tag.getInt("corruption");
    }
}
