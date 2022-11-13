package top.catowncraft.autoworldmap.bungee;

import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Logger;

public final class AutoWorldMap extends Plugin {
    private static final Logger LOGGER = Logger.getLogger("AutoWorldMap");

    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerListener(this, new VoxelMapListener());
    }

    @Override
    public void onDisable() {
    }

    public static Logger getPluginLogger() {
        return LOGGER;
    }
}
