package com.ncu.hotel.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created bt caoqianfeng on 2019/4/16
 */
@ApiModel(value = "搜索历史任务",description = "历史类")
public class HistoryWork {
    @ApiModelProperty(example="2019-03-27")
    private String dateBegin;
    @ApiModelProperty(example="2019-03-29")
    private String dateEnd;
    @ApiModelProperty(value="员工工号ID",example="1")
    private String workid;

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }
}
