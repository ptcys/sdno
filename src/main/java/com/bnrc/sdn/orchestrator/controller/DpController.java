package com.bnrc.sdn.orchestrator.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bnrc.sdn.properties.MongoProperties;
import com.bnrc.sdn.resource.Dp;
import com.bnrc.sdn.service.DpInventoryService;

@RestController
@RequestMapping("/dp")
@EnableConfigurationProperties(DpInventoryService.class)  
public class DpController {
	private  Logger logger = LoggerFactory.getLogger(DpController.class);
	
	@Autowired
	private DpInventoryService dpInventoryService;
	
	@RequestMapping(value = "/all" , method = RequestMethod.GET)
	public List<Dp> getAllDp(){
		logger.info("Dp getAll");
		return dpInventoryService.findAll();
	}
	
//	@RequestMapping(value = "/dpId", method = RequestMethod.GET)
//	public Dp getDpByDpId(@RequestParam(value="dpId")Integer dpId){
//		return dpInventoryService.findByDpId(dpId);
//	}
//	
//	@RequestMapping(value = "/ip", method = RequestMethod.GET)
//	public Dp getDpByIp(@RequestParam(value="ip")String ip){
//		return dpInventoryService.findByIp(ip);
//	}
//	
//	@RequestMapping(value = "/dpIdAndIp", method = RequestMethod.GET)
//	public Dp getDpByDpIdAndIp(@RequestParam(value="dpId")Integer dpId,@RequestParam(value="ip")String ip){
//		return dpInventoryService.findByDpIdAndIp(dpId, ip);
//	}
	

}
