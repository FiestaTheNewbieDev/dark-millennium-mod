package fr.fiesta.dmm.network.packet;

import fr.fiesta.dmm.world.item.gun.GunItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author FiestaTheNewbieDev
 */
public class ReloadC2SPacket {
    public ReloadC2SPacket() {

    }

    public ReloadC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buffer) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            if (player.getMainHandItem().getItem() instanceof GunItem gun) {
                if (!player.getCooldowns().isOnCooldown(gun)) gun.reload(player.getMainHandItem(), player, false);
            } else if (player.getOffhandItem().getItem() instanceof GunItem gun) {
                if (!player.getCooldowns().isOnCooldown(gun)) gun.reload(player.getOffhandItem(), player, false);
            }
        });
        return true;
    }
}
