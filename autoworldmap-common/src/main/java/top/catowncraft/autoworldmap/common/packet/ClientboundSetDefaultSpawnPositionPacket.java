package top.catowncraft.autoworldmap.common.packet;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.mapping.AbstractProtocolMapping;
import dev.simplix.protocolize.api.mapping.ProtocolIdMapping;
import dev.simplix.protocolize.api.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;
import lombok.*;
import top.catowncraft.autoworldmap.common.helper.BlockPos;
import top.catowncraft.autoworldmap.common.util.ByteBufUtil;

import java.util.Arrays;
import java.util.List;

import static dev.simplix.protocolize.api.util.ProtocolVersions.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClientboundSetDefaultSpawnPositionPacket extends AbstractPacket {
    public final static List<ProtocolIdMapping> MAPPINGS = Arrays.asList(
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_8, MINECRAFT_1_8, 0x05),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_9, MINECRAFT_1_11_2, 0x43),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_12, MINECRAFT_1_12, 0x45),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_12_1, MINECRAFT_1_12_2, 0x46),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_13, MINECRAFT_1_13_2, 0x49),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_14, MINECRAFT_1_14_4, 0x4D),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_15, MINECRAFT_1_15_2, 0x4E),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16, MINECRAFT_1_16_5, 0x42),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_17, MINECRAFT_1_18_2, 0x4B),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19, MINECRAFT_1_19, 0x4A),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19_1, MINECRAFT_1_19_2, 0x4D),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19_2, MINECRAFT_1_19_3, 0x4C)
    );

    private BlockPos blockPos;
    private float angle;

    @Override
    public void read(ByteBuf buf, PacketDirection packetDirection, int protocolVersion) {
        this.blockPos = protocolVersion >= MINECRAFT_1_14 ? ByteBufUtil.readBlockPos(buf) : ByteBufUtil.readBlockPosLegacy(buf);
        if (protocolVersion >= MINECRAFT_1_17) {
            this.angle = buf.readFloat();
        }
    }

    @Override
    public void write(ByteBuf buf, PacketDirection packetDirection, int protocolVersion) {
        if (protocolVersion >= MINECRAFT_1_14) {
            ByteBufUtil.writeBlockPos(buf, this.blockPos);
        } else {
            ByteBufUtil.writeBlockPosLegacy(buf, this.blockPos);
        }
        if (protocolVersion >= MINECRAFT_1_17) {
             buf.writeFloat(this.angle);
        }
    }
}
