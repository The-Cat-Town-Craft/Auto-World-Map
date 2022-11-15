package top.catowncraft.autoworldmap.common.listener.downstream;

import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;
import top.catowncraft.autoworldmap.common.event.IClientboundSetDefaultSpawnPositionEvent;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

import java.util.ArrayList;
import java.util.List;

public class ClientboundSetDefaultSpawnPositionListener extends AbstractPacketListener<ClientboundSetDefaultSpawnPositionPacket> {
    public ClientboundSetDefaultSpawnPositionListener() {
        super(ClientboundSetDefaultSpawnPositionPacket.class, Direction.DOWNSTREAM, 0);
    }

    private static final List<IClientboundSetDefaultSpawnPositionEvent> handlers = new ArrayList<>();

    public static void register(IClientboundSetDefaultSpawnPositionEvent handler) {
        if (handlers.contains(handler)) {
            throw new RuntimeException(String.format("Event already registered: %s", handler));
        }
        handlers.add(handler);
    }

    @Override
    public void packetReceive(PacketReceiveEvent<ClientboundSetDefaultSpawnPositionPacket> packetReceiveEvent) {
        for (IClientboundSetDefaultSpawnPositionEvent handler : ClientboundSetDefaultSpawnPositionListener.handlers) {
            handler.onEvent(packetReceiveEvent);
            if (handler.shouldCancel()) {
                packetReceiveEvent.cancelled(true);
                break;
            }
        }
    }

    @Override
    public void packetSend(PacketSendEvent<ClientboundSetDefaultSpawnPositionPacket> packetSendEvent) {
    }
}
