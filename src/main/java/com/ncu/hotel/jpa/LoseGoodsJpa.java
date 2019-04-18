package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.LoseGoods;
import com.ncu.hotel.entiy.Staff;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created bt caoqianfeng on 2019/3/2
 */
public interface LoseGoodsJpa extends JpaRepository<LoseGoods, Integer>, JpaSpecificationExecutor<LoseGoods>, Serializable {
    List<LoseGoods> findByMemberIdOrRoomNumber(int memberId, int roomNumber);
    List<LoseGoods> findByDate(String date);
    List<LoseGoods> findByResult(byte date);
    Page<LoseGoods> findByResult(Pageable pageable,byte date);
    List<LoseGoods> findByRoomNumber(int roomID);
    List<LoseGoods> findByMemberId(int i);


}
