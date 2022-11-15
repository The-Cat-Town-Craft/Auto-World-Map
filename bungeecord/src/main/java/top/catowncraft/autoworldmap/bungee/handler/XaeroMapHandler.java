package top.catowncraft.autoworldmap.bungee.handler;

import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.api.config.ServerInfo;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.event.IClientboundSetDefaultSpawnPositionEvent;
import top.catowncraft.autoworldmap.common.helper.PacketCreator;
import top.catowncraft.autoworldmap.common.packet.ClientboundCustomPayloadPacket;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

public class XaeroMapHandler implements IClientboundSetDefaultSpawnPositionEvent {
    @Override
    public void onEvent(PacketReceiveEvent<ClientboundSetDefaultSpawnPositionPacket> packetReceiveEvent) {
        ByteBuf buf = PacketCreator.xaeroMap(((ServerInfo) packetReceiveEvent.server()).getName());
        packetReceiveEvent.player().sendPacket(new ClientboundCustomPayloadPacket(SharedConstant.XAERO_MINI_MAP_CHANNEL, buf));
        packetReceiveEvent.player().sendPacket(new ClientboundCustomPayloadPacket(SharedConstant.XAERO_WORLD_MAP_CHANNEL, buf));
        buf.release();
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
