package com.ncu.hotel.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created bt caoqianfeng on 2019/4/16
 */
@ApiModel()
public class ChaFang {
    @ApiModelProperty(value="上一个跳转页面 map中sever.accommodation.roomNumber",example="501")
    private int roomNumber;
    @ApiModelProperty(value="上一个跳转页面 map中sever.callID",example="1")
    private int callID;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCallID() {
        return callID;
    }

    public void setCallID(int callID) {
        this.callID = callID;
    }
}
