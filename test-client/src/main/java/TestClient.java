import com.zhanziwei.rpc.api.HelloRequest;
import com.zhanziwei.rpc.api.ResourceTracker;
import com.zhanziwei.rpc.core.client.RpcClientProxy;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        ResourceTracker resourceTracker = proxy.getProxy(ResourceTracker.class);
        HelloRequest helloRequest = new HelloRequest(12, "This is a message");
        String res = resourceTracker.registerNodeManager(helloRequest);
        System.out.println(res);
    }
}
