package top.catowncraft.autoworldmap;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;
import net.md_5.bungee.api.plugin.Plugin;
import top.catowncraft.autoworldmap.bungee.handler.VoxelMapHandler;
import top.catowncraft.autoworldmap.bungee.handler.XaeroMapHandler;
import top.catowncraft.autoworldmap.common.listener.upstream.ClientboundCustomPayloadListener;
import top.catowncraft.autoworldmap.common.listener.downstream.ClientboundSetDefaultSpawnPositionListener;
import top.catowncraft.autoworldmap.common.packet.ClientboundCustomPayloadPacket;
import top.catowncraft.autoworldmap.common.packet.ClientboundSetDefaultSpawnPositionPacket;

public final class AutoWorldMapBungeeCord extends Plugin {
    @Override
    public void onEnable() {
        Protocolize.protocolRegistration().registerPacket(ClientboundCustomPayloadPacket.MAPPINGS, Protocol.PLAY, PacketDirection.CLIENTBOUND, ClientboundCustomPayloadPacket.class);
        Protocolize.protocolRegistration().registerPacket(ClientboundSetDefaultSpawnPositionPacket.MAPPINGS, Protocol.PLAY, PacketDirection.CLIENTBOUND, ClientboundSetDefaultSpawnPositionPacket.class);
        Protocolize.listenerProvider().registerListener(new ClientboundCustomPayloadListener());
        Protocolize.listenerProvider().registerListener(new ClientboundSetDefaultSpawnPositionListener());
        this.getProxy().getPluginManager().registerListener(this, new VoxelMapHandler());
        ClientboundSetDefaultSpawnPositionListener.register(new XaeroMapHandler());
    }

    @Override
    public void onDisable() {
    }
}
