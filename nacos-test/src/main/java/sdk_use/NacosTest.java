package sdk_use;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author xh
 * @date 2025-01-16
 * @Description:
 */
public class NacosTest {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", "localhost:8848");
            properties.put(PropertyKeyConst.NAMESPACE, "dev");

            // 配置中心相关
            ConfigService configService = NacosFactory.createConfigService(properties);
            // 获取配置
            String config = configService.getConfig("test_config", "test", 3000);
            System.out.println(config);
            // 监听配置修改
            configService.addListener("test_config", "test", new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("配置被修改了！");
                    System.out.println("recieve1:" + configInfo);
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });

            // 注册中心相关
            NamingService namingService = NacosFactory.createNamingService(properties);
            // 注册实例 虽然实例没有对应的程序正在运行监听端口号，但这里仍可以注册到 nacos 上
            Instance instance = new Instance();
            instance.setIp("172.16.100.23");
            instance.setPort(8888);
            instance.setClusterName("DEFAULT");
            namingService.registerInstance("nacos.test.service", "test", instance);

            Instance instance2 = new Instance();
            instance2.setIp("127.0.0.1");
            instance2.setPort(9999);
            instance2.setClusterName("DEFAULT");
            namingService.registerInstance("nacos.test.service2", "test", instance2);

            try {
                // 不暂停直接获取不到注册过的实例
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 获取实例
            List<Instance> instances2 = namingService.getAllInstances("nacos.test.service", "test");
            for (Instance i : instances2) {
                System.out.println(i);
            }
            List<Instance> instances3 = namingService.getAllInstances("nacos.test.service2", "test");
            for (Instance i : instances3) {
                System.out.println(i);
            }

            // 测试让主线程不退出，因为订阅配置是守护线程，主线程退出守护线程就会退出。 正式代码中无需下面代码
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
