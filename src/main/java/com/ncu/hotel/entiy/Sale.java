package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/2/5
 */
@Entity
@IdClass(SalePk.class)
public class Sale implements Serializable {
    @Id
    private char roomType;
    @Id
    private int hotelID;

    private int generalSum;
    private int groupSum;
    private int birthdayRoomSum;
    private int proposeRoomSum;
    private int marriedSum;
    private int otherRoomSum;
    private int commission;

    @JsonIgnore
    @ManyToOne
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public char getRoomType() {
        return roomType;
    }

    public void setRoomType(char roomType) {
        this.roomType = roomType;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getGeneralSum() {
        return generalSum;
    }

    public void setGeneralSum(int generalSum) {
        this.generalSum = generalSum;
    }

    public int getGroupSum() {
        return groupSum;
    }

    public void setGroupSum(int groupSum) {
        this.groupSum = groupSum;
    }

    public int getBirthdayRoomSum() {
        return birthdayRoomSum;
    }

    public void setBirthdayRoomSum(int birthdayRoomSum) {
        this.birthdayRoomSum = birthdayRoomSum;
    }

    public int getProposeRoomSum() {
        return proposeRoomSum;
    }

    public void setProposeRoomSum(int proposeRoomSum) {
        this.proposeRoomSum = proposeRoomSum;
    }

    public int getMarriedSum() {
        return marriedSum;
    }

    public void setMarriedSum(int marriedSum) {
        this.marriedSum = marriedSum;
    }

    public int getOtherRoomSum() {
        return otherRoomSum;
    }

    public void setOtherRoomSum(int otherRoomSum) {
        this.otherRoomSum = otherRoomSum;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }
}
