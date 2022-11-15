package top.catowncraft.autoworldmap.bungee.handler;

import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.config.ServerInfo;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.event.IClientboundSetDefaultSpawnPositionEvent;
import top.catowncraft.autoworldmap.common.packet.ClientboundCustomPayloadPacket;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

public class XaeroMapHandler implements IClientboundSetDefaultSpawnPositionEvent {
    @Override
    public void onEvent(PacketReceiveEvent<ClientboundSetDefaultSpawnPositionPacket> packetReceiveEvent) {
        byte[] serverName = ((ServerInfo) packetReceiveEvent.server()).getName().getBytes(StandardCharsets.UTF_8);
        CRC32 crc32 = new CRC32();
        crc32.update(serverName, 0, serverName.length);
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(0);
        buf.writeInt((int) crc32.getValue());
        packetReceiveEvent.player().sendPacket(new ClientboundCustomPayloadPacket(SharedConstant.XAERO_MINI_MAP_CHANNEL, buf));
        packetReceiveEvent.player().sendPacket(new ClientboundCustomPayloadPacket(SharedConstant.XAERO_WORLD_MAP_CHANNEL, buf));
        buf.release();
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
