package com.ncu.hotel.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ncu.hotel.entiy.Hotel;
import com.ncu.hotel.entiy.Server;
import com.ncu.hotel.entiy.Staff;
import com.ncu.hotel.jpa.HotelJpa;
import com.ncu.hotel.jpa.ServerJpa;
import com.ncu.hotel.jpa.StaffJpa;
import com.ncu.hotel.json.CServ;
import com.ncu.hotel.json.HistoryWork;
import com.ncu.hotel.json.PersonCenter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created bt caoqianfeng on 2019/4/15
 */
@Api(tags ={"3.3 RestController"} )
@RestController
@RequestMapping("/cs")
public class RestCustomerService {

    @Autowired
    private ServerJpa serverJpa;
    @Autowired
    private StaffJpa staffJpa;
    @Autowired
    private HotelJpa hotelJpa;

    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40

    public boolean response(CServ json) throws ParseException {

        String callID = json.getCallID();
        String workid = json.getWorkid();

        Optional<Server> server=serverJpa.findById(Integer.parseInt(callID));
        Optional<Staff> staff=staffJpa.findById(Integer.parseInt(workid));

        server.get().setStaff(staff.get());
        server.get().setWorkID(Integer.parseInt(workid));
        server.get().setResponseStatus((byte)1);
        Date date=new Date();

        String nowTime = simpleFormat.format(new Date());
        long mowMinute = simpleFormat.parse(nowTime).getTime();

        String callTime = simpleFormat.format(server.get().getCallTime());
        long from = simpleFormat.parse(callTime).getTime();
        int responseTime= (int) ((mowMinute - from)/(1000 * 60));
        server.get().setResponseTime(responseTime);
        serverJpa.save(server.get());
        return true;
    }

    public boolean finsh(CServ json) throws ParseException {

        String callID = json.getCallID();
        String workid = json.getWorkid();

        Optional<Server> server=serverJpa.findById(Integer.parseInt(callID));
        Optional<Staff> staff=staffJpa.findById(Integer.parseInt(workid));

        server.get().setStaff(staff.get());
        server.get().setWorkID(Integer.parseInt(workid));
        server.get().setFinishStatus((byte)1);
        Date date=new Date();

        String nowTime = simpleFormat.format(new Date());
        long mowMinute = simpleFormat.parse(nowTime).getTime();

        String callTime = simpleFormat.format(server.get().getCallTime());
        long from = simpleFormat.parse(callTime).getTime();

        int finshTime= (int) ((mowMinute - from)/(1000 * 60));

//        System.out.println("finshTime: "+finshTime);
        server.get().setFinishTime(finshTime);
        serverJpa.save(server.get());
        return true;
    }

    @ApiOperation(value = "3.3.1.1 客服部",notes = "客服部")
    @PostMapping("/responseRoom")
    public boolean ResponseRoom(@RequestBody @ApiParam(value = "json数据",required = true) CServ json) throws ParseException {


        if (response(json)){
            return true;
        }else {
            return false;
        }
    }

    @ApiOperation(value = "3.3.1.1 客服部完成",notes = "客服完成")
    @PostMapping("/finshRoom")
    public boolean finshRoom(@RequestBody @ApiParam(value = "json数据",required = true) CServ json) throws ParseException {

        if (finsh(json)){
            return true;
        }else {
            return false;
        }
    }

    @ApiOperation(value = "3.3.1.2 工程部应答",notes = "工程部应答")
    @PostMapping("/responseProject")
    public boolean responseProject(@RequestBody @ApiParam(value = "json数据",required = true) CServ json) throws ParseException {

        if (response(json)){
            return true;
        }else {
            return false;
        }

    }

    @ApiOperation(value = "3.3.1.2 工程部完成",notes = "工程部完成")
    @PostMapping("/finshProject")
    public boolean finshProject(@RequestBody @ApiParam(value = "json数据",required = true) CServ json) throws ParseException {

        if (finsh(json)){
            return true;
        }else {
            return false;
        }
    }

    @ApiOperation(value = "3.3.1.3 餐饮部回应",notes = "餐饮部回应")
    @PostMapping("/responseRepast")
    public boolean responseRepast(@RequestBody @ApiParam(value = "json数据",required = true) CServ json) throws ParseException {

        if (response(json)){
            return true;
        }else {
            return false;
        }

    }


    @ApiOperation(value = "3.3.1.3 餐饮部完成",notes = "餐饮部完成")
    @PostMapping("/finshRepast")
    public boolean finshRepast(@RequestBody @ApiParam(value = "json数据",required = true) CServ json) throws ParseException {

        if (finsh(json)){
            return true;
        }else {
            return false;
        }
    }

    @ApiOperation(value="3.3.2 历史工作", notes="历史工作")
    @PostMapping("/historyWork")
    public List<Server> historyWork(@RequestBody @ApiParam(value = "json数据",required = true)HistoryWork historyWork) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateBegin = historyWork.getDateBegin()+ " 00:00:00";
        String dateEnd = historyWork.getDateEnd()+ " 23:59:59";
        String workid = historyWork.getWorkid();

        Date start=format.parse(dateBegin);
        Date end=format.parse(dateEnd);

        List<Server> servers=serverJpa.findByWorkIDAndCallTimeBetween(Integer.parseInt(workid),start,end);

        return servers;
    }

    @ApiOperation(value="3.3.3 个人中心", notes="个人中心")
    @PostMapping("/personCenter")
    public boolean personCenter(@RequestBody @ApiParam(value = "json数据",required = true)PersonCenter personCenter){
        int workID=personCenter.getWorkID();
        byte flag=personCenter.getFlag();
        Optional<Staff> staff =staffJpa.findById(workID);
        staff.get().setFlag(flag);
        staffJpa.save(staff.get());
        return true;
    }

    @ApiOperation(value="3.3.3.1. 注册", notes="#**Parameters有误，以如下格式为准**  \n" +
            "{  \n" +
            "  \"department\": \"客房部\",  \n" +
            "  \"hotelID\": 666,  \n" +
            "  \"name\": \"曹操\",  \n" +
            "  \"workID\": 20  \n" +
            "}")
    @PostMapping("/workRegesiter")
    public boolean workRegesiter(@RequestBody @ApiParam(value = "json数据",required = true)Staff staff){
        int hotelId=staff.getHotelID();
        Optional<Hotel> hotel=hotelJpa.findById(hotelId);
        staff.setHotel(hotel.get());
        staffJpa.save(staff);
        return true;
    }

}
