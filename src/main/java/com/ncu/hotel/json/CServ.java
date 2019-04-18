package com.ncu.hotel.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created bt caoqianfeng on 2019/4/16
 */
@ApiModel(value = "客服传值",description = "客服类·")
public class CServ {
    @ApiModelProperty(value="页面map中sever.callID",example="1")
    private String callID;
    @ApiModelProperty(value="员工工号ID",example="1")
    private String workid;

    public String getCallID() {
        return callID;
    }

    public void setCallID(String callID) {
        this.callID = callID;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }
}
