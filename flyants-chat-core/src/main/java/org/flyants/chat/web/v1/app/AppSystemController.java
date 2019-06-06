package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.dto.app.SendSmsCodeDto;
import org.flyants.common.annotation.Anonymous;
import org.springframework.web.bind.annotation.*;

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






}
