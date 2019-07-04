package com.entity;


public class BasicPriority {

  private long dataId;
  private long priorityId;
  private long srvGroupId;
  private long priorityLevel;
  private String priorityNote;


  public long getDataId() {
    return dataId;
  }

  public void setDataId(long dataId) {
    this.dataId = dataId;
  }


  public long getPriorityId() {
    return priorityId;
  }

  public void setPriorityId(long priorityId) {
    this.priorityId = priorityId;
  }


  public long getSrvGroupId() {
    return srvGroupId;
  }

  public void setSrvGroupId(long srvGroupId) {
    this.srvGroupId = srvGroupId;
  }


  public long getPriorityLevel() {
    return priorityLevel;
  }

  public void setPriorityLevel(long priorityLevel) {
    this.priorityLevel = priorityLevel;
  }


  public String getPriorityNote() {
    return priorityNote;
  }

  public void setPriorityNote(String priorityNote) {
    this.priorityNote = priorityNote;
  }

}
