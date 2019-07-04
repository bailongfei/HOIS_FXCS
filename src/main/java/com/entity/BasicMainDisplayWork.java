package com.entity;


public class BasicMainDisplayWork {

  private long mdId;
  private java.sql.Timestamp mdLastCTime;
  private long mdStatus;
  private String note;


  public long getMdId() {
    return mdId;
  }

  public void setMdId(long mdId) {
    this.mdId = mdId;
  }


  public java.sql.Timestamp getMdLastCTime() {
    return mdLastCTime;
  }

  public void setMdLastCTime(java.sql.Timestamp mdLastCTime) {
    this.mdLastCTime = mdLastCTime;
  }


  public long getMdStatus() {
    return mdStatus;
  }

  public void setMdStatus(long mdStatus) {
    this.mdStatus = mdStatus;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
