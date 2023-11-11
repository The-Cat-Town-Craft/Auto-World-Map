package top.catowncraft.autoworldmap.velocity.command;

import com.velocitypowered.api.command.RawCommand;
import top.catowncraft.autoworldmap.common.command.AutoWorldMapCommand;
import top.catowncraft.autoworldmap.common.util.CommandUtil;

import java.util.List;

public class AutoWorldMapCommandVelocity implements RawCommand {
    private final AutoWorldMapCommand command = new AutoWorldMapCommand();

    @Override
    public void execute(Invocation invocation) {
        this.command.execute(new CommandSenderImpl(invocation.source()), CommandUtil.getArgs(invocation.arguments()));
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        return this.command.doCompletion(new CommandSenderImpl(invocation.source()), CommandUtil.getArgs(invocation.arguments()));
    }
}
