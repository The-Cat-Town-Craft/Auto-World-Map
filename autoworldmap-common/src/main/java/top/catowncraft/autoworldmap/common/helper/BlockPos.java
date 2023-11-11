package top.catowncraft.autoworldmap.common.helper;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BlockPos {
    private int x;
    private int y;
    private int z;
}
