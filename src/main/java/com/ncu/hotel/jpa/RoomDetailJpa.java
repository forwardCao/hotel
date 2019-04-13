package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.RoomDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * Created bt caoqianfeng on 2019/2/28
 */
public interface RoomDetailJpa extends JpaRepository<RoomDetail, Integer>, JpaSpecificationExecutor<RoomDetail>, Serializable {
    RoomDetail findByRoomNumber(int roomNumber);
    List<RoomDetail> findByRoomType(char type);
    Page<RoomDetail> findAll(Pageable pageable);
    Page<RoomDetail> findByRoomType(Pageable pageable,char type);
}
