package fr.fiesta.dmm.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CorruptionC2SPacket {
    public CorruptionC2SPacket() {

    }

    public CorruptionC2SPacket(FriendlyByteBuf buffer) {

    }

    public void toBytes(FriendlyByteBuf buffer) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            /// Here we are on the server
        });
        return true;
    }
}
