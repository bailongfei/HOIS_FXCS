package com.entity;


public class BasicWsDisplayWork {

  private long wsId;
  private long wsDisplayId;
  private long wsDisplayType;
  private java.sql.Timestamp wsLastCTime;
  private long wsStatusType;
  private String note;


  public long getWsId() {
    return wsId;
  }

  public void setWsId(long wsId) {
    this.wsId = wsId;
  }


  public long getWsDisplayId() {
    return wsDisplayId;
  }

  public void setWsDisplayId(long wsDisplayId) {
    this.wsDisplayId = wsDisplayId;
  }


  public long getWsDisplayType() {
    return wsDisplayType;
  }

  public void setWsDisplayType(long wsDisplayType) {
    this.wsDisplayType = wsDisplayType;
  }


  public java.sql.Timestamp getWsLastCTime() {
    return wsLastCTime;
  }

  public void setWsLastCTime(java.sql.Timestamp wsLastCTime) {
    this.wsLastCTime = wsLastCTime;
  }


  public long getWsStatusType() {
    return wsStatusType;
  }

  public void setWsStatusType(long wsStatusType) {
    this.wsStatusType = wsStatusType;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
