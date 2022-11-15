package top.catowncraft.autoworldmap.common.listener.upstream;

import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;
import top.catowncraft.autoworldmap.common.event.IClientboundCustomPayloadEvent;
import top.catowncraft.autoworldmap.common.packet.ClientboundCustomPayloadPacket;

import java.util.ArrayList;
import java.util.List;

public class ClientboundCustomPayloadListener extends AbstractPacketListener<ClientboundCustomPayloadPacket> {
    public ClientboundCustomPayloadListener() {
        super(ClientboundCustomPayloadPacket.class, Direction.UPSTREAM, 0);
    }

    private static final List<IClientboundCustomPayloadEvent> handlers = new ArrayList<>();

    public static void register(IClientboundCustomPayloadEvent handler) {
        if (handlers.contains(handler)) {
            throw new RuntimeException(String.format("Event already registered: %s", handler));
        }
        handlers.add(handler);
    }

    @Override
    public void packetReceive(PacketReceiveEvent<ClientboundCustomPayloadPacket> packetReceiveEvent) {
        for (IClientboundCustomPayloadEvent handler : ClientboundCustomPayloadListener.handlers) {
            handler.onEvent(packetReceiveEvent);
            if (handler.shouldCancel()) {
                packetReceiveEvent.cancelled(true);
                break;
            }
        }
    }

    @Override
    public void packetSend(PacketSendEvent<ClientboundCustomPayloadPacket> packetSendEvent) {
    }
}
