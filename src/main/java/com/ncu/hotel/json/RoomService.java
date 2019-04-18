package com.ncu.hotel.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created bt caoqianfeng on 2019/4/16
 */
@ApiModel()
public class RoomService {
    @ApiModelProperty(value="服务类型",example="1")
    private String callServerType;
    @ApiModelProperty(value="呼叫服务",example="打扫房间")
    private String callServer;
    @ApiModelProperty(value="页面map中accommodation.ID",example="1")
    private String accomId;

    public String getCallServerType() {
        return callServerType;
    }

    public void setCallServerType(String callServerType) {
        this.callServerType = callServerType;
    }

    public String getCallServer() {
        return callServer;
    }

    public void setCallServer(String callServer) {
        this.callServer = callServer;
    }

    public String getAccomId() {
        return accomId;
    }

    public void setAccomId(String accomId) {
        this.accomId = accomId;
    }
}
