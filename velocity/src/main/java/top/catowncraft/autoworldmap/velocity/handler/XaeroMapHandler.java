package top.catowncraft.autoworldmap.velocity.handler;

import com.google.inject.Singleton;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.ServerInfo;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import top.catowncraft.autoworldmap.AutoWorldMapVelocity;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.event.IClientboundSetDefaultSpawnPositionEvent;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.zip.CRC32;

@Singleton
public class XaeroMapHandler implements IClientboundSetDefaultSpawnPositionEvent {
    private static final MinecraftChannelIdentifier XAERO_MINI_MAP_CHANNEL = MinecraftChannelIdentifier.create(SharedConstant.XAERO_MINI_MAP_NAMESPACE, SharedConstant.XAERO_MINI_MAP_NAME);
    private static final MinecraftChannelIdentifier XAERO_WORLD_MAP_CHANNEL = MinecraftChannelIdentifier.create(SharedConstant.XAERO_WORLD_MAP_NAMESPACE, SharedConstant.XAERO_WORLD_MAP_NAME);

    @Override
    public void onEvent(PacketReceiveEvent<ClientboundSetDefaultSpawnPositionPacket> packetReceiveEvent) {
        byte[] serverName = ((ServerInfo) packetReceiveEvent.server()).getName().getBytes(StandardCharsets.UTF_8);
        CRC32 crc32 = new CRC32();
        crc32.update(serverName, 0, serverName.length);
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(0);
        buf.writeInt((int) crc32.getValue());
        byte[] bytes = buf.array();
        buf.release();
        Optional<Player> player = AutoWorldMapVelocity.getServer().getPlayer(packetReceiveEvent.player().uniqueId());
        player.ifPresent(p -> {
            p.sendPluginMessage(XaeroMapHandler.XAERO_MINI_MAP_CHANNEL, bytes);
            p.sendPluginMessage(XaeroMapHandler.XAERO_WORLD_MAP_CHANNEL, bytes);
        });
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
