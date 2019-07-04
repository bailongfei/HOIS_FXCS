package com.entity;


public class SysUser {

  private long id;
  private String userName;
  private String passWord;
  private long userLevel;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }


  public long getUserLevel() {
    return userLevel;
  }

  public void setUserLevel(long userLevel) {
    this.userLevel = userLevel;
  }

}
