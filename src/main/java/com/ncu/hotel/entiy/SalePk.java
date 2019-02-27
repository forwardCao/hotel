package com.ncu.hotel.entiy;

import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/2/5
 */
public class SalePk implements Serializable {
    private char roomType;
    private int hotelID;

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


}
