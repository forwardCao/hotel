package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Member;
import com.ncu.hotel.entiy.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/3/2
 */
public interface ServerJpa extends JpaRepository<Server, Integer>, JpaSpecificationExecutor<Server>, Serializable {
}
