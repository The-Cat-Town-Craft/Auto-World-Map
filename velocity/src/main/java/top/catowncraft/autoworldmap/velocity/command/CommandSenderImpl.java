package top.catowncraft.autoworldmap.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.text.Component;
import top.catowncraft.autoworldmap.common.command.ICommandSender;

public class CommandSenderImpl implements ICommandSender {
    private final CommandSource source;

    public CommandSenderImpl(CommandSource source) {
        this.source = source;
    }

    @Override
    public void sendFeedback(String msg) {
        this.source.sendMessage(Component.text(msg));
    }

    @Override
    public void sendFeedback(String msg, Object... objects) {
        this.source.sendMessage(Component.text(String.format(msg, objects)));
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.source.hasPermission(permission);
    }
}
