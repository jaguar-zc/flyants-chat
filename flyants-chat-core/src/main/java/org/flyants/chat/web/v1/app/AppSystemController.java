package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.dto.app.SendSmsCodeDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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



    @PostMapping("/sendSmsCode")
    public void sendSmsCode(@RequestBody SendSmsCodeDto smsCode){
        log.info("sendSmsCode:phone:{}"+smsCode.getPhone());
    }



}
