package com.bnrc.sdn.resource.flow;

import java.util.List;

/**
 * Created by dxc on 16-11-21.
 */
public class Flow {
    private String tpSrc;
    private String protocol;
    private String vlanId;
    private Node node;
    private String vlanPriority;
    private String idleTimeout;
    private String priority;
    private String ingressPort;
    private String tosBits;
    /**
     * 此下发的流表项 的名称
     * */
    private String name;

    private String hardTimeout;

    private String dlDst;
    private String installInHw;

    /**
     * 如非需要则不修改
     * */
    private String etherType = "0x800";

    /**
     * 流表的信息
     * */
    private List<String> actions;

    private String cookie;

    private String dlSrc;

    private String nwSrc;

    private String nwDst;

    private String tpDst;


    public String getTpSrc() {
        return tpSrc;
    }

    public void setTpSrc(String tpSrc) {
        this.tpSrc = tpSrc;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getVlanPriority() {
        return vlanPriority;
    }

    public void setVlanPriority(String vlanPriority) {
        this.vlanPriority = vlanPriority;
    }

    public String getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(String idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIngressPort() {
        return ingressPort;
    }

    public void setIngressPort(String ingressPort) {
        this.ingressPort = ingressPort;
    }

    public String getTosBits() {
        return tosBits;
    }

    public void setTosBits(String tosBits) {
        this.tosBits = tosBits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHardTimeout() {
        return hardTimeout;
    }

    public void setHardTimeout(String hardTimeout) {
        this.hardTimeout = hardTimeout;
    }

    public String getDlDst() {
        return dlDst;
    }

    public void setDlDst(String dlDst) {
        this.dlDst = dlDst;
    }

    public String getInstallInHw() {
        return installInHw;
    }

    public void setInstallInHw(String installInHw) {
        this.installInHw = installInHw;
    }

    public String getEtherType() {
        return etherType;
    }

    public void setEtherType(String etherType) {
        this.etherType = etherType;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getDlSrc() {
        return dlSrc;
    }

    public void setDlSrc(String dlSrc) {
        this.dlSrc = dlSrc;
    }

    public String getNwSrc() {
        return nwSrc;
    }

    public void setNwSrc(String nwSrc) {
        this.nwSrc = nwSrc;
    }

    public String getNwDst() {
        return nwDst;
    }

    public void setNwDst(String nwDst) {
        this.nwDst = nwDst;
    }

    public String getTpDst() {
        return tpDst;
    }

    public void setTpDst(String tpDst) {
        this.tpDst = tpDst;
    }
}

//public class Flow{
//
//    private String flow = "{\n" +
//            "\"flow\": [\n" +
//            "{\n" +
//            "\"id\": \"1\"，\n" +
//            "\"match\": {\n" +
//            "\"ethernet-match\": {\n" +
//            "\"ethernet-type\": {\n" +
//            "\"type\": \"0x0800\"\n" +
//            "}\n" +
//            "}，\n" +
//            "\"ipv4-destination\": \"10.0.0.1/24\"\n" +
//            "}，\n" +
//            "\"instructions\": {\n" +
//            "\"instruction\": [\n" +
//            "{\n" +
//            "\"apply-actions\": {\n" +
//            "\"action\": [\n" +
//            "{\n" +
//            "\"dec-nw-ttl\": {}，\n" +
//            "\"order\": \"0\"\n" +
//            "}\n" +
//            "]\n" +
//            "}，\n" +
//            "\"order\": \"0\"\n" +
//            "}\n" +
//            "]\n" +
//            "}，\n" +
//            "\"flow-name\": \"lihao\"，\n" +
//            "\"priority\": \"1\"，\n" +
//            "\"idle-timeout\": \"20\"，\n" +
//            "\"hard-timeout\": \"20\"，\n" +
//            "\"cookie\": \"1\"，\n" +
//            "\"table_id\": \"0\"\n" +
//            "}\n" +
//            "]\n" +
//            "}";
//    public String getFlow() {
//        return this.flow;
//    }
//
//    public void setFlow(String flow) {
//        this.flow = flow;
//    }
//
//}
