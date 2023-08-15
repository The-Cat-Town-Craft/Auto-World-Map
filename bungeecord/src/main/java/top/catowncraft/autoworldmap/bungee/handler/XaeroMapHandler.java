package top.catowncraft.autoworldmap.bungee.handler;

import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.autoworldmap.AutoWorldMapBungeeCord;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.event.IClientboundSetDefaultSpawnPositionEvent;
import top.catowncraft.autoworldmap.common.helper.PacketCreator;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

public class XaeroMapHandler implements IClientboundSetDefaultSpawnPositionEvent {
    @Override
    public void onEvent(@NotNull PacketReceiveEvent<ClientboundSetDefaultSpawnPositionPacket> packetReceiveEvent) {
        ProxiedPlayer player = AutoWorldMapBungeeCord.getServer().getPlayer(packetReceiveEvent.player().uniqueId());

        if (player.isConnected()) {
            byte[] bytes = PacketCreator.xaeroMap(((ServerInfo) packetReceiveEvent.server()).getName());

            if (SharedConstant.getConfig().isXaeroMiniMapEnable()) {
                player.sendData(SharedConstant.XAERO_MINI_MAP_CHANNEL, bytes);
            }

            if (SharedConstant.getConfig().isXaeroWorldMapEnable()) {
                player.sendData(SharedConstant.XAERO_WORLD_MAP_CHANNEL, bytes);
            }
        }
    }

    @Override
    public boolean shouldCancel() {
        return false;
    }
}
