package com.ncu.hotel.entiy;

import javax.persistence.*;

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

    @ManyToOne
    private Room room;

    @OneToOne(mappedBy = "roomDetail")
    private Server server;

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
