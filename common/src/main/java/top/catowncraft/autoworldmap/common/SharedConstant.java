package top.catowncraft.autoworldmap.common;

import lombok.Getter;

import java.util.logging.Logger;

public class SharedConstant {
    public static final String VOXEL_MAP_NAMESPACE = "worldinfo";
    public static final String VOXEL_MAP_NAME = "world_id";
    public static final String VOXEL_MAP_CHANNEL = VOXEL_MAP_NAMESPACE + ":" + VOXEL_MAP_NAME;

    public static final String XAERO_WORLD_MAP_NAMESPACE = "xaeroworldmap";
    public static final String XAERO_WORLD_MAP_NAME = "main";
    public static final String XAERO_WORLD_MAP_CHANNEL = XAERO_WORLD_MAP_NAMESPACE + ":" + XAERO_WORLD_MAP_NAME;

    public static final String XAERO_MINI_MAP_NAMESPACE = "xaerominimap";
    public static final String XAERO_MINI_MAP_NAME = "main";
    public static final String XAERO_MINI_MAP_CHANNEL = XAERO_MINI_MAP_NAMESPACE + ":" + XAERO_MINI_MAP_NAME;

    @Getter
    private static final Logger logger = Logger.getLogger("AutoWorldMap");
}
