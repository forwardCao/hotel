package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * Created bt caoqianfeng on 2019/4/17
 */
public interface OrdersJpa extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders>, Serializable {
    List<Orders> findByStatusAndMember_MemberId(byte b,int i);

}
