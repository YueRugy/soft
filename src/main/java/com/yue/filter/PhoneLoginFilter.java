package com.yue.filter;

import com.yue.constant.SoftConstant;
import com.yue.constant.TimeConstant;
import com.yue.core.AppContent;
import com.yue.core.Token;
import com.yue.core.TokenOperation;
import com.yue.entity.User;
import com.yue.enums.ErrorMessage;
import com.yue.enums.UserStatus;
import com.yue.exception.SoftException;
import com.yue.mapper.UserMapper;
import com.yue.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yue on 2017/9/12
 */
@WebFilter(filterName = "phoneLoginFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })
public class PhoneLoginFilter extends AdapterFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        userMapper = (UserMapper) ctx.getBean("userMapper");
    }


    private UserMapper userMapper;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ResponseUtil.setHttpServletResponse(resp);
        String path = req.getRequestURI();
        if (EscapeUrl.escapeUrls.contains(path)) {
            chain.doFilter(req, resp);
            return;
        }

        Cookie[] cookies = req.getCookies();
        Cookie cookie;
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals(SoftConstant.COOKIE_PHONE_NAME)) {
                    String tokenStr = cookie.getValue();
                    if (StringUtils.isBlank(tokenStr))
                        throw new SoftException(ErrorMessage.need_login);

                    Token token = TokenOperation.toPhoneToken(tokenStr, resp);
                    User user = userMapper.selectByUserId(token.getId());
                    TokenOperation.validPhoneUser(user, token, resp);
                    if (UserStatus.normal.getValue() == user.getStatus()) {

                        AppContent.init(req, resp);
                        AppContent.setToken(token);
                        chain.doFilter(req, resp);
                        return;
                    } else {
                        ResponseUtil.setCookie(SoftConstant.COOKIE_PHONE_NAME, "", SoftConstant.PATH, TimeConstant.TIME_DELETE_COOKIE_MILLISECOND, resp);
                        throw new SoftException(ErrorMessage.account_is_disabled);
                    }
                }
            }
        }

        throw new SoftException(ErrorMessage.need_login);
    }
}
