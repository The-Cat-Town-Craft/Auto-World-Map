package top.catowncraft.autoworldmap.velocity.handler;

import com.google.inject.Singleton;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import top.catowncraft.autoworldmap.AutoWorldMapVelocity;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.helper.PacketCreator;

@Singleton
public class VoxelMapHandler {
    private static final MinecraftChannelIdentifier VOXEL_CHANNEL = MinecraftChannelIdentifier.create(SharedConstant.VOXEL_MAP_NAMESPACE, SharedConstant.VOXEL_MAP_NAME);

    public static void init() {
        AutoWorldMapVelocity.getServer().getChannelRegistrar().register(VoxelMapHandler.VOXEL_CHANNEL);
    }

    @Subscribe
    public void onPluginMessageEvent(PluginMessageEvent pluginMessageEvent) {
        if (SharedConstant.getConfig().isVoxelMapEnable() &&
                pluginMessageEvent.getSource() instanceof Player && pluginMessageEvent.getIdentifier().equals(VOXEL_CHANNEL)) {
            Player player = (Player) pluginMessageEvent.getSource();
            player.getCurrentServer().ifPresent(
                    serverConnection -> player.sendPluginMessage(VoxelMapHandler.VOXEL_CHANNEL,
                            PacketCreator.voxelMap(serverConnection.getServerInfo().getName())));
            pluginMessageEvent.setResult(PluginMessageEvent.ForwardResult.handled());
        }
    }
}
