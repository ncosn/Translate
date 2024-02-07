package com.sgcc.yzd.translate.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "instruction")
public class Instruction implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer _id;

    /**
     * 指令类型
     * 0:问答, 1:控制继电器, 2:控制窗帘, 3:控制空调, 4:控制区域设备
     */
    @ColumnInfo(name = "query_type")
    private Integer queryType;

    @ColumnInfo(name = "query_path")
    private String queryPath;

    @ColumnInfo(name = "key_words")
    private String keyWords;

    /**
     * 操作模式（必须）
     * 窗帘：1:打开窗帘, 2:关闭窗帘
     * 空调：1:开机, 2:关机,
     * 继电器：1:16路全开, 2:16路全关, 3:某路单开, 4:某路单关
     */
    @ColumnInfo(name = "device_type")
    private Integer deviceType;

    @ColumnInfo(name = "device_id")
    private String deviceId;

    @ColumnInfo(name = "device_area")
    private String deviceArea;

    @ColumnInfo(name = "query_reply")
    private String queryReply;

    public Instruction() {
    }

    @Ignore
    public Instruction(Integer queryType, String keyWords, Integer deviceType, String deviceId, String deviceArea, String queryReply) {
        this.queryType = queryType;
        this.keyWords = keyWords;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.deviceArea = deviceArea;
        this.queryReply = queryReply;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public String getQueryPath() {
        return queryPath;
    }

    public void setQueryPath(String queryPath) {
        this.queryPath = queryPath;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceArea() {
        return deviceArea;
    }

    public void setDeviceArea(String deviceArea) {
        this.deviceArea = deviceArea;
    }

    public String getQueryReply() {
        return queryReply;
    }

    public void setQueryReply(String queryReply) {
        this.queryReply = queryReply;
    }
}
