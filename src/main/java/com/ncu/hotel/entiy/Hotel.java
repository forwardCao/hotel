package com.ncu.hotel.entiy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created bt caoqianfeng on 2019/2/26
 */
@Entity
public class Hotel {
    @Id
    private int hotelID;

    private String hotelName;
    private String brand;
    private int starLevel;
    private String address;

    @OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "hotel")
    private Set<Room> rooms=new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "hotel")
    private Set<Staff> staff=new HashSet<>();

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
