package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Server;
import com.ncu.hotel.entiy.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/3/2
 */
public interface StaffJpa extends JpaRepository<Staff, Integer>, JpaSpecificationExecutor<Staff>, Serializable {
}
