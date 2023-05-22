package com.lemi.accountinfomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class AccountInfoMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountInfoMicroserviceApplication.class, args);
    }

}
