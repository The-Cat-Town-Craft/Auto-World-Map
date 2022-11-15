package top.catowncraft.autoworldmap.bungee.handler;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.helper.PacketCreator;

public class VoxelMapHandler implements Listener {
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (event.getSender() instanceof ProxiedPlayer && event.getTag().equals(SharedConstant.VOXEL_MAP_CHANNEL)) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            player.sendData(SharedConstant.VOXEL_MAP_CHANNEL,
                    PacketCreator.voxelMap(player.getServer().getInfo().getName()));
        }
    }
}
