package top.catowncraft.autoworldmap.velocity.handler;

import com.google.inject.Singleton;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.autoworldmap.AutoWorldMapVelocity;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.helper.PacketCreator;

import java.util.concurrent.atomic.AtomicBoolean;

@Singleton
public class VoxelMapHandler {
    private static final MinecraftChannelIdentifier VOXEL_CHANNEL = MinecraftChannelIdentifier.create(SharedConstant.VOXEL_MAP_NAMESPACE, SharedConstant.VOXEL_MAP_NAME);

    public static void init() {
        AutoWorldMapVelocity.getServer().getChannelRegistrar().register(VoxelMapHandler.VOXEL_CHANNEL);
    }

    @Subscribe
    public void onPluginMessageEvent(@NotNull PluginMessageEvent pluginMessageEvent) {
        if (pluginMessageEvent.getSource() instanceof Player && pluginMessageEvent.getIdentifier().equals(VOXEL_CHANNEL)) {
            Player player = (Player) pluginMessageEvent.getSource();

            AtomicBoolean legacy = new AtomicBoolean(false);
            AtomicBoolean modern = new AtomicBoolean(false);

            if (SharedConstant.getConfig().isVoxelMapLegacyEnable()) {
                player.getCurrentServer().ifPresent(serverConnection -> {
                    player.sendPluginMessage(VoxelMapHandler.VOXEL_CHANNEL,
                            PacketCreator.voxelMap(serverConnection.getServerInfo().getName()));
                    legacy.set(true);
                });
            }

            if (SharedConstant.getConfig().isVoxelMapModernEnable()) {
                player.getCurrentServer().ifPresent(serverConnection -> {
                    player.sendPluginMessage(VoxelMapHandler.VOXEL_CHANNEL,
                            PacketCreator.voxelMap(serverConnection.getServerInfo().getName()));
                    modern.set(true);
                });
            }

            if (legacy.get() || modern.get()) {
                pluginMessageEvent.setResult(PluginMessageEvent.ForwardResult.handled());
            }
        }
    }
}
