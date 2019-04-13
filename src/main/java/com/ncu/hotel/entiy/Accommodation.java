package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created bt caoqianfeng on 2019/2/27
 */
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int customerStatisfaction;
    private String customerEvaluate;
    private double damage;
    private String damagePhoto;
    private double commission;
    private double tax;
    private double addPrice;
    private int memberId;
    private int roomNumber;
    private Date evaluateDate;
    private int toCustomerStar;
    private String toCustomerEvaluate;


    @ManyToOne
    private RoomDetail roomDetail ;
    @ManyToOne
    private Member member;

    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "accommodation")
    private Set<Server> servers=new HashSet<>();

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerStatisfaction() {
        return customerStatisfaction;
    }

    public void setCustomerStatisfaction(int customerStatisfaction) {
        this.customerStatisfaction = customerStatisfaction;
    }

    public String getCustomerEvaluate() {
        return customerEvaluate;
    }

    public void setCustomerEvaluate(String customerEvaluate) {
        this.customerEvaluate = customerEvaluate;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public String getDamagePhoto() {
        return damagePhoto;
    }

    public void setDamagePhoto(String damagePhoto) {
        this.damagePhoto = damagePhoto;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public RoomDetail getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetail roomDetail) {
        this.roomDetail = roomDetail;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public double getAddPrice() { return addPrice; }

    public void setAddPrice(double addPrice) { this.addPrice = addPrice; }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public int getToCustomerStar() {
        return toCustomerStar;
    }

    public void setToCustomerStar(int toCustomerStar) {
        this.toCustomerStar = toCustomerStar;
    }

    public String getToCustomerEvaluate() {
        return toCustomerEvaluate;
    }

    public void setToCustomerEvaluate(String toCustomerEvaluate) {
        this.toCustomerEvaluate = toCustomerEvaluate;
    }
}
