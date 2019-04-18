package com.ncu.hotel.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created bt caoqianfeng on 2019/4/17
 */
@ApiModel()
public class Reservation {
    private int hotelID;
    private char roomType;
    private int memberId;
    @ApiModelProperty(value="入住时间",example="2019-04-01")
    private String liveDate;
    @ApiModelProperty(value="离开时间",example="2019-04-03")
    private String leaveDate;
    @ApiModelProperty(value="增值服务",example="生日房100元")
    private String addServer;


    public String getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(String liveDate) {
        this.liveDate = liveDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getAddServer() {
        return addServer;
    }

    public void setAddServer(String addServer) {
        this.addServer = addServer;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public char getRoomType() {
        return roomType;
    }

    public void setRoomType(char roomType) {
        this.roomType = roomType;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
