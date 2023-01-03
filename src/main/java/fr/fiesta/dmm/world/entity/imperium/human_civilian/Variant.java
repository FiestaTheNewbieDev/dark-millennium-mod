package fr.fiesta.dmm.world.entity.imperium.human_civilian;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author FiestaTheNewbieDev
 */
public enum Variant {
    HUMAN_CIVILIAN_0(0),
    HUMAN_CIVILIAN_1(1),
    HUMAN_CIVILIAN_2(2),
    HUMAN_CIVILIAN_3(3),
    HUMAN_CIVILIAN_4(4),
    HUMAN_CIVILIAN_5(5),
    HUMAN_CIVILIAN_6(6),
    HUMAN_CIVILIAN_7(7),
    HUMAN_CIVILIAN_8(8);

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
