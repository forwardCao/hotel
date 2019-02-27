package com.ncu.hotel.control;

import com.ncu.hotel.entiy.Hotel;
import com.ncu.hotel.entiy.Room;
import com.ncu.hotel.entiy.Sale;
import com.ncu.hotel.jpa.HotelJpa;
import com.ncu.hotel.jpa.RoomJpa;
import com.ncu.hotel.jpa.SaleJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created bt caoqianfeng on 2019/2/10
 */
@RestController
@RequestMapping("/test")
public class RestControl {
    @Autowired
    private SaleJpa saleJpa;
    @Autowired
    private RoomJpa roomJpa;
    @Autowired
    private HotelJpa hotelJpa;

    @RequestMapping("/sale")
    public void saveRoom(Sale sale){
        int id = sale.getHotelID();
        char type=sale.getRoomType();
        System.out.println("id: "+id+" "+type);
        Room room= roomJpa.findByHotelIDAndAndRoomType(id,type);
        if (room!=null){
            System.out.println("no Null");
            sale.setRoom(room);
            System.out.println("room"+room.getGeneralTax());
            System.out.println("room: "+sale.getRoom().getGeneralTax());
            saleJpa.save(sale);
        }else {
            System.out.println(" Null");
        }
//        return saleJpa.findByHotelIDAndAndRoomType(id,type);

    }
    @PostMapping("/room")
    public void saveRoom(Room room){
        int id =room.getHotelID();
        Optional<Hotel> hotel=hotelJpa.findById(id);
        if (hotel.isPresent()){
            System.out.println("sad:"+hotel.get().getAddress());
            room.setHotel(hotel.get());
            roomJpa.save(room);
        }else {
            System.out.println("null");
        }
    }

    @PostMapping("/hotel")
    public void saveHotel(Hotel hotel){
        hotelJpa.save(hotel);

    }
    @PostMapping("/query")
    public Sale query(){
        return saleJpa.findByHotelIDAndAndRoomType(7,'b');
    }
    @PostMapping("/queryR")
    public Room queryR(){
        return roomJpa.findByHotelIDAndAndRoomType(7,'b');
    }
    @PostMapping("/queryH")
    public Optional<Hotel> queryH(){
        return hotelJpa.findById(7);
    }

    @PostMapping("/delete")
    public void delete(){
        saleJpa.deleteByHotelIDAndRoomType(7,'b');
    }
}
