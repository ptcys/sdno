package com.bnrc.sdn.orchestrator;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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



@Configuration  
@ComponentScan(basePackages={"com.bnrc.sdn.config","com.bnrc.sdn.orchestrator"})  
@EnableAutoConfiguration  
@EnableConfigurationProperties({MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,SubscriptionService.class})  //!!
@SpringBootApplication

public class Application implements CommandLineRunner{

	@Autowired
	private SubscriptionService subscriptionService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    public void run(String... args) throws Exception {
    	subscriptionService.topoSubscription();
    }
}