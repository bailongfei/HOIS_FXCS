package com.entity;


public class BasicControlCmd {

  private long controlCmdId;
  private java.sql.Timestamp controlCmdTime;
  private long cmdGroupId;
  private long cmdTypeId;
  private String cmdInfo;
  private String cmdPara1;
  private String cmdPara2;
  private String cmdPara3;
  private String cmdPara4;
  private long cmdStatus;


  public long getControlCmdId() {
    return controlCmdId;
  }

  public void setControlCmdId(long controlCmdId) {
    this.controlCmdId = controlCmdId;
  }


  public java.sql.Timestamp getControlCmdTime() {
    return controlCmdTime;
  }

  public void setControlCmdTime(java.sql.Timestamp controlCmdTime) {
    this.controlCmdTime = controlCmdTime;
  }


  public long getCmdGroupId() {
    return cmdGroupId;
  }

  public void setCmdGroupId(long cmdGroupId) {
    this.cmdGroupId = cmdGroupId;
  }


  public long getCmdTypeId() {
    return cmdTypeId;
  }

  public void setCmdTypeId(long cmdTypeId) {
    this.cmdTypeId = cmdTypeId;
  }


  public String getCmdInfo() {
    return cmdInfo;
  }

  public void setCmdInfo(String cmdInfo) {
    this.cmdInfo = cmdInfo;
  }


  public String getCmdPara1() {
    return cmdPara1;
  }

  public void setCmdPara1(String cmdPara1) {
    this.cmdPara1 = cmdPara1;
  }


  public String getCmdPara2() {
    return cmdPara2;
  }

  public void setCmdPara2(String cmdPara2) {
    this.cmdPara2 = cmdPara2;
  }


  public String getCmdPara3() {
    return cmdPara3;
  }

  public void setCmdPara3(String cmdPara3) {
    this.cmdPara3 = cmdPara3;
  }


  public String getCmdPara4() {
    return cmdPara4;
  }

  public void setCmdPara4(String cmdPara4) {
    this.cmdPara4 = cmdPara4;
  }


  public long getCmdStatus() {
    return cmdStatus;
  }

  public void setCmdStatus(long cmdStatus) {
    this.cmdStatus = cmdStatus;
  }

}
