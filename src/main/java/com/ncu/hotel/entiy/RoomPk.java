package com.ncu.hotel.entiy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created bt caoqianfeng on 2019/2/5
 */
public class RoomPk implements Serializable {
    private char roomType;
    private int hotelID;
    private Date date;
    public RoomPk(){}

    public RoomPk(char roomType, int hotelID) {
        this.roomType = roomType;
        this.hotelID = hotelID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomPk roomPk = (RoomPk) o;

        if (roomType != roomPk.roomType) return false;
        if (hotelID != roomPk.hotelID) return false;
        return date.equals(roomPk.date);
    }

    @Override
    public int hashCode() {
        int result = (int) roomType;
        result = 31 * result + hotelID;
        result = 31 * result + date.hashCode();
        return result;
    }
}
