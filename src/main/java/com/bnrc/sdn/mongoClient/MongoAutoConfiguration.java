package com.bnrc.sdn.mongoClient;

import java.net.UnknownHostException;  
  








import javax.annotation.PreDestroy;  
  








import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;  
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;  
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;  
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;  
import org.springframework.core.env.Environment;  
import org.springframework.stereotype.Component;

import com.bnrc.sdn.properties.MongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
  


@Configuration  
@ConditionalOnClass(MongoClient.class)  
@EnableConfigurationProperties(MongoProperties.class)  
@ConfigurationProperties
//@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")  //?????????????????

public class MongoAutoConfiguration {
	

	@Autowired  
    private MongoProperties properties;  
  
    @Autowired(required = false)  
    private MongoClientOptions options;  
  
    @Autowired  
    private Environment environment;  
  
    private MongoClient mongo;  
  
    @PreDestroy  
    public void close() {  
        if (this.mongo != null) {  
            this.mongo.close();  
        }  
    }  
  
    @Bean
//    @ConditionalOnMissingBean  //????????????????
    public MongoClient mongo() {
    	try {
    		this.mongo = new MongoClient(properties.getHost(),properties.getPort());
    	}catch (Exception e){
    		e.printStackTrace();
    	}
        return this.mongo;  
    }  
}
