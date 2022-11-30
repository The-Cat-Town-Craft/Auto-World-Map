package top.catowncraft.autoworldmap.common.config;

import de.leonhard.storage.Yaml;
import lombok.Getter;

public class ConfigManager {
    @Getter
    private final boolean xaeroMapEnable;
    @Getter
    private final boolean voxelMapEnable;

    public ConfigManager() {
        Yaml config = new Yaml("config", "./plugins/{plugin_name}");
        this.xaeroMapEnable = config.getOrSetDefault("packet.voxel_map", true);
        this.voxelMapEnable = config.getOrSetDefault("packet.xaero_map", true);
    }
}
