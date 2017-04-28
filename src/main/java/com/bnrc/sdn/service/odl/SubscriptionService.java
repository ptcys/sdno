package com.bnrc.sdn.service.odl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


import com.bnrc.sdn.util.SpringContextUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;

import com.bnrc.sdn.properties.MongoProperties;
import com.bnrc.sdn.properties.OdlProperties;
import com.bnrc.sdn.util.SocketHandler;
import com.mongodb.MongoClient;

public class SubscriptionService {
	private OdlProperties odlProperties; 
	
	public void test(){
		System.out.println(odlProperties.getAddress());
	}
	public SubscriptionService(){
		odlProperties = (OdlProperties) SpringContextUtil.getBeanByClass(OdlProperties.class);
	}
	
	public  void topoSubscription()  {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpHeaders postheaders = new HttpHeaders();
		postheaders.setContentType(MediaType.APPLICATION_JSON);
		postheaders.set("AUTHORIZATION", "Basic YWRtaW46YWRtaW4=");
	
		JSONObject rqbody = new JSONObject();
		Map<String, String> rqbody_val = new HashMap<String,String>();
		rqbody_val.put("path", "/network-topology:network-topology");
		rqbody_val.put("sal-remote-augment:datastore","OPERATIONAL");
		rqbody_val.put("sal-remote-augment:scope","SUBTREE");
		
		try{
			rqbody.put("input", rqbody_val);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    HttpEntity<String> entity = new HttpEntity<String>(rqbody.toString(), postheaders);
	    ResponseEntity<String> response=null;
	    
	    try{
			response = restTemplate.
					postForEntity(new URI("http://"+odlProperties.getAddress()+":"+ odlProperties.getPort()+"/restconf/operations/sal-remote:create-data-change-event-subscription"),entity,String.class);
		//odlProperties.getAddress()+":"+ odlProperties.getPort()+
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    JSONObject rsbody = null;
	    try{
	    	rsbody = new JSONObject(response.getBody());
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    try{
	    	System.out.println("stream name:  "+rsbody.getJSONObject("output").getString("stream-name").toString());
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    
		HttpHeaders getheaders = new HttpHeaders();
		getheaders.set("AUTHORIZATION", "Basic YWRtaW46YWRtaW4=");
		
		String streamPath = "http://"+odlProperties.getAddress()+":"+ odlProperties.getPort()+"/restconf/streams/stream/";
		String streamName = null;
		try{
			streamName = rsbody.getJSONObject("output").getString("stream-name").toString();
		}catch(Exception e){
			e.printStackTrace();
		}
			URI wsLocation;
		
		HttpEntity<String> getEntity = new HttpEntity<String>(getheaders);
		
		URI uri = null;
		try{
			uri = new URI(streamPath+streamName); 
		}catch(Exception e){
			e.printStackTrace();
		}
		HttpEntity<String> getresponse = restTemplate.exchange(uri,HttpMethod.GET,getEntity,String.class);
		wsLocation = getresponse.getHeaders().getLocation();
		
		
		System.out.println("URI:  "+wsLocation);
		
		
		WebSocketClient client = new JettyWebSocketClient();
	
		
		SocketHandler handler = new SocketHandler("http://"+odlProperties.getAddress()+":"+ odlProperties.getPort());
		  WebSocketConnectionManager cManager = new WebSocketConnectionManager(client, handler, wsLocation.toString());
	}
	
	
	
//	public void subscription(SDNController controller){
//		
//	}
}
