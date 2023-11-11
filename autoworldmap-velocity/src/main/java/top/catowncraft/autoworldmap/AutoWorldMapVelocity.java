package top.catowncraft.autoworldmap;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;
import lombok.Getter;
import top.catowncraft.autoworldmap.common.listener.downstream.ClientboundSetDefaultSpawnPositionListener;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;
import top.catowncraft.autoworldmap.velocity.command.AutoWorldMapCommandVelocity;
import top.catowncraft.autoworldmap.velocity.handler.VoxelMapHandler;
import top.catowncraft.autoworldmap.velocity.handler.XaeroMapHandler;

import java.util.logging.Logger;

@Plugin(id = "auto-world-map",
        name = "AutoWorldMap",
        version = "@PLUGIN_VERSION@",
        url = "https://github.com/The-Cat-Town-Craft/Auto-World-Map",
        description = "Automatic map switching for VoxelMap, XaeroMap when switching servers.",
        authors = {"The-Cat-Town-Craft", "Hendrix-Shen"},
        dependencies = @Dependency(id = "protocolize")
)
@Singleton
public final class AutoWorldMapVelocity {
    @Getter
    private static ProxyServer server;

    @Inject
    public AutoWorldMapVelocity(ProxyServer proxyServer, Logger logger) {
        AutoWorldMapVelocity.server = proxyServer;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        Protocolize.protocolRegistration().registerPacket(ClientboundSetDefaultSpawnPositionPacket.MAPPINGS, Protocol.PLAY, PacketDirection.CLIENTBOUND, ClientboundSetDefaultSpawnPositionPacket.class);
        Protocolize.listenerProvider().registerListener(new ClientboundSetDefaultSpawnPositionListener());
        VoxelMapHandler.init();
        AutoWorldMapVelocity.getServer().getEventManager().register(this, new VoxelMapHandler());
        ClientboundSetDefaultSpawnPositionListener.register(new XaeroMapHandler());
        AutoWorldMapVelocity.getServer().getCommandManager().register("{plugin_id}", new AutoWorldMapCommandVelocity());
    }
}
