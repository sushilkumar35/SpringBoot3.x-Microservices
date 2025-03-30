package com.springmicroservicedemo.config;

import com.springmicroservicedemo.client.DepartmentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Value("${department.service.base.url}")
    private String departmentServiceBaseUrl;

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    // creating a webclient for department service and using filter function to perform load balancing
    @Bean
    public WebClient departmentWebClient() {
        return WebClient.builder()
                .baseUrl(departmentServiceBaseUrl)
                .filter(filterFunction)
                .build();
    }

    //Below method is used to tell web client to connect with Department client through HTTP Exchange

    @Bean
    public DepartmentClient departmentClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(departmentWebClient()))
                .build();
        return httpServiceProxyFactory
                .createClient((DepartmentClient.class));
    }
}
