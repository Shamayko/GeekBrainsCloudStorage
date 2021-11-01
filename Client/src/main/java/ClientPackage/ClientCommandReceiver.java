package ClientPackage;

import Common.CommandReceiver;
import io.netty.channel.ChannelHandlerContext;

public class ClientCommandReceiver extends CommandReceiver {

    @Override
    public void parseCommand(ChannelHandlerContext ctx, String cmd) {
        throw new IllegalStateException("No commands detected");
    }

}
