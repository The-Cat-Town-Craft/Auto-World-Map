package top.catowncraft.autoworldmap.velocity.handler;

import com.google.inject.Singleton;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.ServerInfo;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import top.catowncraft.autoworldmap.AutoWorldMapVelocity;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.event.IClientboundSetDefaultSpawnPositionEvent;
import top.catowncraft.autoworldmap.common.helper.PacketCreator;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

import java.util.Optional;

@Singleton
public class XaeroMapHandler implements IClientboundSetDefaultSpawnPositionEvent {
    private static final MinecraftChannelIdentifier XAERO_MINI_MAP_CHANNEL = MinecraftChannelIdentifier.create(SharedConstant.XAERO_MINI_MAP_NAMESPACE, SharedConstant.XAERO_MINI_MAP_NAME);
    private static final MinecraftChannelIdentifier XAERO_WORLD_MAP_CHANNEL = MinecraftChannelIdentifier.create(SharedConstant.XAERO_WORLD_MAP_NAMESPACE, SharedConstant.XAERO_WORLD_MAP_NAME);

    @Override
    public void onEvent(PacketReceiveEvent<ClientboundSetDefaultSpawnPositionPacket> packetReceiveEvent) {
        if (SharedConstant.getConfig().isXaeroMapEnable()) {
            Optional<Player> player = AutoWorldMapVelocity.getServer().getPlayer(packetReceiveEvent.player().uniqueId());
            player.ifPresent(p -> {
                byte[] bytes = PacketCreator.xaeroMap(((ServerInfo) packetReceiveEvent.server()).getName());
                p.sendPluginMessage(XaeroMapHandler.XAERO_MINI_MAP_CHANNEL, bytes);
                p.sendPluginMessage(XaeroMapHandler.XAERO_WORLD_MAP_CHANNEL, bytes);
            });
        }
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
