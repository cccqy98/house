package com.aaa.house.utils;

import com.aaa.house.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Classname：CusUtil
 * @author: L_Fly
 * @Date: 2019/7/27  Time：10:44
 * @Version 1.0.0
 */

public class CusUtil {
    /**
     * 取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes att = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return att.getRequest();
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = null;
        if (null == session) {
            session = getRequest().getSession();
        }
        return session;
    }

    /**
     * 往session中保存登录后的客户信息
     *
     * @param user
     */
    public static void saveCus(User user) {
        getSession().setAttribute(ISysConstants.CUS, user);
    }


    /**
     * 从session获取客户信息
     *
     * @return
     */
    public static User getCusFromSession() {
        User user = (User) getSession().getAttribute(ISysConstants.CUS);
        return user;
    }

    /**
     * 退出登录，销毁session
     */
    public static void removeCusson() {
        getSession().invalidate();
    }
}
