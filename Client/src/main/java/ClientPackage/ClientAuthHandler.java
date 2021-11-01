package ClientPackage;

import Common.Constants;
import Common.CommonHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import javax.swing.*;

public class ClientAuthHandler extends ChannelInboundHandlerAdapter {

    private static boolean authOk = false;
    private static String nick;
    private int nextLength;

    public static String getNick() {
        return nick;
    }

    public static boolean isAuthOk() {
        return authOk;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (authOk) {
            ctx.fireChannelRead(msg);
        } else {
            ByteBuf buf = ((ByteBuf) msg);
            byte authByte = buf.readByte();
            if (authByte == Constants.AUTH_OK) {
                if (buf.readableBytes() >= 4) {
                    nextLength = buf.readInt();
                }
                if (buf.readableBytes() >= nextLength) {
                    byte[] nickByte = new byte[nextLength];
                    buf.readBytes(nickByte);
                    nick = new String(nickByte); //
                    authOk = true;
                    ctx.pipeline().addLast((ChannelHandler) new CommonHandler(("client_storage/" + ClientAuthHandler.getNick()), new ClientCommandReceiver()));
                    Network.sendMsg(() -> {
                        JOptionPane.showConfirmDialog(null, "Authorization successful");
                    });
                }
            }
            if (authByte == Constants.AUTH_NOT_OK) {
                Network.sendMsg(() -> {
                    JOptionPane.showConfirmDialog(null, "Wrong Login or Password");
                });
            }
            if (authByte == Constants.REG_NOT_OK) {
                Network.sendMsg(() -> {
                    JOptionPane.showConfirmDialog(null, "User already exist");
                });
            }
            if (authByte == Constants.REG_OK) {
                Network.sendMsg(() -> {
                    JOptionPane.showConfirmDialog(null, "Registration Successful. Please Authorize");
                });
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
