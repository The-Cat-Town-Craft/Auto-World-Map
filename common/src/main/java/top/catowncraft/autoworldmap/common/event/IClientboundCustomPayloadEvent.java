package top.catowncraft.autoworldmap.common.event;

import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import top.catowncraft.autoworldmap.common.packet.ClientboundCustomPayloadPacket;

public interface IClientboundCustomPayloadEvent {
    void onEvent(PacketReceiveEvent<ClientboundCustomPayloadPacket> packetReceiveEvent);

    boolean shouldCancel();
}
