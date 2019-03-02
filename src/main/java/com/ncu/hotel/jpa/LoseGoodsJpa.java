package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.LoseGoods;
import com.ncu.hotel.entiy.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created bt caoqianfeng on 2019/3/2
 */
public interface LoseGoodsJpa extends JpaRepository<LoseGoods, Date>, JpaSpecificationExecutor<LoseGoods>, Serializable {
}
