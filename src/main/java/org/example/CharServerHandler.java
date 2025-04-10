package org.example;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.DefaultEventExecutor;

public class CharServerHandler extends ChannelInboundHandlerAdapter {

    private static final ChannelGroup channels = new DefaultChannelGroup(new DefaultEventExecutor());


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incomingChannel = ctx.channel();
        // broadcast all channel;
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + channel.remoteAddress() + " has joined!\n");
        }
        channels.add(incomingChannel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incomingChannel = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + channel.remoteAddress() + " has left!\n");
        }
        channels.remove(incomingChannel);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel incomingChannel = ctx.channel();
        for (Channel channel : channels) {
            if (channel != incomingChannel) {
                channel.writeAndFlush("[" + incomingChannel.remoteAddress() + "] " + msg + "\n");
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
    }
}
