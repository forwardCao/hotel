package com.ncu.hotel.control;

import com.ncu.hotel.entiy.Server;
import com.ncu.hotel.entiy.Staff;
import com.ncu.hotel.jpa.ServerJpa;
import com.ncu.hotel.jpa.StaffJpa;
import com.ncu.hotel.json.ChaFang;
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
@Api(tags ={"3.3 Controller"} )
@Controller
@RequestMapping("/cService")
public class CustomerService {

    @Autowired
    private ServerJpa serverJpa;
    @Autowired
    private StaffJpa staffJpa;

    @ApiOperation(value = "3.3.1.1 客服部",notes = "页面**map**含有:  \n" +
            "**notReceiveServer**(Server类)  未应答任务  \n" +
            "**workid** 前端传给后端的员工ID")
    @GetMapping("/guestRoom")
    public String GuestRoom(@RequestParam("workid") @ApiParam(value = "员工ID",required = true) int workid, ModelMap map){
        List<Server> notReceiveServer =serverJpa.findByCallServerTypeAndWorkID("1",0);
        map.addAttribute("notReceiveServer", notReceiveServer);
        map.addAttribute("workid", workid);
        return " ";
    }


    @ApiOperation(value = "3.3.1.2 工程部",notes = "页面**map**含有:  \n" +
            "**notReceiveServer**(Server类)  未应答任务  \n" +
            "**workid** 前端传给后端的员工ID")
    @GetMapping("/project")
    public String project(@RequestParam("workid") @ApiParam(value = "员工ID",required = true) int workid, ModelMap map){
        List<Server> notReceiveServer =serverJpa.findByCallServerTypeAndWorkID("2",0);
        map.addAttribute("notReceiveServer", notReceiveServer);
        map.addAttribute("workid", workid);
        return " ";
    }


    @ApiOperation(value = "3.3.1.3 餐饮部",notes = "页面**map**含有:  \n" +
            "**notReceiveServer**(Server类)  未应答任务  \n" +
            "**workid** 前端传给后端的员工ID")
    @GetMapping("/repast")
    public String repast(@RequestParam("workid") @ApiParam(value = "员工ID",required = true) int workid, ModelMap map){
        List<Server> notReceiveServer =serverJpa.findByCallServerTypeAndWorkID("3",0);
        map.addAttribute("notReceiveServer", notReceiveServer);
        map.addAttribute("workid", workid);
        return " ";
    }

    @ApiOperation(value = "3.3.2 历史任务",notes = "页面**map**含有:  \n" +
            "**workid** 员工ID(前端传给后端的)")
    @GetMapping("/history")
    public String history(@RequestParam("workid") @ApiParam(value = "员工ID",required = true) int workid, ModelMap map){
        map.addAttribute("workid", workid);
        return " ";
    }

    @ApiOperation(value = "3.3.1.1.1 查房",notes = "页面**map**含有:  \n" +
            "**chaFang** 数据格式如输入(下方Parameters)")
    @GetMapping("/chaFang")
    public String chaFang(@RequestBody @ApiParam(value = "json数据",required = true)ChaFang chaFang, ModelMap map){
        map.addAttribute("chaFang", chaFang);
        return " ";
    }


    @ApiOperation(value = "3.3.3 个人中心",notes = "页面**map**含有:  \n" +
            "**staff** 员工类")
    @GetMapping("/personCenter")
    public String personCenter(@RequestParam("workid") @ApiParam(value = "员工ID",required = true) int workid, ModelMap map){
        Optional<Staff> staff=staffJpa.findById(workid);
        map.addAttribute("staff", staff.get());
        return " ";
    }

    @ApiOperation(value = "3.3.3.1 注册",notes = "")
    @GetMapping("/regesiter")
    public String regesiter(){
        return "";
    }
}
