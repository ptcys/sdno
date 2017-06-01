package com.bnrc.sdn.util;

import com.bnrc.sdn.resource.flow.Flow;
import com.google.gson.Gson;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hupeng on 16-11-15.
 */
public class FlowUtil {
    private String url = "";
    private String username;
    private String password;

    /**
     * 构造函数
     * @param host OpenDayLight的北向接口的主机名称
     * @param port OpenDayLight的北向接口的端口
     * @param username OpenDayLight的管理员的用户名
     * @param password OpenDayLight的管理员的密码
     * */
    public FlowUtil(String host,int port,String username,String password){
        this.url = "http://" + host + ":" + port;
        this.username = username;
        this.password = password;
        HttpRequest.setBasicAuth(getBasicAuthStr(username,password));
    }

    /**
     * 获取拓扑信息
     * */
    public String getTopology(){

        String str = HttpRequest.sendGet(url + "/controller/nb/v2/topology/default" ,"");
//        String str = HttpRequest.sendGet("http://172.17.17.9:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:1/flow-node-inventory:table/0","");
        System.out.print("!!"+str);
        return str;
    }



    /**
     * 获取主机信息
     * */
    public String getHosts(){

        ///controller/nb/v2/topology/' + str(container_name)
    	//！！改
        String str = HttpRequest.sendGet(url + "/controller/nb/v2/topology/" ,"");

        System.out.println(str);

        return str;
    }

    /**
     * 下发流表
     * */
    public String installFlow(Flow flow){
        String json  = new Gson().toJson(flow);
        Map<String,String >headers = new HashMap<>();
        headers.put("Accept","application/json'");
        headers.put("Content-type","application/json");
        try {
        	
        	//!!改
            String str = HttpRequest.sendPut(url + "/controller/nb/v2/flowprogrammer/"  + "/node/OF/" + flow.getNode().getId() + "/staticFlow/" + flow.getName(),headers,json);
            System.out.println(str);
            return str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private String getBasicAuthStr(String name,String password){
        return "Basic " + Base64.getEncoder().encodeToString((name + ":" + password).getBytes());
    }

}
