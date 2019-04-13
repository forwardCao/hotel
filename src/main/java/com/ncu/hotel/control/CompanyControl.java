package com.ncu.hotel.control;

import com.alibaba.fastjson.JSONObject;
import com.ncu.hotel.entiy.Hotel;
import com.ncu.hotel.entiy.Member;
import com.ncu.hotel.entiy.Room;
import com.ncu.hotel.entiy.Sale;
import com.ncu.hotel.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyControl {
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

    @GetMapping("/hotelManagement")
    public String queryH(ModelMap map){
        Optional<Hotel> hotel = hotelJpa.findById(888);
        map.addAttribute("hotel", hotel.get());
        return "html/company/jdgl";
    }

    @GetMapping("/hotelSearch")
    public String queryHotel(@RequestParam("name") String name,ModelMap map){
        Hotel hotel = hotelJpa.findByHotelNameOrBrandOrAddress(name, name, name);
        map.addAttribute("hotel", hotel);
        return "html/company/jdgl";
    }

    @GetMapping("/memberManagement")
    public String queryH1(ModelMap map){
        List<Member> member=memberJpa.findAll();
        map.addAttribute("Member", member);
        return "html/company/index";
    }

    @GetMapping("/sumUp")
    public String queryH2(ModelMap map){
        List<Room> room=roomJpa.findByHotelID(888);
        map.addAttribute("Room", room);
        Optional<Hotel> hotel = hotelJpa.findById(888);
        map.addAttribute("hotel", hotel.get());
        return "html/company/hstj";
    }

}
