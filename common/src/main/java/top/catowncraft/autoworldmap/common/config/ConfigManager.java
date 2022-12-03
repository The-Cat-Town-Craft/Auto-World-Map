package top.catowncraft.autoworldmap.common.config;

import de.leonhard.storage.Yaml;
import lombok.Getter;

public class ConfigManager {
    private final Yaml config;
    @Getter
    private boolean xaeroMapEnable;
    @Getter
    private boolean voxelMapEnable;

    public ConfigManager() {
        this.config = new Yaml("config", "./plugins/{plugin_name}");
        this.load();
    }

    public void load() {
        this.xaeroMapEnable = this.config.getOrSetDefault("packet.voxel_map", true);
        this.voxelMapEnable = this.config.getOrSetDefault("packet.xaero_map", true);
    }

    public void reload() {
        this.config.forceReload();
        this.load();
    }
}
