package com.ncu.hotel.control;

import com.ncu.hotel.entiy.*;
import com.ncu.hotel.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private MemberJpa memberJpa;
    @Autowired
    private RoomDetailJpa roomDetailJpa ;
    @Autowired
    private AccommodationJpa accommodationJpa;
    @Autowired
    private StaffJpa staffJpa;
    @Autowired
    private ServerJpa serverJpa;
    @Autowired
    private LoseGoodsJpa loseGoodsJpa;


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

    @PostMapping("/member")
    public void saveHotel(Member member){
        System.out.println("id"+member.getMemberId());
        memberJpa.save(member);
    }

    @PostMapping("/romeDetail")
    public void saveRoomDetail(RoomDetail roomDetail){
        int id = roomDetail.getHotelID();
        char type=roomDetail.getRoomType();
        Room room= roomJpa.findByHotelIDAndAndRoomType(id,type);

        if (room!=null){
            System.out.println("no Null");
            roomDetail.setRoom(room);
            roomDetailJpa.save(roomDetail);
        }else {
            System.out.println(" Null");
        }
    }

    @PostMapping("/accommodation")
    public void saveAccommodation(Accommodation accommodation){
        int memberId=accommodation.getMemberId();
        int roomNumber=accommodation.getRoomNumber();
        Optional<RoomDetail> roomDetail=roomDetailJpa.findById(roomNumber);
        Optional<Member> member=memberJpa.findById(memberId);
        accommodation.setDate(new Date());
        if (roomDetail.isPresent()&&member.isPresent()){
            accommodation.setMember(member.get());
            accommodation.setRoomDetail(roomDetail.get());
            accommodationJpa.save(accommodation);
        }else {
            System.out.println("\n数据不关联\n");
        }

    }

    @PostMapping("/server")
    public void saveServer(Server server){
        int accomId=server.getID();
        int workID=server.getWorkID();
        Optional<Accommodation> accommodation=accommodationJpa.findById(accomId);
        Optional<Staff> staff=staffJpa.findById(workID);
        if (accommodation.isPresent()){
//            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//            System.out.println(sdf.format(new Date()));
            server.setCallTime(new Date());
            server.setAccommodation(accommodation.get());
            serverJpa.save(server);
        }
        if (staff.isPresent()){
            System.out.println("staff exit\n");
            server.setStaff(staff.get());
            serverJpa.save(server);
        } else{
            System.out.println("server表插入失败");
        }
    }

    @PostMapping("/staff")
    public void saveStaff(Staff staff){
        int hotelID=staff.getHotelID();
        Optional<Hotel> hotel=hotelJpa.findById(hotelID);
        if (hotel.isPresent()){
            staff.setHotel(hotel.get());
            staffJpa.save(staff);
        }
    }

    @PostMapping("/loseGoods")
    public void saveLose(LoseGoods loseGoods ){
        int roomNumber =loseGoods.getRoomNumber();
        int memberId=loseGoods.getMemberId();
        loseGoods.setDate(new Date());
        Optional<RoomDetail> roomDetail=roomDetailJpa.findById(roomNumber);
        Optional<Member> member=memberJpa.findById(memberId);
        if (roomDetail.isPresent()&&member.isPresent()){
            loseGoods.setRoomDetail(roomDetail.get());
            loseGoods.setMember(member.get());
            loseGoodsJpa.save(loseGoods);
        }

    }

    @GetMapping("/query/{id}/{type}")//查询sale表
    public Sale query(@PathVariable("id") int id,@PathVariable("type") char type){
        return saleJpa.findByHotelIDAndAndRoomType(id,type);
    }

    @GetMapping("/queryR/{id}/{type}")//查询room 客房管理  客房管理
    public Room queryR(@PathVariable("id") int id,@PathVariable("type") char type){
        return roomJpa.findByHotelIDAndAndRoomType(id,type);
    }

    @GetMapping("/queryH/{id}")//查询hotel表  酒店管理	 核算统计
    public Optional<Hotel> queryH(@PathVariable("id") int id){
        return hotelJpa.findById(id);
    }

    @GetMapping("/queryMemberAll")//查询会员信息表   会员管理
    public List<Member> queryAllMember(){
        return memberJpa.findAll();
    }

    @GetMapping("/queryAccomm")  //入住期间 	房费统计 	评价管理
    public List<Accommodation> queryAccom(){
        return accommodationJpa.findAll();
    }

    @GetMapping("/queryServer")  //	绩效分值
    public List<Server> queryServer(){
        return serverJpa.findAll();
    }

    @GetMapping("/queryLoseGoods")  // 遗留物品
    public List<LoseGoods> queryLose(){
        return loseGoodsJpa.findAll();
    }

    @PostMapping("/delete")
    public void delete(){
        hotelJpa.deleteById(7);
    }
}
