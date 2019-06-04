package org.flyants.chat.configuration;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.domain.entity.platform.Token;
import org.flyants.chat.domain.repository.TokenRepository;
import org.flyants.chat.utils.JWTManager;
import org.flyants.common.annotation.Anonymous;
import org.flyants.common.exception.TokenExpireException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;


/**
 * zhangchao
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {


    public static final String AUTHORIZATION = "Authorization";


    @Autowired
    TokenRepository tokenRepository;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有Anonymous注释，有则跳过认证
        if (method.isAnnotationPresent(Anonymous.class)) {
            return true;
        }

        log.info("Authorization:{}",token);

        //检查有没有需要用户权限的注解
        // 执行认证
        if (StringUtils.isEmpty(token)) {
            throw new TokenExpireException("401","token expire");
        }
        // 获取 token 中的 user id

        Optional<Token> accessToken = tokenRepository.findByAccessToken(token);

        if(!accessToken.isPresent()){
            throw new TokenExpireException("401","token expire");
        }

        String peopleId = accessToken.get().getPeopleId();
        JWTManager.set(peopleId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
