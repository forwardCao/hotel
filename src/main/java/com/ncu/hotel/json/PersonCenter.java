package com.ncu.hotel.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created bt caoqianfeng on 2019/4/16
 */
@ApiModel()
public class PersonCenter {
    @ApiModelProperty(value="工号ID",example="1")
    private int workID;
    @ApiModelProperty(value="1上岗 0下岗",example="1")
    private byte flag;//1上岗 0下岗

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }
}
