package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.domain.service.AreaService;
import org.flyants.chat.dto.Address;
import org.flyants.chat.dto.Province;
import org.flyants.chat.dto.app.SendSmsCodeDto;
import org.flyants.chat.utils.LocationUtils;
import org.flyants.common.annotation.Anonymous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/31 17:04
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/system")
@Slf4j
public class AppSystemController {

    @Autowired
    private AreaService areaService;


    @Anonymous
    @GetMapping("/sendSmsCode")
    public void sendSmsCode(String phone){
        log.info("sendSmsCode:phone:{}"+phone);
    }


    @Anonymous
    @GetMapping("/area/listAll")
    public List<Province> listAll(){
        return areaService.listAll();
    }

    @Anonymous
    @GetMapping("/searchLocation")
    public List<Address> searchLocation(String longitude, String latitude){
        return LocationUtils.searchLocation(longitude,latitude);
    }


}
