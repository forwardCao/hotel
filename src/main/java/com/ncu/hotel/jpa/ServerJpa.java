package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Server;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

/**
 * Created bt caoqianfeng on 2019/3/2
 */
public interface ServerJpa extends JpaRepository<Server, Integer>, JpaSpecificationExecutor<Server>, Serializable {
    List<Server> findByAccommodation_RoomNumber(int roomNumber);
    List<Server> findByCallTimeAfter(Date date);
    List<Server> findByCallTimeAfterAndResponseStatus(Date date,byte flag);
    Page<Server> findByCallTimeAfterAndResponseStatus(Pageable pageable,Date date,byte flag);
    List<Server> findByCallTimeAfterAndFinishStatus(Date date,byte flag);
    Page<Server> findByCallTimeAfterAndFinishStatus(Pageable pageable,Date date,byte flag);

    Page<Server> findByCallTimeAfter(Pageable pageable, Date query);
    Page<Server> findByCallTimeBetween(Pageable pageable,Date start,Date end);
}
