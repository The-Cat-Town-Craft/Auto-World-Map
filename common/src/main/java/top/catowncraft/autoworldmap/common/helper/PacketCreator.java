package top.catowncraft.autoworldmap.common.helper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

public class PacketCreator {
    public static byte[] voxelMap(String serverName) {
        byte[] serverNameByte = serverName.getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(0);
        buf.writeByte(serverNameByte.length);
        buf.writeBytes(serverNameByte);
        byte[] voxelArray = buf.array();
        buf.release();
        return voxelArray;
    }

    public static ByteBuf xaeroMap(String serverName) {
        byte[] serverNameByte = serverName.getBytes(StandardCharsets.UTF_8);
        CRC32 crc32 = new CRC32();
        crc32.update(serverNameByte, 0, serverNameByte.length);
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(0);
        buf.writeInt((int) crc32.getValue());
        return buf;
    }
}
