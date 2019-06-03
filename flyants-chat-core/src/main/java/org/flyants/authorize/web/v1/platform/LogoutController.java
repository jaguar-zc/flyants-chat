package org.flyants.authorize.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.common.annotation.Anonymous;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.flyants.authorize.utils.ResourceUtils.SESSION_USER;

/**
 * @Author zhangchao
 * @Date 2019/4/26 13:46
 * @Version v1.0
 */
@Controller
@RequestMapping(PlatformVersion.version + "/logout")
@Slf4j
public class LogoutController {


    @Anonymous
    @GetMapping
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_USER);
        return "redirect:/";
    }

}
