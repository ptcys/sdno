package com.bnrc.sdn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.bnrc.sdn.mongoClient.MongoDataAutoConfiguration;
import com.bnrc.sdn.resource.topo.Topo;

@Service
@ConfigurationProperties
@AutoConfigureAfter(MongoDataAutoConfiguration.class)  
public class TopoService {
private  Logger logger = LoggerFactory.getLogger(TopoService.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Topo> findAll(){
		if(mongoTemplate!=null){
    		System.out.println("mongo 已启动");
    	}else
    		System.out.println("mongo 未启动");
		return mongoTemplate.findAll(Topo.class);
		
	}
	
}
