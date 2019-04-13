package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created bt caoqianfeng on 2019/2/26
 */
@Entity
public class RoomDetail {
    @Id
    private int roomNumber;

    private byte window;
    private String windowOrientation;
    private byte wifi;
    private String feacture;
    private byte flag;
    private char roomType;
    private int hotelID;

    @ManyToOne
    private Room room;

    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "member")
    private Set<Accommodation> accommodations=new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "member",orphanRemoval = true)
    private Set<LoseGoods> loseGoods=new HashSet<>();

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
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

    public Set<LoseGoods> getLoseGoods() {
        return loseGoods;
    }

    public void setLoseGoods(Set<LoseGoods> loseGoods) {
        this.loseGoods = loseGoods;
    }

    public Set<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(Set<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public byte getWindow() {
        return window;
    }

    public void setWindow(byte window) {
        this.window = window;
    }

    public String getWindowOrientation() {
        return windowOrientation;
    }

    public void setWindowOrientation(String windowOrientation) {
        this.windowOrientation = windowOrientation;
    }

    public byte getWifi() {
        return wifi;
    }

    public void setWifi(byte wifi) {
        this.wifi = wifi;
    }

    public String getFeacture() {
        return feacture;
    }

    public void setFeacture(String feacture) {
        this.feacture = feacture;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
