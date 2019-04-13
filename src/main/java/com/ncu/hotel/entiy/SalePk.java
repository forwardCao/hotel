package com.ncu.hotel.entiy;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created bt caoqianfeng on 2019/2/5
 */
public class SalePk implements Serializable {
    private char roomType;
    private int hotelID;
    @Temporal(TemporalType.DATE)
    private Date date;


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

        SalePk salePk = (SalePk) o;

        if (roomType != salePk.roomType) return false;
        if (hotelID != salePk.hotelID) return false;
        return date.equals(salePk.date);
    }

    @Override
    public int hashCode() {
        int result = (int) roomType;
        result = 31 * result + hotelID;
        result = 31 * result + date.hashCode();
        return result;
    }
}
