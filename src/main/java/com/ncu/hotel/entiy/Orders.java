package com.ncu.hotel.entiy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created bt caoqianfeng on 2019/2/27
 */
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int orderNumber;

    private Date orderDate;
    private String addServer;
    private Date liveDate;
    private Date leaveDate;
    private byte status;  //0 未付款  1已付款  2 取消

    @ManyToOne
    private Room room;
    @ManyToOne
    private Member member;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddServer() {
        return addServer;
    }

    public void setAddServer(String addServer) {
        this.addServer = addServer;
    }

    public Date getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(Date liveDate) {
        this.liveDate = liveDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }


}
