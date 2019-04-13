package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Room;
import com.ncu.hotel.entiy.RoomPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created bt caoqianfeng on 2019/2/10
 */
public interface RoomJpa extends JpaRepository<Room, RoomPk> , JpaSpecificationExecutor<Room>,Serializable {
    Room findByHotelIDAndAndRoomType(int id,char type);
    List<Room> findByHotelID(int hotelId);
    List<Room> findByHotelIDAndDateBetween(int hotelId, Date start,Date end);
}
