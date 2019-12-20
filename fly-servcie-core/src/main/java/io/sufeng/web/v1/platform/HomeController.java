package io.sufeng.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.domain.entity.People;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static io.sufeng.context.utils.ResourceUtils.SESSION_USER;

/**
 * @Author zhangchao
 * @Date 2019/4/26 14:33
 * @Version v1.0
 */
@Controller
@RequestMapping(PlatformVersion.version + "/")
@Slf4j
public class HomeController {


    @GetMapping
    public String home(HttpServletRequest request, Model model){
        People  people = (People) request.getSession().getAttribute(SESSION_USER);
        if(people == null){
            return "redirect:login";
        }
        log.info(people.toString());
        model.addAttribute("user",people);
        model.addAttribute("userstr",people.toString());
        model.addAttribute(people);
        return "index";
    }

}
