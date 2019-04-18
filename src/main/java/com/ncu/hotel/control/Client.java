package com.ncu.hotel.control;

import com.ncu.hotel.entiy.*;
import com.ncu.hotel.jpa.*;
import com.ncu.hotel.json.Reservation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

/**
 * Created bt caoqianfeng on 2019/4/15
 */
@Api(tags ={"3.4 Controller"} )
@Controller
@RequestMapping("/client")
public class Client {
    @Autowired
    private MemberJpa memberJpa;
    @Autowired
    private AccommodationJpa accommodationJpa;
    @Autowired
    private HotelJpa hotelJpa;
    @Autowired
    private OrdersJpa ordersJpa;
    @Autowired
    private LoseGoodsJpa loseGoodsJpa;
    @Autowired
    private StaffJpa staffJpa;


    @ApiOperation(value = "3.4.1 首页",notes = "" )
    @GetMapping("/index")
    public String index(){
        return "";
    }

    @ApiOperation(value = "3.4.2 酒店预订",notes = "页面**map**含有:  \n" +
            "* **hotels**：类型 List<Hotel>(酒店表)  \n" +
            "* **member**：  登录会员" )
    @GetMapping("/reservation")
    public String reservation(ModelMap map){
        List<Hotel> hotels=hotelJpa.findAll();
        map.addAttribute("hotels", hotels);
        //搜索会员
        Optional<Member> member=memberJpa.findById(1);
        map.addAttribute("member",member.get());
        return "";
    }

    @ApiOperation(value = "3.4.2.1  注册",notes = "" )
    @GetMapping("/regesiter")
    public String regesiter(){
        return "";
    }

    @ApiOperation(value = "3.4.2.2. 订房",notes = "页面**map**含有:  \n" +
            "**reservation**：传来的值  \n" +
            "***  \n" +
            "**Parammeters值如下**  \n" +
            "{  \n" +
            "  \"hotelID\": 666,  \n" +
            "  \"memberId\": 1,  \n" +
            "  \"roomType\": \"a\"  \n" +
            "}" )
    @GetMapping("/reservationRoom")
    public String reservationRoom(@RequestBody @ApiParam(value = "json传值",required = true)Reservation reservation, ModelMap map){
        map.addAttribute("reservation", reservation);
        return "";
    }

    @ApiOperation(value = "3.4.2.3 添加入住人",notes = "**带参地址跳转**  \n" +
            "* 如/client/addPerson&orderNumber=1  \n" +
            "***  \n" +
            "页面map中含有orderNumber，以便把此参数带到下一个页面" )
    @GetMapping("/addPerson")
    public String addPerson(@RequestParam("orderNumber") int orderNumber,ModelMap map){
        map.addAttribute("orderNumber",orderNumber);
        return "";
    }

    @ApiOperation(value = "3.4.2.4. 登记入住人",notes = "**带参地址跳转**  \n" +
            "* 如/client/djPerson&orderNumber=1  \n" +
            "***  \n" +
            "页面map中含有orderNumber，以便把此参数带到下一个页面" )
    @GetMapping("/djPerson")
    public String djPerson(@RequestParam("orderNumber") int orderNumber,ModelMap map){
        map.addAttribute("orderNumber",orderNumber);

        return "";
    }

    @ApiOperation(value = "3.4.2.5. 确认入住人信息",notes = "**本页面带参地址跳转**  \n" +
            "* 如/client/confirm&accomID=1  \n" +
            "***  \n" +
            "**需带accommodation.OrdersId参数跳转到3.4.2.6 支付**  \n" +
            "*如/client/pay&OrdersId=1" )
    @GetMapping("/confirm")
    public String confirm(@RequestParam("accomID") int accomID,ModelMap map){
        Optional<Accommodation> accommodation=accommodationJpa.findById(accomID);
        map.addAttribute("accommodation",accommodation.get());
        return "";
    }

    @ApiOperation(value = "3.4.2.6 支付",notes = "**带参地址跳转至本页面**  \n" +
            "**如/client/pay&OrdersId=1  \n" +
            "***  \n" +
            "页面map中含有orders" )
    @GetMapping("/pay")
    public String pay(@RequestParam("OrdersId") int OrdersId,ModelMap map){
        Optional<Orders> orders=ordersJpa.findById(OrdersId);
        map.addAttribute("orders",orders.get());
        return "";
    }


    @ApiOperation(value = "3.4.3 我的订单",notes = "**0 未付款  1已付款  2 取消**" )
    @GetMapping("/orders")
    public String orders(ModelMap map){
        return "";
    }

    @ApiOperation(value = "3.4.3.1 未付款",notes = "" )
    @GetMapping("/noPay")
    public String noPay(@RequestParam("memberId") int memberId,
                        ModelMap map){
        List<Orders> orders=ordersJpa.findByStatusAndMember_MemberId((byte) 0,memberId);
        map.addAttribute("orders",orders);

        return "";
    }

    @ApiOperation(value = "3.4.3.2. 取消订单",notes = "" )
    @GetMapping("/cancel")
    public String cancel(@RequestParam("memberId") int memberId,
                        ModelMap map){
        List<Orders> orders=ordersJpa.findByStatusAndMember_MemberId((byte) 2,memberId);
        map.addAttribute("orders",orders);

        return "";
    }

    @ApiOperation(value = "3.4.3.3. 正在进行",notes = "" )
    @GetMapping("/running")
    public String running(@RequestParam("memberId") int memberId,
                         ModelMap map){
        //查找当前会员下入住订单
        List<Accommodation> accommodations=accommodationJpa.findByMemberIdOrderByIDDesc(memberId);
        //正在进行
        Accommodation accommodation=accommodations.get(0);
        map.addAttribute("accommodation", accommodation);

        return "";
    }

    @ApiOperation(value = "3.4.3.4. 已完成",notes = "" )
    @GetMapping("/finshOrder")
    public String finshOrder(@RequestParam("memberId") int memberId,
                          ModelMap map){
        List<Orders> orders=ordersJpa.findByStatusAndMember_MemberId((byte) 1,memberId);
        map.addAttribute("orders",orders);

        return "";
    }

    @ApiOperation(value = "3.4.3.3.1. 设施预启",notes = "" )
    @GetMapping("/preOpening")
    public String preOpening(@RequestParam("memberId") int memberId,
                          ModelMap map){


        return "";
    }


    @ApiOperation(value = "3.4.3.3.2 客房服务",notes = "页面**map**含有:  \n" +
            "**accommodation**：当前用户入住表  \n" )
    @GetMapping("/customerRoom")
    public String customerRoom(ModelMap map){
        //查找当前会员下入住订单
        List<Accommodation> accommodations=accommodationJpa.findByMemberIdOrderByIDDesc(1);
        //当前订单
        Accommodation accommodation=accommodations.get(0);
//        System.out.println(accommodation.getID());
        map.addAttribute("accommodation", accommodation);
        return "";
    }

    @ApiOperation(value = "3.4.3.3.3.1 点餐 ",notes = "**支付及确认 链接同3.4.3.3.2 客房服务**  \n" +
            "*传送参数也相同"
             )
    @GetMapping("/eat")
    public String eat(@RequestParam("memberId") int memberId,
                             ModelMap map){
        //查找当前会员下入住订单
        List<Accommodation> accommodations=accommodationJpa.findByMemberIdOrderByIDDesc(memberId);
        //当前订单
        Accommodation accommodation=accommodations.get(0);
//        System.out.println(accommodation.getID());
        map.addAttribute("accommodation", accommodation);

        return "";
    }

    @ApiOperation(value = "3.4.3.3.3.2 预订餐厅 ",notes = "**支付及确认 链接同3.4.3.3.2 客房服务**  \n" +
            "*传送参数也相同"
    )
    @GetMapping("/bookRestaurant")
    public String bookRestaurant(@RequestParam("memberId") int memberId,
                                 ModelMap map){
        //查找当前会员下入住订单
        List<Accommodation> accommodations=accommodationJpa.findByMemberIdOrderByIDDesc(memberId);
        //当前订单
        Accommodation accommodation=accommodations.get(0);
//        System.out.println(accommodation.getID());
        map.addAttribute("accommodation", accommodation);

        return "";
    }


    @ApiOperation(value = "3.4.3.3.4. 房间清扫 ",notes = "**支付及确认 链接同3.4.3.3.2 客房服务**  \n" +
            "*传送参数也相同"
    )
    @GetMapping("/clean")
    public String clean(@RequestParam("memberId") int memberId,
                      ModelMap map){
        //查找当前会员下入住订单
        List<Accommodation> accommodations=accommodationJpa.findByMemberIdOrderByIDDesc(memberId);
        //当前订单
        Accommodation accommodation=accommodations.get(0);
//        System.out.println(accommodation.getID());
        map.addAttribute("accommodation", accommodation);

        return "";
    }

    @ApiOperation(value = "3.4.3.3.5. 续住",notes = "" )
    @GetMapping("/continueLive")
    public String continueLive(@RequestParam("memberId") int memberId,
                             ModelMap map){


        return "";
    }

    @ApiOperation(value = "3.4.3.3.6. 退房",notes = "" )
    @GetMapping("/checkOut")
    public String checkOut(@RequestParam("memberId") int memberId,
                               ModelMap map){
        //查找当前会员下入住订单
        List<Accommodation> accommodations=accommodationJpa.findByMemberIdOrderByIDDesc(memberId);
        //当前订单
        Accommodation accommodation=accommodations.get(0);
//        System.out.println(accommodation.getID());
        map.addAttribute("accommodation", accommodation);

        return "";
    }


    @ApiOperation(value = "3.4.3.4.1. 开具发票",notes = "" )
    @GetMapping("/Billing")
    public String Billing(@RequestParam("memberId") int memberId,
                           ModelMap map){


        return "";
    }

    @ApiOperation(value = "3.4.3.4.2. 遗留物品",notes = "**map中含有loseGoods  **  \n" )
    @GetMapping("/loseGoods")
    public String loseGoods(@RequestParam("memberId") int memberId,
                          ModelMap map){
        List<LoseGoods> loseGoods=loseGoodsJpa.findByMemberId(memberId);
        map.addAttribute("loseGoods",loseGoods);
        return "";
    }

    @ApiOperation(value = "3.4.3.4.2.1. 邮寄给我",notes = "**map中含有loseGoodsID 以便点击顺丰到付确认将此参数传给后台**" )
    @GetMapping("/Mailing")
    public String Mailing(@RequestParam("loseGoodsID") int loseGoodsID,
                            ModelMap map){
        map.addAttribute("loseGoodsID",loseGoodsID);
        return "";
    }

    @ApiOperation(value = "3.4.3.4.2.2. 自取",notes = "**map中含有staff 此遗失物品负责员工 **" )
    @GetMapping("/pickUp")
    public String pickUp(@RequestParam("loseGoodsID") int loseGoodsID,
                          ModelMap map){
        Optional<LoseGoods> loseGoods=loseGoodsJpa.findById(loseGoodsID);
        String worker=loseGoods.get().getWorker();
        Staff staff=staffJpa.findByName(worker);
        map.addAttribute("staff",staff);
        System.out.println(staff.getCardID());
        System.out.println(staff.getDepartment());
        return "";
    }

    @ApiOperation(value = "3.4.4. 个人中心",notes = "" )
    @GetMapping("/personCenter")
    public String personCenter(ModelMap map){

        return "";
    }

    @ApiOperation(value = "3.4.4.1. 我的好友",notes = "" )
    @GetMapping("/friend")
    public String friend(ModelMap map){
        return "";
    }

}
