package com.entity;

import java.util.Map;

/**
 * @Package: com.info.entity
 * @ClassName: QueueInfo
 * @Description: TODO
 * @Author: LaoShiRen
 * @CreateDate: 2019-05-14 12:10
 * @Version: 1.0
 */
public class QueueInfo implements Comparable<QueueInfo> {

    private String queue_No;
    private String regDate;
    private String srvGroup_Name;
    private String statusTypeName;
    private String queueID;
    private String customerID;
    private String customerNo;
    private String customerName;
    private String customerType;
    private String customerTypeName;
    private String queueNo;
    private String queuePos;
    private String queuePriority;
    private String queuePriorityName;
    private String queueStatus;
    private String queueStatusDesc;
    private String itemID;
    private String itemName;
    private String wsID;
    private String wsNo;
    private String wsName;
    private String staffID;
    private String staffCode;
    private String staffName;
    private String queueNote;
    private Map<String, Object> infoMap;


    public QueueInfo(String queue_No, String regDate, String srvGroup_Name, String statusTypeName) {
        this.queue_No = queue_No;
        this.regDate = regDate;
        this.srvGroup_Name = srvGroup_Name;
        this.statusTypeName = statusTypeName;
    }

    public String getQueue_No() {
        return queue_No;
    }

    public void setQueue_No(String queue_No) {
        this.queue_No = queue_No;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getSrvGroup_Name() {
        return srvGroup_Name;
    }

    public void setSrvGroup_Name(String srvGroup_Name) {
        this.srvGroup_Name = srvGroup_Name;
    }

    public String getStatusTypeName() {
        return statusTypeName;
    }

    public void setStatusTypeName(String statusTypeName) {
        this.statusTypeName = statusTypeName;
    }

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

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(String queueNo) {
        this.queueNo = queueNo;
    }

    public String getQueuePos() {
        return queuePos;
    }

    public void setQueuePos(String queuePos) {
        this.queuePos = queuePos;
    }

    public String getQueuePriority() {
        return queuePriority;
    }

    public void setQueuePriority(String queuePriority) {
        this.queuePriority = queuePriority;
    }

    public String getQueuePriorityName() {
        return queuePriorityName;
    }

    public void setQueuePriorityName(String queuePriorityName) {
        this.queuePriorityName = queuePriorityName;
    }

    public String getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(String queueStatus) {
        this.queueStatus = queueStatus;
    }

    public String getQueueStatusDesc() {
        return queueStatusDesc;
    }

    public void setQueueStatusDesc(String queueStatusDesc) {
        this.queueStatusDesc = queueStatusDesc;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getWsID() {
        return wsID;
    }

    public void setWsID(String wsID) {
        this.wsID = wsID;
    }

    public String getWsNo() {
        return wsNo;
    }

    public void setWsNo(String wsNo) {
        this.wsNo = wsNo;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getQueueNote() {
        return queueNote;
    }

    public void setQueueNote(String queueNote) {
        this.queueNote = queueNote;
    }

    public QueueInfo() {

    }

    public QueueInfo(Map<String, Object> map) {
        queueID = map.get("queueID") == null ? "" : map.get("queueID").toString();
        customerID = map.get("customerID") == null ? "" : map.get("customerID").toString();
        customerNo = map.get("customerNo") == null ? "" : map.get("customerNo").toString();
        customerName = map.get("customerName") == null ? "" : map.get("customerName").toString();
        customerType = map.get("customerType") == null ? "" : map.get("customerType").toString();
        customerTypeName = map.get("customerTypeName") == null ? "" : map.get("customerTypeName").toString();
        queueNo = map.get("queueNo") == null ? "" : map.get("queueNo").toString();
        queuePos = map.get("queuePos") == null ? "" : map.get("queuePos").toString();
        queuePriority = map.get("queuePriority") == null ? "" : map.get("queuePriority").toString();
        queuePriorityName = map.get("queuePriorityName") == null ? "" : map.get("queuePriorityName").toString();
        queueStatus = map.get("queueStatus") == null ? "" : map.get("queueStatus").toString();
        queueStatusDesc = map.get("queueStatusDesc") == null ? "" : map.get("queueStatusDesc").toString();
        itemID = map.get("itemID") == null ? "" : map.get("itemID").toString();
        itemName = map.get("itemName") == null ? "" : map.get("itemName").toString();
        wsID = map.get("wsID") == null ? "" : map.get("wsID").toString();
        wsNo = map.get("wsNo") == null ? "" : map.get("wsNo").toString();
        wsName = map.get("wsName") == null ? "" : map.get("wsName").toString();
        staffID = map.get("staffID") == null ? "" : map.get("staffID").toString();
        staffCode = map.get("staffCode") == null ? "" : map.get("staffCode").toString();
        staffName = map.get("staffName") == null ? "" : map.get("staffName").toString();
        queueNote = map.get("queueNote") == null ? "" : map.get("queueNote").toString();
        this.infoMap = map;
    }

    public Map<String, Object> toMap() {
        return infoMap;
    }


    @Override
    public String toString() {
        return getQueueNo() + " " + getCustomerName();
    }

    @Override
    public int compareTo(QueueInfo o) {
        // 比较queuePos 一致 的话 比较排队号
        int queuePos1 = Integer.parseInt(queuePos);
        int queuePos2 = Integer.parseInt(o.queuePos);
        if ((queuePos1 - queuePos2) == 0) {
            int len3 = this.queueNo.toCharArray().length;
            int len4 = o.queueNo.toCharArray().length;
            int limit = Math.min(len3, len4);
            char[] v3 = this.queueNo.toCharArray();
            char[] v4 = o.queueNo.toCharArray();
            int j = 0;
            while (j < limit) {
                char c1 = v3[j];
                char c2 = v4[j];
                if (c1 != c2) {
                    return c1 - c2;
                }
                j++;
            }
            return len3 - len4;
        } else {
            return queuePos1 - queuePos2;
        }
    }
}
