package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/2/28
 */
public interface RoomDetailJpa extends JpaRepository<RoomDetail, Integer>, JpaSpecificationExecutor<RoomDetail>, Serializable {
}
