package top.catowncraft.autoworldmap.common.packet;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.mapping.AbstractProtocolMapping;
import dev.simplix.protocolize.api.mapping.ProtocolIdMapping;
import dev.simplix.protocolize.api.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;
import lombok.*;
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
public class ClientboundCustomPayloadPacket extends AbstractPacket {
    public final static List<ProtocolIdMapping> MAPPINGS = Arrays.asList(
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_8, MINECRAFT_1_8, 0x3F),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_9, MINECRAFT_1_12_2, 0x18),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_13, MINECRAFT_1_13_2, 0x19),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_14, MINECRAFT_1_14_4, 0x18),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_15_2, MINECRAFT_1_15_2, 0x19),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16, MINECRAFT_1_16_1, 0x18),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16_2, MINECRAFT_1_16_5, 0x17),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_17, MINECRAFT_1_18_2, 0x18),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19, MINECRAFT_1_19, 0x15),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19_1, MINECRAFT_1_19_2, 0x16)
    );

    private String identifier = "minecraft";
    private ByteBuf data;

    @Override
    public void read(ByteBuf buf, PacketDirection packetDirection, int protocolVersion) {
        this.identifier = ByteBufUtil.readUtf(buf);
        this.data = buf.readBytes(buf.readableBytes());
    }

    @Override
    public void write(ByteBuf buf, PacketDirection packetDirection, int protocolVersion) {
        ByteBufUtil.writeUtf(buf, this.identifier);
        buf.writeBytes(this.data.copy());
    }
}
