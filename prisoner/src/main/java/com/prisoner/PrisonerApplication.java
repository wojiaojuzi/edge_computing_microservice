package com.prisoner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableScheduling
@Configuration
@MapperScan("com.prisoner.mapper")
public class PrisonerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PrisonerApplication.class, args);
        String exe = "python";
        String command = "./py/weighted_risk_score.py";
        String[] cmdArr = new String[]{exe, command};
        Process process = Runtime.getRuntime().exec(cmdArr);
    }
    @Autowired
    private RestTemplateBuilder builder;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
}
