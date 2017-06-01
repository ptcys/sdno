package com.bnrc.sdn.orchestrator.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bnrc.sdn.mongoClient.MongoDataAutoConfiguration;
import com.bnrc.sdn.properties.MongoProperties;
import com.bnrc.sdn.resource.Dp;
import com.bnrc.sdn.resource.topo.Topo;
import com.bnrc.sdn.service.DpInventoryService;
import com.bnrc.sdn.service.TopoService;

@RestController
@RequestMapping("/topo")
@EnableConfigurationProperties(TopoService.class)    
public class TopoController {
	private  Logger logger = LoggerFactory.getLogger(TopoController.class);
	
	@Autowired
	TopoService topoService;
	
	@RequestMapping(value = "/all" , method = RequestMethod.GET)
	public List<Topo> getAllDp(){
		logger.info("Topo getAll");
		return topoService.findAll();
	}
	
	

}
