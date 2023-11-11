package top.catowncraft.autoworldmap.common.command;

public interface ICommandSender {
    void sendFeedback(String msg);

    void sendFeedback(String msg, Object... objects);

    boolean hasPermission(String permission);
}
