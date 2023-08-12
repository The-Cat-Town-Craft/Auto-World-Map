package top.catowncraft.autoworldmap.common;

import lombok.Getter;
import top.catowncraft.autoworldmap.common.config.ConfigManager;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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
    private static final String pluginAuthor = "{plugin_author_string}";
    @Getter
    private static final String pluginDescription = "{plugin_description}";
    @Getter
    private static final String pluginID = "{plugin_id}";
    @Getter
    private static final String pluginName = "{plugin_name}";
    @Getter
    private static final String pluginPermissionRoot = "{plugin_permission_root}";
    @Getter
    private static final String pluginUrl = "{plugin_url}";
    @Getter
    private static final String pluginVersion = "{plugin_version}";

    public static final List<String> CONFIG_HEADER = Arrays.asList(
            " ==================================================",
            "         Auto World Map configuration file",
            "",
            String.format(" Copyright (c) 2020 - %s The Cat Town Craft and ", Calendar.getInstance().get(Calendar.YEAR)),
            " contributors.",
            " =================================================="
    );

    @Getter
    private static final Logger logger = Logger.getLogger("AutoWorldMap");

    @Getter
    private static final ConfigManager config = new ConfigManager();
}
