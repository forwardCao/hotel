package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created bt caoqianfeng on 2019/2/26
 */
@Entity
public class Server {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int callID;

    private String callServerType;
    private String callServer;

    @CreatedDate
    private Date callTime;

    private byte responseStatus;
    private int responseTime;
    private byte finishStatus;
    private int finishTime;
    private int Satisfaction;
    private String appraisal;
    private int ID;//accomId
    private int workID;

    @ManyToOne
    private Accommodation accommodation;
    @ManyToOne
    private  Staff staff;

    public int getSatisfaction() {
        return Satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        Satisfaction = satisfaction;
    }

    public String getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

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

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
