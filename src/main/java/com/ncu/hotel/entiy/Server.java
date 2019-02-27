package com.ncu.hotel.entiy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Created bt caoqianfeng on 2019/2/26
 */
@Entity
public class Server {
    @Id
    private int callID;

    private String callServerType;
    private String callServer;
    private Date callTime;
    private byte responseStatus;
    private int responseTime;
    private byte finishStatus;
    private int finishTime;

    @OneToOne
    private RoomDetail roomDetail;
    @OneToOne
    private  Staff staff;

    public int getCallID() {
        return callID;
    }

    public void setCallID(int callID) {
        this.callID = callID;
    }

    public String getCallServerType() {
        return callServerType;
    }

    public void setCallServerType(String callServerType) {
        this.callServerType = callServerType;
    }

    public String getCallServer() {
        return callServer;
    }

    public void setCallServer(String callServer) {
        this.callServer = callServer;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public byte getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(byte responseStatus) {
        this.responseStatus = responseStatus;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public byte getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(byte finishStatus) {
        this.finishStatus = finishStatus;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public RoomDetail getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetail roomDetail) {
        this.roomDetail = roomDetail;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
