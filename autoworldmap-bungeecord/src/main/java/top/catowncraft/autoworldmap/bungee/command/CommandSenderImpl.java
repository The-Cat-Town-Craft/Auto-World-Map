package top.catowncraft.autoworldmap.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import top.catowncraft.autoworldmap.common.command.ICommandSender;

public class CommandSenderImpl implements ICommandSender {
    private final CommandSender sender;

    public CommandSenderImpl(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendFeedback(String msg) {
        this.sender.sendMessage(new TextComponent(msg));
    }

    @Override
    public void sendFeedback(String msg, Object... objects) {
        this.sender.sendMessage(new TextComponent(String.format(msg, objects)));
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.sender.hasPermission(permission);
    }
}
