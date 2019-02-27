package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/2/26
 */
public interface HotelJpa extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel>, Serializable {
}
