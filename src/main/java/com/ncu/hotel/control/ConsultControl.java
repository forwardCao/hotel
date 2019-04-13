package com.ncu.hotel.control;

import com.ncu.hotel.entiy.Member;
import com.ncu.hotel.entiy.Room;
import com.ncu.hotel.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/consult")
public class ConsultControl {
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
        List<Room> room=roomJpa.findAll();
        map.addAttribute("host", "http://blog.didispace.com");
        map.addAttribute("Room", room);
        // return模板文件的名称，对应th:src/main/resources/templates/index.html
        return "html/company/jdgl";
    }

    @GetMapping("/memberManagement")
    public String queryH1(ModelMap map){
        List<Member> member=memberJpa.findAll();
        map.addAttribute("host", "http://blog.didispace.com");
        map.addAttribute("Member", member);
        // return模板文件的名称，对应th:src/main/resources/templates/index.html
        return "html/company/index";
    }

    @GetMapping("/sumUp")
    public String queryH2(ModelMap map){
        List<Room> room=roomJpa.findAll();
        map.addAttribute("host", "http://blog.didispace.com");
        map.addAttribute("Room", room);
        // return模板文件的名称，对应th:src/main/resources/templates/index.html
        return "html/company/hstj";
    }
}
