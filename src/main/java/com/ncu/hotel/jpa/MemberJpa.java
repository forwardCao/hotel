package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/2/27
 */
public interface MemberJpa extends JpaRepository<Member, Integer>, JpaSpecificationExecutor<Member>, Serializable {

}
