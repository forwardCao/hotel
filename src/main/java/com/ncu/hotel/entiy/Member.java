package com.ncu.hotel.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created bt caoqianfeng on 2019/2/27
 */
@Entity
public class Member {
    @Id
    @GeneratedValue
    private int memberId;

    private String name;
    private String userName;
    private byte sex;
    private String birthday;
    private String address;
    private String tel;
    private String email;
    private String certificateType;
    private String certificateNumber;
    private String certificateFrontPhoto;
    private String certificateReversePhoto;
    private int credit;

    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "member")
    private Set<Accommodation> accommodations=new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "member")
    private Set<LoseGoods> loseGoods=new HashSet<>();

    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy = "member")
    private Set<Orders> orders=new HashSet<>();

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
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


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateFrontPhoto() {
        return certificateFrontPhoto;
    }

    public void setCertificateFrontPhoto(String certificateFrontPhoto) {
        this.certificateFrontPhoto = certificateFrontPhoto;
    }

    public String getCertificateReversePhoto() {
        return certificateReversePhoto;
    }

    public void setCertificateReversePhoto(String certificateReversePhoto) {
        this.certificateReversePhoto = certificateReversePhoto;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
