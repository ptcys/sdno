package com.bnrc.sdn.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import com.bnrc.sdn.mongoClient.MongoAutoConfiguration;
import com.bnrc.sdn.mongoClient.MongoDataAutoConfiguration;
import com.bnrc.sdn.resource.Dp;
import com.mongodb.DBCollection;

@Service
@ConfigurationProperties
@AutoConfigureAfter(MongoDataAutoConfiguration.class)  
public class DpInventoryService {
	private  Logger logger = LoggerFactory.getLogger(DpInventoryService.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Dp> findAll(){
		if(mongoTemplate!=null){
    		System.out.println("mongo 已启动");
    	}else
		System.out.println("mongo 未启动");
		return mongoTemplate.findAll(Dp.class);
		
	}
	

//	@Autowired(required=true)
//	public DpRepository dpRepository; 
//	
//	public List<Dp> findAll(){
//		List<Dp> dps = new ArrayList<Dp>();
//		try {
//			dps  = dpRepository.findAll();	    	   
//		} catch (Exception e) {
//				logger.error("Can not get all topo from database.");;
//		}
//		return dps;
//		
//	}
//	
//	public Dp findByDpId(Integer dpId){
//		Dp dp = new Dp();
//		try{
//			dp = dpRepository.findByDpId(dpId);
//		}catch(Exception e){
//			logger.error("Can not get "+dpId+" dp from database.");;
//		}
//		return dp;
//	}
//	
//	public Dp findByIp(String ip){
//		Dp dp = new Dp();
//		try{
//			dp = dpRepository.findByIp(ip);
//		}catch(Exception e){
//			logger.error("Can not get "+ip+" dp from database.");;
//		}
//		return dp;
//	}
//	
//	public Dp findByDpIdAndIp(Integer dpId, String ip){
//		Dp dp = new Dp();
//		try{
//			dp = dpRepository.findByDpIdAndIp(dpId, ip);
//		}catch(Exception e){
//			logger.error("Can not get dp from database.");;
//		}
//		return dp;
//	}
	
}
