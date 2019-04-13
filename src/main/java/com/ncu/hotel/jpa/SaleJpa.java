package com.ncu.hotel.jpa;

import com.ncu.hotel.entiy.Sale;
import com.ncu.hotel.entiy.SalePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created bt caoqianfeng on 2019/2/10
 */
public interface SaleJpa extends JpaRepository<Sale, SalePk>, JpaSpecificationExecutor<Sale>, Serializable {
    Sale findByHotelIDAndAndRoomType(int id,char type);

    @Modifying
    @Transactional
    @Query("delete from Sale u where u.hotelID = ?1 and u.roomType=?2")
    void deleteByHotelIDAndRoomType(int id,char type);


    List<Sale> findByDateBetween(Date start,Date end);
}
