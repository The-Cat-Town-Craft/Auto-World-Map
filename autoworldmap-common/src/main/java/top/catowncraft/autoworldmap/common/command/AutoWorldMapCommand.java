package top.catowncraft.autoworldmap.common.command;

import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.util.CommandUtil;

import java.util.ArrayList;
import java.util.List;

public class AutoWorldMapCommand {
    public List<String> doCompletion(ICommandSender sender, List<String> args) {
        List<String> ret = new ArrayList<>();
        if (args.size() == 0) {
            if (CommandUtil.hasPermission(sender, "reload")) {
                ret.add("reload");
            }
        }
        return ret;
    }

    public void execute(ICommandSender sender, List<String> args) {
        if (args.size() == 0) {
            sender.sendFeedback(String.format("§a%s §bv%s §7by §6%s", SharedConstant.getPluginName(),
                    SharedConstant.getPluginVersion(), SharedConstant.getPluginAuthor()));
        } else if (args.size() == 1 && args.get(0).equals("reload")) {
            SharedConstant.getConfig().reload();
            sender.sendFeedback("§aConfiguration reload successful!");
        } else {
            sender.sendFeedback("§cUnknown command!");
        }
    }
}
