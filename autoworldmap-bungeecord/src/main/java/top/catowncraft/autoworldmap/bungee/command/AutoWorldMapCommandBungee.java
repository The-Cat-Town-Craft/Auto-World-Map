package top.catowncraft.autoworldmap.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.command.AutoWorldMapCommand;
import top.catowncraft.autoworldmap.common.util.CommandUtil;

public class AutoWorldMapCommandBungee extends Command implements TabExecutor {
    private final AutoWorldMapCommand command = new AutoWorldMapCommand();

    public AutoWorldMapCommandBungee() {
        super(SharedConstant.getPluginID());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        this.command.execute(new CommandSenderImpl(sender), CommandUtil.tweakArgs(args));
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return this.command.doCompletion(new CommandSenderImpl(sender), CommandUtil.tweakArgs(args));
    }
}
