package com.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Package: com.info.entity
 * @ClassName: CallInfo
 * @Description: TODO
 * @Author: LaoShiRen
 * @CreateDate: 2019-05-14 14:44
 * @Version: 1.0
 */
public class CallInfo {

    private String queueID;
    private String customerID;
    private String customerName;
    private String queueNo;
    private String callMessage;

    public String getQueueID() {
        return queueID;
    }

    public void setQueueID(String queueID) {
        this.queueID = queueID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(String queueNo) {
        this.queueNo = queueNo;
    }

    public String getCallMessage() {
        return callMessage;
    }

    public void setCallMessage(String callMessage) {
        this.callMessage = callMessage;
    }

    public CallInfo() {

    }

    public CallInfo(Map<String, Object> map) {
        queueID = map.get("queueID").toString();
        customerID = map.get("customerID").toString();
        customerName = map.get("customerName").toString();
        queueNo = map.get("queueNo").toString();
        callMessage = map.get("callMessage").toString();
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("queueID", queueID);
        map.put("customerID", customerID);
        map.put("customerName", customerName);
        map.put("queueNo", queueNo);
        map.put("callMessage", callMessage);
        return map;
    }


}
