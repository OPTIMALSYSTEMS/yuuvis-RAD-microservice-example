
package com.os.services.demo;

import feign.jackson.JacksonEncoder;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJms
@EnableFeignClients(defaultConfiguration = JacksonEncoder.class)
public class DemoApplication implements ApplicationContextAware
{
    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException
    {
        // https://github.com/springfox/springfox/issues/1074
        // // force the bean to get loaded as soon as possible
        ac.getBean("requestMappingHandlerAdapter");
    }

}
