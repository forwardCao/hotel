package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/2/26
 */
@Entity
public class Staff {
    @Id
    private int workID;

    private String cardID;
    private String department;
    private String name;

    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    Hotel hotel;

    @OneToOne(mappedBy = "staff")
    private Server server;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
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
