package com.bnrc.sdn.orchestrator.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bnrc.sdn.mongoClient.MongoDataAutoConfiguration;
import com.bnrc.sdn.service.DpInventoryService;
import com.bnrc.sdn.util.FlowUtil;
import com.bnrc.sdn.resource.Greeting;
import com.bnrc.sdn.resource.flow.Flow;
import com.bnrc.sdn.resource.topo.*;
import com.sun.webkit.ContextMenu.ShowContext;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.algorithms.shortestpath.PrimMinimumSpanningTree;

@RestController
@RequestMapping("/network")
@AutoConfigureAfter(MongoDataAutoConfiguration.class)
public class NetworkController {
	
	private  Logger logger = LoggerFactory.getLogger(DpInventoryService.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	Graph<String, Link> networkGraph = null;
    Set<String> linkAdded = new HashSet<String>();
    
    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public Greeting hello(){
    	return new Greeting(10,"hello");
    }
    
	@RequestMapping(value = "/shortestPath" , method = RequestMethod.GET)
	public String setShortestPath(@RequestParam(value="host1")Integer host1Id,@RequestParam(value="host2")Integer host2Id){
		Topo topo = mongoTemplate.findAll(Topo.class).get(0);
		List<Link> links = topo.getLink();
		List<Node> nodes = topo.getNode();
		
		if (links == null || links.isEmpty()) {
			logger.info("In addLinks: No link added as links is null or empty.");
          return new String("no");
        }

        if (networkGraph == null) {
            networkGraph = new SparseMultigraph<>();
        }

        for (Link link : links) {
//            if (linkAlreadyAdded(link)) {
//                continue;
//            }
            String sourceNodeId = link.getSource().getSourceNode();
            String destinationNodeId = link.getDestination().getDestNode();
            networkGraph.addVertex(sourceNodeId);
            networkGraph.addVertex(destinationNodeId);
            networkGraph.addEdge(link, sourceNodeId, destinationNodeId, EdgeType.DIRECTED);
            System.out.println("source node id:"+sourceNodeId);
            System.out.println("destination node id:"+destinationNodeId);      
		}
        System.out.println("link:"+networkGraph.getEdgeCount());
        DijkstraShortestPath<String,Link> shortestPath = new DijkstraShortestPath<>(networkGraph);
        
        System.out.println(nodes.get(2).getNodeId()+":"+nodes.get(0).getNodeId());
        for(Link i : shortestPath.getPath(nodes.get(2).getNodeId(), nodes.get(0).getNodeId())){
        	System.out.println(i.toString());
        }
        
        System.out.println(nodes.get(1).getNodeId()+":"+nodes.get(0).getNodeId());
        for(Link i : shortestPath.getPath(nodes.get(0).getNodeId(), nodes.get(1).getNodeId())){
        	System.out.println(i.toString());
        }
        List<Link> resultlinks = shortestPath.getPath(nodes.get(1).getNodeId(), nodes.get(0).getNodeId());
        installFlowByShortestPath(resultlinks);
        return new String("ok");
	}
	private boolean linkAlreadyAdded(Link link) {
        String linkAddedKey = null;
        if (link.getDestination().getDestTp().hashCode() > link.getSource().getSourceTp().hashCode()) {
            linkAddedKey = link.getSource().getSourceTp() + link.getDestination().getDestTp();
        } else {
            linkAddedKey = link.getDestination().getDestTp() + link.getSource().getSourceTp();
        }
        if (linkAdded.contains(linkAddedKey)) {
            return true;
        } else {
            linkAdded.add(linkAddedKey);
            return false;
        }
    }
	private boolean installFlowByShortestPath(List<Link> links){
		
//		// write your code here
//        //System.out.print(Base64.getEncoder().encodeToString(("admin" + ":" + "123456").getBytes()));
//        FlowUtil flowUtil = new FlowUtil("172.17.17.9",8080,"admin","admin","default");
//        //odlUtil.getTopology();
//        Flow flow = new Flow();
//        flow.setName("12");
//        com.bnrc.sdn.resource.flow.Node node = new com.bnrc.sdn.resource.flow.Node();
//        node.setId("openflow:1");
//        node.setType("OF");
//        flow.setNode(node);
//        List<String> actions = new ArrayList<String>();
//        actions.add("SET_NW_TOS=63");
//        flow.setActions(actions);
//        flow.setNwSrc("10.0.0.1");
//        flow.setNwDst("10.0.0.3");
//        flowUtil.installFlow(flow);
//        
//		return true;
//		
		 FlowUtil flowUtil = new FlowUtil("172.17.17.9",8080,"admin","admin");
		 flowUtil.getTopology();
	        return true;
	}
}
