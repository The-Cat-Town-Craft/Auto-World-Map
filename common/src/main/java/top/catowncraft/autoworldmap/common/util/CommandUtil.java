package top.catowncraft.autoworldmap.common.util;

import top.catowncraft.autoworldmap.common.SharedConstant;
import top.catowncraft.autoworldmap.common.command.ICommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandUtil {
    public static boolean hasPermission(ICommandSender sender, String permission) {
        return sender.hasPermission(String.format("%s.%s", SharedConstant.getPluginPermissionRoot(), permission));
    }

    public static List<String> getArgs(String argumentString) {
        String[] args = argumentString.split(" ");
        if (args.length == 1 && args[0].equals("")) {
            return new ArrayList<>();
        }
        return Arrays.asList(args);
    }

    public static List<String> tweakArgs(String[] args) {
        ArrayList<String> ret = new ArrayList<>(Arrays.asList(args));
        if (!ret.isEmpty() && ret.get(ret.size() - 1).equals("")) {
            ret.remove(ret.size() - 1);
        }
        return ret;
    }
}
