package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

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
    private double birthdayPrice;
    private double proposePrice;
    private double marriedPrice;
    private double otherPrice;
    @Temporal(TemporalType.DATE)
    private Date date;

    @JsonIgnore
    @ManyToOne
    private Room room;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public double getBirthdayPrice() {
        return birthdayPrice;
    }

    public void setBirthdayPrice(double birthdayPrice) {
        this.birthdayPrice = birthdayPrice;
    }

    public double getProposePrice() {
        return proposePrice;
    }

    public void setProposePrice(double proposePrice) {
        this.proposePrice = proposePrice;
    }

    public double getMarriedPrice() {
        return marriedPrice;
    }

    public void setMarriedPrice(double marriedPrice) {
        this.marriedPrice = marriedPrice;
    }

    public double getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(double otherPrice) {
        this.otherPrice = otherPrice;
    }
}
