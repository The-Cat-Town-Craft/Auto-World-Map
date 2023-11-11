package top.catowncraft.autoworldmap.common.util;

import io.netty.buffer.ByteBuf;
import top.catowncraft.autoworldmap.common.helper.BlockPos;

import java.nio.charset.StandardCharsets;

public class ByteBufUtil {
    private static final int SEGMENT_BITS = 0x7F;
    private static final int CONTINUE_BIT = 0x80;

    public static void writeUtf(ByteBuf buf, String string) {
        byte[] bs = string.getBytes(StandardCharsets.UTF_8);
        ByteBufUtil.writeVarInt(buf, bs.length);
        buf.writeBytes(bs);
    }

    public static void writeVarInt(ByteBuf buf, int value) {
        while (true) {
            if ((value & ByteBufUtil.SEGMENT_BITS) == 0) {
                buf.writeByte(value);
                return;
            }
            buf.writeByte((value & ByteBufUtil.SEGMENT_BITS | ByteBufUtil.CONTINUE_BIT));
            value >>>= 7;
        }
    }

    public static String readUtf(ByteBuf buf) {
        return buf.toString(buf.readerIndex(), ByteBufUtil.readVarInt(buf.copy()), StandardCharsets.UTF_8);
    }

    public static int readVarInt(ByteBuf buf) {
        int value = 0;
        int position = 0;
        byte currentByte;
        while (true) {
            currentByte = buf.readByte();
            value |= (currentByte & ByteBufUtil.SEGMENT_BITS) << position;
            if (((currentByte & ByteBufUtil.CONTINUE_BIT) == 0)) {
                break;
            }
            position += 7;
            if (position >= 32) {
                throw new RuntimeException("VarInt is too big");
            }
        }
        return value;
    }

    public static void writeBlockPos(ByteBuf buf, BlockPos pos) {
        buf.writeLong(((long) (pos.getX() & 0x3FFFFFF) << 38) | ((long) (pos.getZ() & 0x3FFFFFF) << 12) | (pos.getY() & 0xFFF));
    }

    public static void writeBlockPosLegacy(ByteBuf buf, BlockPos pos) {
        buf.writeLong(((long) (pos.getX() & 0x3FFFFFF) << 38) | ((long) (pos.getY() & 0xFFF) << 26) | (pos.getZ() & 0x3FFFFFF));
    }

    public static BlockPos readBlockPos(ByteBuf buf) {
        long pos = buf.readLong();
        return new BlockPos((int) (pos >> 38), (int) (pos << 52 >> 52), (int) (pos << 26 >> 38));
    }

    public static BlockPos readBlockPosLegacy(ByteBuf buf) {
        long pos = buf.readLong();
        return new BlockPos((int) (pos >> 38), (int) ((pos >> 26) & 0xFFF), (int) (pos << 38 >> 38));
    }
}
