package com.entity;


public class BasicDepartmentInfo {

  private long dpId;
  private String dpNo;
  private String dpName;
  private long dpType;
  private long dpFatherId;
  private long dpLevelIndex;
  private String dpNote;
  private long siteId;


  public long getDpId() {
    return dpId;
  }

  public void setDpId(long dpId) {
    this.dpId = dpId;
  }


  public String getDpNo() {
    return dpNo;
  }

  public void setDpNo(String dpNo) {
    this.dpNo = dpNo;
  }


  public String getDpName() {
    return dpName;
  }

  public void setDpName(String dpName) {
    this.dpName = dpName;
  }


  public long getDpType() {
    return dpType;
  }

  public void setDpType(long dpType) {
    this.dpType = dpType;
  }


  public long getDpFatherId() {
    return dpFatherId;
  }

  public void setDpFatherId(long dpFatherId) {
    this.dpFatherId = dpFatherId;
  }


  public long getDpLevelIndex() {
    return dpLevelIndex;
  }

  public void setDpLevelIndex(long dpLevelIndex) {
    this.dpLevelIndex = dpLevelIndex;
  }


  public String getDpNote() {
    return dpNote;
  }

  public void setDpNote(String dpNote) {
    this.dpNote = dpNote;
  }


  public long getSiteId() {
    return siteId;
  }

  public void setSiteId(long siteId) {
    this.siteId = siteId;
  }

}
