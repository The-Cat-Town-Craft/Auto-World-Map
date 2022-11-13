package top.catowncraft.autoworldmap.bungee;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.CRC32;

public class VoxelMapListener implements Listener {
    private static final String VOXEL_MAP_CHANNEL = "worldinfo:world_id";
    private static final String XAERO_WORLD_MAP_CHANNEL = "xaeroworldmap:main";
    private static final String XAERO_MINI_MAP_CHANNEL = "xaerominimap:main";
    private static final String MINECRAFT_REGISTER_CHANNEL = "minecraft:register";
    private static final byte[] MINECRAFT_WORLD_INFO_DATA = VOXEL_MAP_CHANNEL.getBytes();

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            // Voxel map handler
            if (event.getTag().equals(VoxelMapListener.VOXEL_MAP_CHANNEL)) {
                ProxiedPlayer player = (ProxiedPlayer) event.getSender();
                byte[] serverName = player.getServer().getInfo().getName().getBytes(StandardCharsets.UTF_8);
                ByteBuf buf = Unpooled.buffer();
                buf.writeByte(0);
                buf.writeByte(serverName.length);
                buf.writeBytes(serverName);
                byte[] voxelArray = buf.array();
                buf.release();
                player.sendData(VoxelMapListener.VOXEL_MAP_CHANNEL, voxelArray);
                AutoWorldMap.getPluginLogger().info("Handled voxelmap packet!");
                // Send xaero map data.
            } else if (event.getTag().equals(VoxelMapListener.MINECRAFT_REGISTER_CHANNEL) &&
                    Arrays.equals(event.getData(), VoxelMapListener.MINECRAFT_WORLD_INFO_DATA)) {
                ProxiedPlayer player = (ProxiedPlayer) event.getSender();
                byte[] serverName = player.getServer().getInfo().getName().getBytes(StandardCharsets.UTF_8);
                CRC32 crc32 = new CRC32();
                crc32.update(serverName, 0, serverName.length);
                ByteBuf buf = Unpooled.buffer();
                buf.writeByte(0);
                buf.writeInt((int) crc32.getValue());
                byte[] xaeroArray = buf.array();
                player.sendData(VoxelMapListener.XAERO_MINI_MAP_CHANNEL, xaeroArray);
                player.sendData(VoxelMapListener.XAERO_WORLD_MAP_CHANNEL, xaeroArray);
                AutoWorldMap.getPluginLogger().info("XaeroMap packet sent!");
            }
            // packet catcher
            // AutoWorldMap.getPluginLogger().warning("[+]============================================[+]");
            // AutoWorldMap.getPluginLogger().info(String.format("Event tag: %s", event.getTag()));
            // AutoWorldMap.getPluginLogger().info(String.format("Event data: %s", new String(event.getData())));
            // AutoWorldMap.getPluginLogger().info(String.format("Event data_bytes: %s", event.getData()));
            // AutoWorldMap.getPluginLogger().info(String.format("Sender: %s", event.getSender()));
            // AutoWorldMap.getPluginLogger().info(String.format("Receiver: %s", event.getReceiver()));
            // AutoWorldMap.getPluginLogger().warning("[-]============================================[-]");
        }
    }
}
