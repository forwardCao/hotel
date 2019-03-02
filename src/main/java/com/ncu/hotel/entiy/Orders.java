package com.ncu.hotel.entiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created bt caoqianfeng on 2019/2/27
 */
@Entity
public class Orders {
    @Id
    @GeneratedValue
    private int orderNumber;

    private Date orderDate;
    private String addServer;
    private Date liveDate;
    private Date leaveDate;
    private byte bits;

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

    public byte getBits() {
        return bits;
    }

    public void setBits(byte bits) {
        this.bits = bits;
    }
}
