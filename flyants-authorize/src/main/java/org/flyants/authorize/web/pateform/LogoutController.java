package org.flyants.authorize.web.pateform;

import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/logout")
@Slf4j
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_USER);
        return "redirect:/";
    }

}
