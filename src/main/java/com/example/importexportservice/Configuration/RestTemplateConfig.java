package com.example.importexportservice.Configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate SupplierRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    @LoadBalanced
    public RestTemplate OrderRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    @LoadBalanced
    public RestTemplate OperatinRestTemplate(){
        return new RestTemplate();
    }

}
