package com.entity;


public class BasicDatadict {

  private long dictId;
  private String dictName;
  private String dictPara;
  private String dictValue;
  private String dictNote;
  private String dictEnable;
  private java.sql.Timestamp createDateTime;
  private java.sql.Timestamp updateDateTime;


  public long getDictId() {
    return dictId;
  }

  public void setDictId(long dictId) {
    this.dictId = dictId;
  }


  public String getDictName() {
    return dictName;
  }

  public void setDictName(String dictName) {
    this.dictName = dictName;
  }


  public String getDictPara() {
    return dictPara;
  }

  public void setDictPara(String dictPara) {
    this.dictPara = dictPara;
  }


  public String getDictValue() {
    return dictValue;
  }

  public void setDictValue(String dictValue) {
    this.dictValue = dictValue;
  }


  public String getDictNote() {
    return dictNote;
  }

  public void setDictNote(String dictNote) {
    this.dictNote = dictNote;
  }


  public String getDictEnable() {
    return dictEnable;
  }

  public void setDictEnable(String dictEnable) {
    this.dictEnable = dictEnable;
  }


  public java.sql.Timestamp getCreateDateTime() {
    return createDateTime;
  }

  public void setCreateDateTime(java.sql.Timestamp createDateTime) {
    this.createDateTime = createDateTime;
  }


  public java.sql.Timestamp getUpdateDateTime() {
    return updateDateTime;
  }

  public void setUpdateDateTime(java.sql.Timestamp updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

}
