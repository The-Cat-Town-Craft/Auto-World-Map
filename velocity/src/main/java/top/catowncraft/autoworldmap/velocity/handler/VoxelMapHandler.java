package top.catowncraft.autoworldmap.velocity.handler;

import com.google.inject.Singleton;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import top.catowncraft.autoworldmap.AutoWorldMapVelocity;
import top.catowncraft.autoworldmap.common.SharedConstant;

import java.nio.charset.StandardCharsets;

@Singleton
public class VoxelMapHandler {
    private static final MinecraftChannelIdentifier VOXEL_CHANNEL = MinecraftChannelIdentifier.create(SharedConstant.VOXEL_MAP_NAMESPACE, SharedConstant.VOXEL_MAP_NAME);

    public static void init() {
        AutoWorldMapVelocity.getServer().getChannelRegistrar().register(VoxelMapHandler.VOXEL_CHANNEL);
    }

    @Subscribe
    public void onPluginMessageEvent(PluginMessageEvent pluginMessageEvent) {
        if (pluginMessageEvent.getSource() instanceof Player && pluginMessageEvent.getIdentifier().equals(VOXEL_CHANNEL)) {
            Player player = (Player) pluginMessageEvent.getSource();
            player.getCurrentServer().ifPresent(
                    serverConnection -> {
                        byte[] serverName = serverConnection.getServerInfo().getName().getBytes(StandardCharsets.UTF_8);
                        ByteBuf buf = Unpooled.buffer();
                        buf.writeByte(0);
                        buf.writeByte(serverName.length);
                        buf.writeBytes(serverName);
                        byte[] bytes = buf.array();
                        buf.release();
                        player.sendPluginMessage(VoxelMapHandler.VOXEL_CHANNEL, bytes);
                    });
            pluginMessageEvent.setResult(PluginMessageEvent.ForwardResult.handled());
        }
    }
}
