package com.entity;

public class QueueItem {

    private String queueNo;
    private String statusTypeName;
    private String srvGroupName;
    private String regDate;

    public String getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(String queueNo) {
        this.queueNo = queueNo;
    }

    public String getStatusTypeName() {
        return statusTypeName;
    }

    public void setStatusTypeName(String statusTypeName) {
        this.statusTypeName = statusTypeName;
    }

    public String getSrvGroupName() {
        return srvGroupName;
    }

    public void setSrvGroupName(String srvGroupName) {
        this.srvGroupName = srvGroupName;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
