package com.bnrc.sdn.orchestrator;


import java.util.List;

import com.bnrc.sdn.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.context.properties.EnableConfigurationProperties;  

import com.bnrc.sdn.mongoClient.MongoAutoConfiguration;
import com.bnrc.sdn.mongoClient.MongoDataAutoConfiguration;
import com.bnrc.sdn.properties.OdlProperties;
import com.bnrc.sdn.resource.Dp;
import com.bnrc.sdn.resource.Greeting;
import com.bnrc.sdn.service.DpInventoryService;
import com.bnrc.sdn.service.odl.SubscriptionService;
import com.bnrc.sdn.util.SpringContextUtil;



@Configuration  
@ComponentScan(basePackages={"com.bnrc.sdn.orchestrator"})
@EnableAutoConfiguration  
@EnableConfigurationProperties({MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,OdlProperties.class})  //!!

@SpringBootApplication

public class Application {//implements CommandLineRunner{


    public static void main(String[] args) {
        ApplicationContext applicationContext =  SpringApplication.run(Application.class, args);
        SpringContextUtil.setApplicationContext(applicationContext);

        SubscriptionService subscriptionService = new SubscriptionService();
        subscriptionService.topoSubscription();
//        subscriptionService.test();

    }
 
}
