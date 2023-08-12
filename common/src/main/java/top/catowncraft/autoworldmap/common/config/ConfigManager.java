package top.catowncraft.autoworldmap.common.config;

import de.leonhard.storage.Yaml;
import lombok.Getter;
import top.catowncraft.autoworldmap.common.SharedConstant;

public class ConfigManager {
    private final Yaml config;
    @Getter
    private boolean xaeroMapEnable;
    @Getter
    private boolean voxelMapLegacyEnable;
    @Getter
    private boolean voxelMapModernEnable;

    public ConfigManager() {
        this.config = new Yaml("config", "./plugins/{plugin_name}");
        this.config.setHeader(SharedConstant.CONFIG_HEADER);
        this.load();
    }

    public void load() {
        this.voxelMapLegacyEnable = this.config.getOrSetDefault("packet.voxel_map.legacy", true);
        this.voxelMapModernEnable = this.config.getOrSetDefault("packet.voxel_map.modern", true);
        this.xaeroMapEnable = this.config.getOrSetDefault("packet.xaero_map", true);
    }

    public void reload() {
        this.config.forceReload();
        this.load();
    }
}
