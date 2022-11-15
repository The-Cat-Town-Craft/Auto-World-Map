package top.catowncraft.autoworldmap.bungee.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import top.catowncraft.autoworldmap.common.SharedConstant;

import java.nio.charset.StandardCharsets;

public class VoxelMapHandler implements Listener {
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            if (event.getTag().equals(SharedConstant.VOXEL_MAP_CHANNEL)) {
                ProxiedPlayer player = (ProxiedPlayer) event.getSender();
                byte[] serverName = player.getServer().getInfo().getName().getBytes(StandardCharsets.UTF_8);
                ByteBuf buf = Unpooled.buffer();
                buf.writeByte(0);
                buf.writeByte(serverName.length);
                buf.writeBytes(serverName);
                byte[] voxelArray = buf.array();
                buf.release();
                player.sendData(SharedConstant.VOXEL_MAP_CHANNEL, voxelArray);
            }
        }
    }
}
