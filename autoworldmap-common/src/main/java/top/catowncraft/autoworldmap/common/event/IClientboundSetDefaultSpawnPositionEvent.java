package top.catowncraft.autoworldmap.common.event;

import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

public interface IClientboundSetDefaultSpawnPositionEvent {
    void onEvent(PacketReceiveEvent<ClientboundSetDefaultSpawnPositionPacket> packetReceiveEvent);

    boolean shouldCancel();
}
