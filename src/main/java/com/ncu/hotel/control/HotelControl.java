package com.ncu.hotel.control;

import com.alibaba.fastjson.JSONObject;
import com.ncu.hotel.entiy.*;
import com.ncu.hotel.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ApiIgnore
@Controller
@RequestMapping("/hotel")
public class HotelControl {
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


    @GetMapping("/feeSumUp")
    public String queryH(@PageableDefault(page = 0,size = pageSize) Pageable pageable, ModelMap map) throws ParseException {

        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar yesterday = Calendar.getInstance();

        yesterday.setTime(new Date());
        yesterday.add(Calendar.DATE,-1);//设置时间为昨天

        Date yesterDay=df.parse(String.valueOf(yesterday.getTime()));

        //只查找前一天数据
        Page<Accommodation> page=accommodationJpa.findByDate(pageable ,yesterDay);
        map.addAttribute("page", page);

        return "html/hotel/fftj";
    }

    @GetMapping("/roomManagement")
    public String queryH1(@PageableDefault(page = 0,size = pageSize) Pageable pageable,ModelMap map){
        Page<RoomDetail> roomDetail=roomDetailJpa.findByRoomType(pageable,'a');
        map.addAttribute("page", roomDetail);
        setMap(map);
        return "html/hotel/index";
    }

    @GetMapping("/roomSearchByType")
    public String roomSearchByType(@RequestParam("pageNumber") int pageNumber,
                                   @RequestParam("type") char type,
                                   ModelMap map){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<RoomDetail> roomDetail=roomDetailJpa.findByRoomType(pageable,type);
        map.addAttribute("page", roomDetail);
        map.addAttribute("type", type);
        setMap(map);
        return "html/hotel/indexByType";
    }

    public void setMap(ModelMap map){
        List<RoomDetail> a=roomDetailJpa.findByRoomType('a');
        List<RoomDetail> b=roomDetailJpa.findByRoomType('b');
        List<RoomDetail> c=roomDetailJpa.findByRoomType('c');
        List<RoomDetail> d=roomDetailJpa.findByRoomType('d');
        List<RoomDetail> e=roomDetailJpa.findByRoomType('e');
        List<RoomDetail> f=roomDetailJpa.findByRoomType('f');
        List<RoomDetail> g=roomDetailJpa.findByRoomType('g');
        List<RoomDetail> h=roomDetailJpa.findByRoomType('h');
        List<RoomDetail> i=roomDetailJpa.findByRoomType('i');
        List<RoomDetail> j=roomDetailJpa.findByRoomType('j');
        List<RoomDetail> k=roomDetailJpa.findByRoomType('k');
        List<RoomDetail> l=roomDetailJpa.findByRoomType('l');
        map.addAttribute("a", a.size());
        map.addAttribute("b", b.size());
        map.addAttribute("c", c.size());
        map.addAttribute("d", d.size());
        map.addAttribute("e", e.size());
        map.addAttribute("f", f.size());
        map.addAttribute("g", g.size());
        map.addAttribute("h", h.size());
        map.addAttribute("i", i.size());
        map.addAttribute("i", j.size());
        map.addAttribute("k", k.size());
        map.addAttribute("l", l.size());
    }

    @GetMapping("/performanceScore")
    public String queryH2(@PageableDefault(page = 0,size = pageSize) Pageable pageable,ModelMap map){
        Page<Server> servers=serverJpa.findAll(pageable );
        map.addAttribute("page", servers);
        return "html/hotel/jxfz";
    }

    @GetMapping("/feedbackManagement")
    public String queryH3(@PageableDefault(page = 0,size = pageSize) Pageable pageable,ModelMap map){
        Page<Accommodation> accommodation=accommodationJpa.findAll(pageable);
        map.addAttribute("page", accommodation);
        return "html/hotel/pjgl";
    }

    @GetMapping("/checkInPeroid")
    public String queryH4(@PageableDefault(page = 0,size = pageSize) Pageable pageable,ModelMap map) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        Calendar nowDay = Calendar.getInstance();
        nowDay.setTime(new Date());

        nowDay.set(Calendar.HOUR_OF_DAY,0);//设置时
        nowDay.set(Calendar.MINUTE, 0);//这里分
        nowDay.set(Calendar.SECOND, 0);//设置秒
//        System.out.println(nowDay);
//        System.out.println(format.format(df.parse(String.valueOf(nowDay.getTime()))));

        Date query=df.parse(String.valueOf(nowDay.getTime()));
//        System.out.println("df:"+query);
        Page<Server> dayServer=serverJpa.findByCallTimeAfter(pageable,query);


        String nowTime = simpleFormat.format(new Date());
        long mowMinute = simpleFormat.parse(nowTime).getTime();

        //当天订单 更新响应时间及完成时间
        for(int i = 0;i < dayServer.getContent().size(); i ++){
            Server server2=dayServer.getContent().get(i);
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
        int all=serverJpa.findByCallTimeAfter(query).size();
        int notResponse=serverJpa.findByCallTimeAfterAndResponseStatus(query, (byte) 0).size();
        int notFinsh=serverJpa.findByCallTimeAfterAndFinishStatus(query, (byte) 0).size();
        map.addAttribute("all", all);
        map.addAttribute("notResponse", notResponse);
        map.addAttribute("notFinsh", notFinsh);

        map.addAttribute("page", dayServer);

//        System.out.println(server);
        return "html/hotel/rzqj";
    }


    @GetMapping("/rzqjClick")
    public String rzqjClick(@RequestParam("pageNumber") int pageNumber,
                            @RequestParam("status") byte status,
                            ModelMap map) throws ParseException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        Calendar nowDay = Calendar.getInstance();
        nowDay.setTime(new Date());

        nowDay.set(Calendar.HOUR_OF_DAY,0);//设置时
        nowDay.set(Calendar.MINUTE, 0);//这里分
        nowDay.set(Calendar.SECOND, 0);//设置秒
//        System.out.println(nowDay);
//        System.out.println(format.format(df.parse(String.valueOf(nowDay.getTime()))));

        Date query=df.parse(String.valueOf(nowDay.getTime()));
//        System.out.println("df:"+query);
        Page<Server> dayServer=serverJpa.findByCallTimeAfter(pageable,query);
        if (status==0){
            dayServer=serverJpa.findByCallTimeAfterAndResponseStatus(pageable,query,status);
        }else if (status==1){
            dayServer=serverJpa.findByCallTimeAfterAndFinishStatus(pageable,query, (byte) 0);
        }
        for (int i=0;i<dayServer.getContent().size();i++){
//            System.out.println("date:"+dayServer.getContent().get(i).getID());
        }

        String nowTime = simpleFormat.format(new Date());
        long mowMinute = simpleFormat.parse(nowTime).getTime();

        //当天订单 更新响应时间及完成时间
        for(int i = 0;i < dayServer.getContent().size(); i ++){
            Server server2=dayServer.getContent().get(i);
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
        int all=serverJpa.findByCallTimeAfter(query).size();
        int notResponse=serverJpa.findByCallTimeAfterAndResponseStatus(query, (byte) 0).size();
        int notFinsh=serverJpa.findByCallTimeAfterAndFinishStatus(query, (byte) 0).size();
        map.addAttribute("all", all);
        map.addAttribute("notResponse", notResponse);
        map.addAttribute("notFinsh", notFinsh);
        map.addAttribute("status", status);
        map.addAttribute("page", dayServer);

//        System.out.println(server);
        return "html/hotel/rzqjClick";
    }


    @GetMapping("/restGoods")
    public String queryH5(@PageableDefault(page = 0,size = pageSize) Pageable pageable,ModelMap map){
        Page<LoseGoods> losegoods=loseGoodsJpa.findAll(pageable);
        int all=loseGoodsJpa.findAll().size();
        int notFinsh=loseGoodsJpa.findByResult((byte) 0).size();
        int zhenYi=loseGoodsJpa.findByResult((byte) 2).size();
        map.addAttribute("all", all);
        map.addAttribute("notFinsh", notFinsh);
        map.addAttribute("zhenYi", zhenYi);
        map.addAttribute("page", losegoods);
        return "html/hotel/ylwp";
    }

    @GetMapping("/restGoodsClick")
    public String restGoodsClick(@RequestParam("pageNumber") int pageNumber,
                                 @RequestParam("result") byte result,
                                 ModelMap map){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<LoseGoods> losegoods=loseGoodsJpa.findByResult(pageable,result);
        int all=loseGoodsJpa.findAll().size();
        int notFinsh=loseGoodsJpa.findByResult((byte) 0).size();
        int zhenYi=loseGoodsJpa.findByResult((byte) 2).size();
        map.addAttribute("all", all);
        map.addAttribute("notFinsh", notFinsh);
        map.addAttribute("zhenYi", zhenYi);
        map.addAttribute("result", result);
        map.addAttribute("page", losegoods);
        return "html/hotel/ylwpClick";
    }

    @RequestMapping("/fftj_serach")
    public String fftj_serach(@RequestParam("pageNumber") int pageNumber,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("type") char type,
                              ModelMap map) throws ParseException {
//        System.out.println(startDate+" "+endDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateBegin = startDate+ " 12:10:01";
        String dateEnd = endDate+ " 12:10:01";
//        System.out.println("dateBegin: "+dateBegin);

        Date start=format.parse(dateBegin);
        Date end=format.parse(dateEnd);

        Pageable page = PageRequest.of(pageNumber, pageSize);
//        System.out.println("start: "+start);
        Page<Accommodation> accommodations=accommodationJpa.findByRoomDetailRoomTypeAndDateBetween(page,type,start,end);
        //分页查询
//        System.out.println("startDate: "+startDate);
        map.addAttribute("page", accommodations);
        map.addAttribute("startDate", (String)startDate);
        map.addAttribute("endDate", endDate);
        map.addAttribute("type", type);
        return "html/hotel/fftjSearch";
    }

    @RequestMapping("/jxfz_serach")
    public String jxfz_serach(@RequestParam("pageNumber") int pageNumber,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              ModelMap map) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateBegin = startDate+ " 12:10:01";
        String dateEnd = endDate+ " 12:10:01";
//        System.out.println("dateBegin: "+dateBegin);

        Date start=format.parse(dateBegin);
        Date end=format.parse(dateEnd);

        Pageable page = PageRequest.of(pageNumber, pageSize);
//        System.out.println("start: "+start);
        Page<Server> servers=serverJpa.findByCallTimeBetween(page,start,end);
        //分页查询
//        System.out.println("startDate: "+startDate);
        map.addAttribute("page", servers);
        map.addAttribute("startDate", startDate);
        map.addAttribute("endDate", endDate);
        return "html/hotel/jxfzSearch";

    }


}
