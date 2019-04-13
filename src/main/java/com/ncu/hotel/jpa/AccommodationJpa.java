package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Accommodation;
import com.ncu.hotel.entiy.Server;
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
public interface AccommodationJpa extends JpaRepository<Accommodation, Integer>, JpaSpecificationExecutor<Accommodation>, Serializable {

    List<Accommodation> findByRoomNumber(int roomNumber);
    Page<Accommodation> findByDate(Pageable pageable, Date date);
//    List<Accommodation> findByRoomDetailRoomTypeAndDateBetween(char type,Date start,Date end);
    Page<Accommodation> findByRoomDetailRoomTypeAndDateBetween(Pageable pageable,char type,Date start,Date end);

}
