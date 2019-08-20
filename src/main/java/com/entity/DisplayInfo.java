package com.entity;

import java.util.Map;

/**
 * @Package: com.info.entity
 * @ClassName: displayInfo
 * @Description: TODO
 * @Author: LaoShiRen
 * @CreateDate: 2019-05-14 10:05
 * @Version: 1.0
 */
public class DisplayInfo {

    private String displayID;
    private String displayName;
    private String displayType;
    private String displayTypeName;
    private String displayTitle;
    private String displayScrollText;
    private String displayIP;
    private String displayPort;
    private String displayNote;
    private String displayStatus;
    private String displayPauseTime;
    private String displayCloseTime;
    private String districtID;
    private String districtName;
    private Map<String, Object> infoMap;

    public String getDisplayID() {
        return displayID;
    }

    public void setDisplayID(String displayID) {
        this.displayID = displayID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplayTypeName() {
        return displayTypeName;
    }

    public void setDisplayTypeName(String displayTypeName) {
        this.displayTypeName = displayTypeName;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getDisplayScrollText() {
        return displayScrollText;
    }

    public void setDisplayScrollText(String displayScrollText) {
        this.displayScrollText = displayScrollText;
    }

    public String getDisplayIP() {
        return displayIP;
    }

    public void setDisplayIP(String displayIP) {
        this.displayIP = displayIP;
    }

    public String getDisplayPort() {
        return displayPort;
    }

    public void setDisplayPort(String displayPort) {
        this.displayPort = displayPort;
    }

    public String getDisplayNote() {
        return displayNote;
    }

    public void setDisplayNote(String displayNote) {
        this.displayNote = displayNote;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getDisplayPauseTime() {
        return displayPauseTime;
    }

    public void setDisplayPauseTime(String displayPauseTime) {
        this.displayPauseTime = displayPauseTime;
    }

    public String getDisplayCloseTime() {
        return displayCloseTime;
    }

    public void setDisplayCloseTime(String displayCloseTime) {
        this.displayCloseTime = displayCloseTime;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public DisplayInfo() {

    }

    public DisplayInfo(Map<String, Object> map) {
        setDisplayID(map.get("displayID") == null ? "" : map.get("displayID").toString());
        setDisplayName(map.get("displayName") == null ? "" : map.get("displayName").toString());
        setDisplayType(map.get("displayType") == null ? "" : map.get("displayType").toString());
        setDisplayTypeName(map.get("displayTypeName") == null ? "" : map.get("displayTypeName").toString());
        setDisplayScrollText(map.get("displayScrollText") == null ? "" : map.get("displayScrollText").toString());
        setDisplayIP(map.get("displayIP") == null ? "" : map.get("displayIP").toString());
        setDisplayPort(map.get("displayPort") == null ? "" : map.get("displayPort").toString());
        setDisplayNote(map.get("displayNote") == null ? "" : map.get("displayNote").toString());
        setDisplayStatus(map.get("displayStatus") == null ? "" : map.get("displayStatus").toString());
        setDisplayPauseTime(map.get("displayPauseTime") == null ? "" : map.get("displayPauseTime").toString());
        setDisplayCloseTime(map.get("displayCloseTime") == null ? "" : map.get("displayCloseTime").toString());
        setDistrictID(map.get("districtID") == null ? "" : map.get("districtID").toString());
        setDistrictName(map.get("districtName") == null ? "" : map.get("districtName").toString());
        this.infoMap = map;
    }

    public Map<String, Object> toMap() {
        return infoMap;
    }

}
