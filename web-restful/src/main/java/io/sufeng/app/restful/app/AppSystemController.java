package io.sufeng.app.restful.app;

import io.sufeng.common.annotation.Anonymous;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangchao
 * @Date 2019/5/31 17:04
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/system")
@Slf4j
public class AppSystemController {

    @Anonymous
    @GetMapping("/sendSmsCode")
    public void sendSmsCode(String phone){
        log.info("sendSmsCode:phone:{}"+phone);
    }

//    @Anonymous
//    @GetMapping("/searchLocation")
//    public List<Address> searchLocation(String longitude, String latitude){
//        return LocationUtils.searchLocation(longitude,latitude);
//    }


}
