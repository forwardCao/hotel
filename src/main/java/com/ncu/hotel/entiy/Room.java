package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created bt caoqianfeng on 2019/2/5
 */
@Entity
@IdClass(RoomPk.class)
public class Room implements Serializable {
    @Id
    private char roomType;
    @Id
    private int hotelID;

    private String photo;
    private int quantity;
    private double generalPrice;
    private double generalTax;
    private double groupPrice;
    private double groupTax;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private Hotel hotel;

    @OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "room",orphanRemoval = true)
    private Set<Sale> sales=new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "room",orphanRemoval = true)
    private Set<RoomDetail> roomDetails=new HashSet<>();

    public Set<RoomDetail> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(Set<RoomDetail> roomDetails) {
        this.roomDetails = roomDetails;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(double generalPrice) {
        this.generalPrice = generalPrice;
    }

    public double getGeneralTax() {
        return generalTax;
    }

    public void setGeneralTax(double generalTax) {
        this.generalTax = generalTax;
    }

    public double getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(double groupPrice) {
        this.groupPrice = groupPrice;
    }

    public double getGroupTax() {
        return groupTax;
    }

    public void setGroupTax(double groupTax) {
        this.groupTax = groupTax;
    }
}
