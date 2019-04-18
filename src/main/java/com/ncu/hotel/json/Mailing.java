package com.ncu.hotel.json;

import io.swagger.annotations.ApiModel;

/**
 * Created bt caoqianfeng on 2019/4/18
 */
@ApiModel(value = "遗留物品邮寄")
public class Mailing {
    private int loseGoodsID;
    private String address;
    private String name;
    private String tel;

    public int getLoseGoodsID() {
        return loseGoodsID;
    }

    public void setLoseGoodsID(int loseGoodsID) {
        this.loseGoodsID = loseGoodsID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
