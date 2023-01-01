package fr.fiesta.dmm.world.entity.imperium.imperial_guard;

import java.util.Arrays;
import java.util.Comparator;

public enum Variant {
    IMPERIAL_GUARD_0(0),
    IMPERIAL_GUARD_1(1),
    IMPERIAL_GUARD_2(2),
    IMPERIAL_GUARD_3(3),
    IMPERIAL_GUARD_4(4),
    IMPERIAL_GUARD_5(5),
    IMPERIAL_GUARD_6(6),
    IMPERIAL_GUARD_7(7),
    IMPERIAL_GUARD_8(8);

    private static final Variant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(Variant::getId)).toArray((p_30989_) -> {
        return new Variant[p_30989_];
    });

    private final int id;

    Variant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static Variant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
