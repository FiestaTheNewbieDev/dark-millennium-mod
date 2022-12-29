package fr.fiesta.dmm.network.packet;

import fr.fiesta.dmm.client.ModKeyBinds;
import fr.fiesta.dmm.world.item.gun.GunItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.function.Supplier;

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
            }
        });
        return true;
    }
}
