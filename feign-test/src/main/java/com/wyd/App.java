package com.wyd;

import com.wyd.client.FeignTestClient;
import com.wyd.common.AjaxResult;
import com.wyd.common.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class App {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        FeignTestClient client = context.getBean(FeignTestClient.class);

        AjaxResult<UserEntity> result = client.feignTest("111", 111L);
        System.out.println(result);

    }


}
