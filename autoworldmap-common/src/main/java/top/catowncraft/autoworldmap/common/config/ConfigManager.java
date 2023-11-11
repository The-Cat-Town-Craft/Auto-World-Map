package top.catowncraft.autoworldmap.common.config;

import de.leonhard.storage.Yaml;
import lombok.Getter;
import top.catowncraft.autoworldmap.common.SharedConstant;

public class ConfigManager {
    private final Yaml config;
    @Getter
    private boolean voxelMapLegacyEnable;
    @Getter
    private boolean voxelMapModernEnable;
    @Getter
    private boolean xaeroMiniMapEnable;
    @Getter
    private boolean xaeroWorldMapEnable;

    public ConfigManager() {
        this.config = new Yaml("config", "./plugins/{plugin_name}");
        this.config.setHeader(SharedConstant.CONFIG_HEADER);
        this.load();
    }

    public void load() {
        this.voxelMapLegacyEnable = this.config.getOrSetDefault("packet.voxel_map.legacy", true);
        this.voxelMapModernEnable = this.config.getOrSetDefault("packet.voxel_map.modern", true);
        this.xaeroMiniMapEnable = this.config.getOrSetDefault("packet.xaero_map.mini", true);
        this.xaeroWorldMapEnable = this.config.getOrSetDefault("packet.xaero_map.world", true);
    }

    public void reload() {
        this.config.forceReload();
        this.load();
    }
}
