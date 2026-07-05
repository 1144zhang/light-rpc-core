package com.zhang;

import com.zhang.Hello;
import com.zhang.HelloService;
import com.zhang.annotation.RpcScan;
import com.zhang.remoting.transport.netty.server.NettyRpcServer;
import com.zhang.serviceimpl.HelloServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Server: Automatic registration service via @RpcService annotation
 *
 * @author shuang.kou
 * @createTime 2020年05月10日 07:25:00
 */
@RpcScan(basePackage = {"com.zhang"})
public class NettyServerMain {
    public static void main(String[] args) {
        autoRegistry();
    }

    public static void autoRegistry() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        HelloService helloService = applicationContext.getBean(HelloServiceImpl.class);
        helloService.hello(new Hello("你好fzk", "你好服务端"));
        nettyRpcServer.start();
    }
}
