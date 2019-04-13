package com.ncu.hotel.control;

import com.alibaba.fastjson.JSONObject;
import com.ncu.hotel.entiy.*;
import com.ncu.hotel.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final int  pageSize=3;


    @RequestMapping("/sale")
    public void saveRoom(Sale sale){
        int id = sale.getHotelID();
        char type=sale.getRoomType();
//        System.out.println("id: "+id+" "+type);
        Room room= roomJpa.findByHotelIDAndAndRoomType(id,type);
        if (room!=null){
//            System.out.println("no Null");
            sale.setRoom(room);
//            System.out.println("room"+room.getGeneralTax());
//            System.out.println("room: "+    sale.getRoom().getGeneralTax());
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
//            System.out.println("sad:"+hotel.get().getAddress());
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
//        System.out.println("id"+member.getMemberId());
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        loseGoods.setDate(format.format(new Date()));
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

    @PostMapping("/queryHS")
    public Hotel queryHS(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("name");
//        System.out.println("name: "+name);
        Hotel hotel = hotelJpa.findByHotelNameOrBrandOrAddress(name, name, name);
        return hotel;
    }

    @PostMapping("/queryMS")
    public Member queryMS(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("name");
//        System.out.println("name: "+name);
        Member member;
        try{
            Integer.parseInt(name);
            member= memberJpa.findByMemberIdOrCertificateNumberOrTel(Integer.parseInt(name) , name, name);
        }catch(NumberFormatException e){
            member= memberJpa.findByCertificateNumberOrTel(name, name);
        }

        return member;
    }

    @PostMapping("/setMemberFlag")
    public boolean setMemberFlag(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("memberId");
        int memberID=Integer.parseInt(name);
        String temp = (String) object.get("flag");
        byte flag= (byte) Integer.parseInt(temp);

        Optional<Member> member=memberJpa.findById(memberID);
        member.get().setFlag(flag);
        memberJpa.save(member.get());
        return true;
    }

    @PostMapping("/setRoom")
    public boolean setRoom(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("roomID");
        int roomID=Integer.parseInt(name);
        String temp = (String) object.get("flag");
        byte flag= (byte) Integer.parseInt(temp);

        Optional<RoomDetail> room=roomDetailJpa.findById(roomID);
        room.get().setFlag(flag);
        roomDetailJpa.save(room.get());
        return true;
    }

    @PostMapping("/delRoom")
    public void delRoom(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("roomID");
        int roomID=Integer.parseInt(name);
//        System.out.println("ID:"+roomID);

        //delete accom
        List<Accommodation> accommodations=accommodationJpa.findByRoomNumber(roomID);
        for (int i = 0; i < accommodations.size(); i++) {
            accommodationJpa.delete(accommodations.get(i));
        }

        //delete losegoods
        List<LoseGoods> loseGoods =loseGoodsJpa.findByRoomNumber(roomID);
        for (int i = 0; i < loseGoods.size(); i++) {
            loseGoodsJpa.delete(loseGoods.get(i));
        }


        RoomDetail roomDetail=roomDetailJpa.findByRoomNumber(roomID);
        roomDetailJpa.delete(roomDetail);
    }

    @PostMapping("/toCustomerEvaluate")
    public boolean toCustomerEvaluate(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("accomID");
//        System.out.println("name ："+name);
        String evaluate = (String) object.get("toCustomerEvaluate");
        int accomID=Integer.parseInt(name);
        Optional<Accommodation> accommodation=accommodationJpa.findById(accomID);
        accommodation.get().setToCustomerEvaluate(evaluate);
        accommodationJpa.save(accommodation.get());
        return true;
    }

    @PostMapping("/delCustomerEvaluate")
    public boolean delCustomerEvaluate(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("accomID");
        int accomID=Integer.parseInt(name);
        Optional<Accommodation> accommodation=accommodationJpa.findById(accomID);
        accommodation.get().setToCustomerEvaluate(" ");
        accommodation.get().setCustomerEvaluate(" ");
        accommodation.get().setCustomerStatisfaction(0);
        accommodation.get().setToCustomerStar(0);
        accommodationJpa.save(accommodation.get());
        return true;
    }


    @PostMapping("/queryRS")
    public  RoomDetail queryRS(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
        String name = (String) object.get("name");
        RoomDetail roomDetail = roomDetailJpa.findByRoomNumber(Integer.parseInt(name));
        return roomDetail;
    }
    @PostMapping("/queryRSType")
    public  List<RoomDetail> queryRSType(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
        String name = (String) object.get("name");
        char type=name.charAt(0);
//        System.out.println("char"+type);
        List<RoomDetail> roomDetail = roomDetailJpa.findByRoomType(type);
        return roomDetail;
    }
    @PostMapping("/queryRS1")
    public  List<Server> queryRS1(@RequestBody String json) {
        JSONObject object = JSONObject.parseObject(json);
        String name = (String) object.get("name");
        List<Server> server = serverJpa.findByAccommodation_RoomNumber(Integer.parseInt(name));
        return server;
    }

    //核算统计按日期查找
    @PostMapping("/queryHstjBydate")
    public List<Room> queryHstjBydate(@RequestBody String json) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
//        System.out.println("json:"+object);

        String dateBegin = (String) object.get("dateBegin")+ " 12:10:01";
        String dateEnd = (String) object.get("dateEnd")+ " 12:10:01";
//        System.out.println("dateBegin: "+dateBegin);
        String hotelId= (String) object.get("hotelId");
        Date start=format.parse(dateBegin);
        Date end=format.parse(dateEnd);

//        List<Sale> sales=saleJpa.findByDateBetween(start,end);
        List<Room> rooms=roomJpa.findByHotelIDAndDateBetween(Integer.parseInt(hotelId),start,end);
        return rooms;

    }


    @PostMapping("/queryLGS")
    public List<LoseGoods> queryLGS(@RequestBody String json) throws ParseException {
        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("name");

        List<LoseGoods>  loseGoods;

        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()){
            loseGoods = loseGoodsJpa.findByMemberIdOrRoomNumber(Integer.parseInt(name),Integer.parseInt(name));

        }else {
            loseGoods = loseGoodsJpa.findByDate(name);

        }

//        System.out.println("lose:"+loseGoods);
        return loseGoods;
    }

    @PostMapping("/queryLoseFinish")
    public List<LoseGoods> queryLoseFinish(@RequestBody String json){
        JSONObject object = JSONObject.parseObject(json);
        String name = (String) object.get("name");
        List<LoseGoods> loseGoods;
        //未完成
        if (name.equals("b")){
//            System.out.println("b");
            loseGoods=loseGoodsJpa.findByResult((byte) 0);
        }else if (name.equals("a")){
            loseGoods=loseGoodsJpa.findAll();
        }else {
            loseGoods=loseGoodsJpa.findByResult((byte) 2);
        }
        return loseGoods;
    }


    @PostMapping("/queryFangFei")
    public Page<Accommodation> queryFangFei(@PageableDefault(page = 0,size = pageSize) Pageable pageable,@RequestBody String json) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
//        System.out.println("json:"+object);
        String roomType = (String) object.get("roomType");
        char type=roomType.charAt(0);
        String dateBegin = (String) object.get("dateBegin")+ " 12:10:01";
        String dateEnd = (String) object.get("dateEnd")+ " 12:10:01";
//        System.out.println("dateBegin: "+dateBegin);

        Date start=format.parse(dateBegin);
        Date end=format.parse(dateEnd);

//        System.out.println("start: "+start);
        Page<Accommodation> accommodations=accommodationJpa.findByRoomDetailRoomTypeAndDateBetween(pageable,type,start,end);
        //分页查询
//        Page<Accommodation> accommodations=accommodationJpa.findByRoomDetailRoomTypeAndDateBetween(pageable,type,start,end);

//        System.out.println("size"+accommodations.size());
        return accommodations;
    }

    @PostMapping("/queryServerWYD")
    public List<Server> queryServerWYD(@RequestBody String json) throws ParseException {

        JSONObject object = JSONObject.parseObject(json);
//        System.out.println("json:"+object);
        String name = (String) object.get("name");

        List<Server> dayServer;

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        Calendar nowDay = Calendar.getInstance();
        nowDay.setTime(new Date());

        nowDay.set(Calendar.HOUR_OF_DAY,0);//设置时
        nowDay.set(Calendar.MINUTE, 0);//这里分
        nowDay.set(Calendar.SECOND, 0);//设置秒
//        System.out.println(nowDay);
        System.out.println(format.format(df.parse(String.valueOf(nowDay.getTime()))));

        Date query=df.parse(String.valueOf(nowDay.getTime()));
//        System.out.println("df:"+query);


        if (Integer.parseInt(name)==0){
//            System.out.println("weiyingda");
            dayServer=serverJpa.findByCallTimeAfterAndResponseStatus(query, (byte)0);
        }else if (Integer.parseInt(name)==1){
//            System.out.println("yiwancheng");
            dayServer=serverJpa.findByCallTimeAfterAndFinishStatus(query,(byte)1);
        }else {
            dayServer=serverJpa.findByCallTimeAfter(query);
        }


        String nowTime = simpleFormat.format(new Date());
        long mowMinute = simpleFormat.parse(nowTime).getTime();

        //当天订单 更新响应时间及完成时间
        for(int i = 0;i < dayServer.size(); i ++){
            Server server2=dayServer.get(i);
            String callTime = simpleFormat.format(server2.getCallTime());
            long from = simpleFormat.parse(callTime).getTime();

            if (server2.getResponseStatus()==0){
                int minutes = (int) ((mowMinute - from)/(1000 * 60));
                server2.setResponseTime(minutes);
                serverJpa.save(server2);
//                System.out.println("jiange:"+minutes);
//                System.out.println(server2.getCallTime());
            }
            if (server2.getFinishStatus()==0){
                int minutes = (int) ((mowMinute - from)/(1000 * 60))-server2.getResponseTime();
                server2.setFinishTime(minutes);
                serverJpa.save(server2);
            }
        }
        return dayServer;
    }

//    public static Date strToDate(String strDate) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        ParsePosition pos = new ParsePosition(0);
//        Date strode = formatter.parse(strDate,pos);
//        System.out.println("date\n"+strode);
//        System.out.println("date\n"+formatter.parse(strDate));
//        return strode;
//        }
}
