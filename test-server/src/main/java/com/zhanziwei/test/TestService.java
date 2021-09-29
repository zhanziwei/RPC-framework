package com.zhanziwei.test;

import com.zhanziwei.rpc.core.server.RpcServer;

public class TestService {
    public static void main(String[] args) {
        ResourceTrackerService resourceTrackerService = new ResourceTrackerService();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(resourceTrackerService, 9000);
    }
}
