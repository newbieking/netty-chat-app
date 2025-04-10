package com.newbieking;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {

    public static void main(String[] args) {
        int port = 8000;
        if (args.length > 0) {
            String argv2 = args[0];
            try {
                port = Integer.parseInt(argv2);
                if (port < 1024 || port > 65535) {
                    throw new OutOfRangeException("port out of range [1024, 65535]");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number: " + port);
                throw new RuntimeException(e);
            }
        }
        new ChatServer(port).run();
    }


    private final int port;

    public ChatServer(int port) {
        this.port = port;
    }


    public void run() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitializer());

            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
