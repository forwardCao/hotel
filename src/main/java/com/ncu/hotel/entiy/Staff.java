package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created bt caoqianfeng on 2019/2/26
 */
@Entity
public class Staff {
    @Id
    private int workID;
    private int hotelID;

    private String cardID;
    private String department;
    private String name;
    private byte flag;//1上岗 0下岗

    @JsonIgnore
    @ManyToOne
    Hotel hotel;


    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "staff")
    private Set<Server> servers=new HashSet<>();

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
