package com.newbieking;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatClient {

    public static void main(String[] args) {
        new ChatClient("localhost", 8000).run();
    }

    private final String host;
    private final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channel.writeAndFlush(reader.readLine() + "\r\n");
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }

}
