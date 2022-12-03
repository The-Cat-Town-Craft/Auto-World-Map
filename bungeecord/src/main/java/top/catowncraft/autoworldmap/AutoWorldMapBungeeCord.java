package top.catowncraft.autoworldmap;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import top.catowncraft.autoworldmap.bungee.command.AutoWorldMapCommandBungee;
import top.catowncraft.autoworldmap.bungee.handler.VoxelMapHandler;
import top.catowncraft.autoworldmap.bungee.handler.XaeroMapHandler;
import top.catowncraft.autoworldmap.common.listener.downstream.ClientboundSetDefaultSpawnPositionListener;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

public final class AutoWorldMapBungeeCord extends Plugin {
    @Getter
    private static ProxyServer server;

    @Override
    public void onEnable() {
        AutoWorldMapBungeeCord.server = this.getProxy();
        Protocolize.protocolRegistration().registerPacket(ClientboundSetDefaultSpawnPositionPacket.MAPPINGS, Protocol.PLAY, PacketDirection.CLIENTBOUND, ClientboundSetDefaultSpawnPositionPacket.class);
        Protocolize.listenerProvider().registerListener(new ClientboundSetDefaultSpawnPositionListener());
        this.getProxy().getPluginManager().registerListener(this, new VoxelMapHandler());
        ClientboundSetDefaultSpawnPositionListener.register(new XaeroMapHandler());
        this.getProxy().getPluginManager().registerCommand(this, new AutoWorldMapCommandBungee());
    }

    @Override
    public void onDisable() {
    }
}
