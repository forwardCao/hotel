package com.ncu.hotel.entiy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created bt caoqianfeng on 2019/2/27
 */
@Entity
public class LoseGoods {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int loseGoodsID;

    private String date;
    private String dispose;
    private String worker;
    private String disposeWorker;
    private String losePhoto;
    private byte result;
    private int roomNumber;
    private int memberId;


    @ManyToOne
    private RoomDetail roomDetail ;
    @ManyToOne
    private Member member;

    public String getLosePhoto() {
        return losePhoto;
    }

    public void setLosePhoto(String losePhoto) {
        this.losePhoto = losePhoto;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getLoseGoodsID() {
        return loseGoodsID;
    }

    public void setLoseGoodsID(int loseGoodsID) {
        this.loseGoodsID = loseGoodsID;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDispose() {
        return dispose;
    }

    public void setDispose(String dispose) {
        this.dispose = dispose;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getDisposeWorker() {
        return disposeWorker;
    }

    public void setDisposeWorker(String disposeWorker) {
        this.disposeWorker = disposeWorker;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
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
}
