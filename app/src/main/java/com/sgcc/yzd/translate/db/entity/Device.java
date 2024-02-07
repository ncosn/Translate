package com.sgcc.yzd.translate.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "device")
public class Device implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer _id;

    @ColumnInfo(name = "device_id")
    private Integer deviceId;

    @ColumnInfo(name = "device_code")
    private String deviceCode;

    @ColumnInfo(name = "device_no")
    private Integer deviceNo;

    @ColumnInfo(name = "device_type")
    private String deviceType;

    @ColumnInfo(name = "device_alias")
    private String deviceAlias;

    @ColumnInfo(name = "device_area")
    private String deviceArea;

    public Device() {
    }

    @Ignore
    public Device(Integer deviceId, String deviceCode, Integer deviceNo, String deviceType, String deviceAlias, String deviceArea) {
        this.deviceId = deviceId;
        this.deviceCode = deviceCode;
        this.deviceNo = deviceNo;
        this.deviceType = deviceType;
        this.deviceAlias = deviceAlias;
        this.deviceArea = deviceArea;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Integer getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Integer deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceAlias() {
        return deviceAlias;
    }

    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
    }

    public String getDeviceArea() {
        return deviceArea;
    }

    public void setDeviceArea(String deviceArea) {
        this.deviceArea = deviceArea;
    }
}
