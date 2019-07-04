package com.entity;


public class BasicEventLog {

  private long eventId;
  private long eventGroupId;
  private long eventTypeId;
  private String eventInfo;
  private java.sql.Timestamp eventTime;
  private String eventNote;


  public long getEventId() {
    return eventId;
  }

  public void setEventId(long eventId) {
    this.eventId = eventId;
  }


  public long getEventGroupId() {
    return eventGroupId;
  }

  public void setEventGroupId(long eventGroupId) {
    this.eventGroupId = eventGroupId;
  }


  public long getEventTypeId() {
    return eventTypeId;
  }

  public void setEventTypeId(long eventTypeId) {
    this.eventTypeId = eventTypeId;
  }


  public String getEventInfo() {
    return eventInfo;
  }

  public void setEventInfo(String eventInfo) {
    this.eventInfo = eventInfo;
  }


  public java.sql.Timestamp getEventTime() {
    return eventTime;
  }

  public void setEventTime(java.sql.Timestamp eventTime) {
    this.eventTime = eventTime;
  }


  public String getEventNote() {
    return eventNote;
  }

  public void setEventNote(String eventNote) {
    this.eventNote = eventNote;
  }

}
