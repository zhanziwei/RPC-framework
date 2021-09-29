package com.zhanziwei.test;

import com.zhanziwei.rpc.api.HelloRequest;
import com.zhanziwei.rpc.api.ResourceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceTrackerService implements ResourceTracker {
    private static final Logger logger = LoggerFactory.getLogger(ResourceTrackerService.class);


    public String registerNodeManager(HelloRequest request) {
        logger.info("接收到：{}", request.getMessage());
        return "这是调用的返回值，id="+request.getId();
    }
}
