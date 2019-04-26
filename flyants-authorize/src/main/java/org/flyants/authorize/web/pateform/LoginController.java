package org.flyants.authorize.web.pateform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.oauth2.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.flyants.authorize.utils.ResourceUtils.SESSION_USER;

/**
 * @Author zhangchao
 * @Date 2019/4/26 13:46
 * @Version v1.0
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @Autowired
    PeopleService peopleService;


    @GetMapping
    public String login(Model model, String redirectUrl) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model, String username, String password, String redirectUrl) {
        Optional<People> people = peopleService.findByUsernameAndPassword(username, password);
        if (people.isPresent()) {
            People people1 = people.get();
            log.info(people1.toString());
            request.getSession().setAttribute(SESSION_USER, people1);
            if(StringUtils.isEmpty(redirectUrl)){
                return "redirect:/";
            }else{
                return "redirect:" + redirectUrl;
            }
        }
        model.addAttribute("error", "用户名密码不存在");
        return "login";
    }


}
