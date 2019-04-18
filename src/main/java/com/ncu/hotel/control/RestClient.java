package com.ncu.hotel.control;

import com.alibaba.fastjson.JSONObject;
import com.ncu.hotel.entiy.*;
import com.ncu.hotel.jpa.*;
import com.ncu.hotel.json.Mailing;
import com.ncu.hotel.json.Reservation;
import com.ncu.hotel.json.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created bt caoqianfeng on 2019/4/15
 */
@Api(tags ={"3.4 RestController"} )
@RestController
@RequestMapping("/cl")
public class RestClient {
    @Autowired
    private ServerJpa serverJpa;
    @Autowired
    private AccommodationJpa accommodationJpa;
    @Autowired
    private MemberJpa memberJpa;
    @Autowired
    private OrdersJpa ordersJpa;
    @Autowired
    private RoomJpa roomJpa;
    @Autowired
    private SaleJpa saleJpa;
    @Autowired
    private RoomDetailJpa roomDetailJpa;
    @Autowired
    private LoseGoodsJpa loseGoodsJpa;


    String sourceDate = "2019-03-20"+ " 8:00:01";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date myDate;

    {
        try {
            myDate = format.parse(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "3.4.2.1  注册",notes = "**返回值**:  \n" +
            "  *返回注册成功的会员号*  \n" +
            "***  \n"+
            "**Parameters有误，以如下格式为准**  \n" +
            "{  \n" +
            "  \"address\": \"江西省南昌市青山湖区\",  \n" +
            "  \"birthday\": \"1997-01-23\",  \n" +
            "  \"certificateType\": \"身份证\",  \n" +
            "  \"email\": \"3843987264@qq.com\",  \n" +
            "  \"name\": \"曹操\",  \n" +
            "  \"sex\": \"1\",  \n" +
            "  \"tel\": \"13189273847\"  \n" +
            "}")
    @PostMapping("/regesiter")
    public int regesiter(@RequestBody  @ApiParam(value = "json数据",required = true)Member member){
        memberJpa.save(member);
        Member member1=memberJpa.findByTel(member.getTel());

        return member1.getMemberId();
    }


    @ApiOperation(value = "3.4.2.2. 订房",notes = "**返回值为订单号主键ID，需带着此ID跳转到3.4.2.3. 添加入住人页面**  \n" +
            "* 如/client/addPerson&orderNumber=1" )
    @PostMapping("/reservationRoom")
    public int reservationRoom(@RequestBody @ApiParam(value = "json传值",required = true) Reservation reservation) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int hotelID=reservation.getHotelID();
        char roomType=reservation.getRoomType();
        int memberId=reservation.getMemberId();
        String liveDate=reservation.getLiveDate()+ " 8:00:01";
        String leaveDate=reservation.getLeaveDate()+ " 23:59:59";
        Date start=format.parse(liveDate);
        Date end=format.parse(leaveDate);
        String addServer=reservation.getAddServer();

        Room room=roomJpa.findByDateAndHotelIDAndRoomType(new Date(),hotelID,roomType);
        if (room==null){
            //更新房间今日价格
            updataRoom();
            room=roomJpa.findByDateAndHotelIDAndRoomType(new Date(),hotelID,roomType);
        }
        Optional<Member> member=memberJpa.findById(memberId);
        Orders orders=new Orders();
        orders.setAddServer(addServer);
        //设置订单时间
        orders.setOrderDate(new Date());
        //设置入住时间
        orders.setLiveDate(start);
        //设置离开时间
        orders.setLeaveDate(end);
        orders.setAddServer(addServer);
        //关联表
        orders.setRoom(room);
        orders.setMember(member.get());
        ordersJpa.save(orders);

        int ordersId=orders.getOrderNumber();

        //更新销售表Sale
        Sale sale=saleJpa.findByDateAndHotelIDAndRoomType(new Date(),hotelID,roomType);
        if (sale==null){
            updateSale();
            sale=saleJpa.findByDateAndHotelIDAndRoomType(new Date(),hotelID,roomType);
        }
        sale.setRoom(room);
        if (addServer.contains("生日")){
            sale.setBirthdayRoomSum(sale.getBirthdayRoomSum()+1);
        }else if (addServer.contains("求婚房")){
            sale.setProposeRoomSum(sale.getProposeRoomSum()+1);
        }else if (addServer.contains("婚房")){
            sale.setMarriedSum(sale.getMarriedSum()+1);
        }else {
            sale.setOtherRoomSum(sale.getOtherRoomSum()+1);
        }
        saleJpa.save(sale);

        return ordersId;
    }

    @ApiOperation(value = "3.4.2.4 登记入住人",notes = "**带参地址跳转到此页**  \n" +
            "* 如/cl/djPerson&orderNumber=1  \n" +
            "***  \n" +
            "**返回accommodation.ID 已分配房间，需带此id跳转下一页面格式如下，返回错误无房间分配**  \n" +
            "* 如/client/confirm&accomID=1  \n" +
            "***  \n" +
            "**Parameters有误，以如下格式为准**  \n" +
            "{  \n" +
            "  \"address\": \"江西省南昌市青山湖区\",  \n" +
            "  \"birthday\": \"1997-01-23\",  \n" +
            "  \"certificateType\": \"身份证\",  \n" +
            "  \"email\": \"3843987264@qq.com\",  \n" +
            "  \"name\": \"曹操\",  \n" +
            "  \"sex\": \"1\",  \n" +
            "  \"tel\": \"13189273847\"  \n" +
            "}")
    @PostMapping("/djPerson")
    public int djPerson(@RequestParam("orderNumber") int orderNumber,
                            @RequestBody  @ApiParam(value = "json数据",required = true) Member member
    ){
        memberJpa.save(member);
        Optional<Orders> orders=ordersJpa.findById(orderNumber);
        Accommodation accommodation=new Accommodation();
        accommodation.setMemberId(member.getMemberId());
        accommodation.setMember(member);
        accommodation.setOrdersId(orderNumber);
        accommodation.setLeaveDate(orders.get().getLeaveDate());
        accommodation.setLiveDate(orders.get().getLiveDate());
        char roomType=orders.get().getRoom().getRoomType();
        //按类型安排房间号
        List<RoomDetail> roomDetails=roomDetailJpa.findByRoomType(roomType);
        RoomDetail roomDetail=new RoomDetail();
        for (int i = 0; i < roomDetails.size(); i++) {
            if (roomDetails.get(i).getFlag()== (byte)0){
                roomDetail =roomDetails.get(i);
                roomDetail.setFlag((byte) 1);
                roomDetailJpa.save(roomDetail);
                break;
            }
        }
        accommodation.setRoomDetail(roomDetail);
        accommodation.setRoomNumber(roomDetail.getRoomNumber());
        accommodationJpa.save(accommodation);

        return accommodation.getID();
    }


    @ApiOperation(value = "3.4.3.3.2 客房服务",notes = "**服务类型**:  \n" +
            "* 1 客房问题  \n" +
            "* 2 工程问题  \n" +
            "* 3 餐饮问题")
    @PostMapping("/RoomService")
    public boolean ResponseRoom(@RequestBody  @ApiParam(value = "json数据",required = true) RoomService roomService){
        String callServerType = roomService.getCallServerType();
        String callServer = roomService.getCallServer();
        String accomId = roomService.getAccomId();

        Server server=new Server();
        server.setCallServerType(callServerType);
        server.setCallServer(callServer);
        server.setWorkID(0);
        //关联accom
        Optional<Accommodation> accommodation=accommodationJpa.findById(Integer.parseInt(accomId));
        server.setAccommodation(accommodation.get());
        serverJpa.save(server);
        return true;
    }

    @ApiOperation(value = "3.4.3.4.2.1. 邮寄给我",notes = "")
    @PostMapping("/mail")
    public boolean mail(@RequestBody  @ApiParam(value = "json数据",required = true) Mailing mailing){
        int loseGoodsID=mailing.getLoseGoodsID();
        Optional<LoseGoods> loseGoods=loseGoodsJpa.findById(loseGoodsID);
        BeanUtils.copyProperties(mailing,loseGoods.get());

        loseGoodsJpa.save(loseGoods.get());
        return true;
    }



    public boolean updataRoom() throws ParseException {
        List<Room> rooms=roomJpa.findByDate(myDate);
        Room room=new Room();

        for (int i=0;i<rooms.size();i++){
            BeanUtils.copyProperties(rooms.get(i),room);
            room.setDate(new Date());
            roomJpa.save(room);
        }
        return true;
    }

    public boolean updateSale(){
        List<Sale> sales=saleJpa.findByDate(myDate);
        Sale sale=new Sale();

        for (int i = 0; i < sales.size(); i++) {
           BeanUtils.copyProperties(sales.get(i),sale);
           sale.setDate(new Date());
           saleJpa.save(sale);
        }

        return true;
    }


}
