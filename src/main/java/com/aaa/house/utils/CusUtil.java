package com.aaa.house.utils;

import com.aaa.house.entity.Staff;
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
 * 存 取session
 */
public class CusUtil {
    /**
     * 获取 HttpServletRequest
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

    //--------------------------------------------------------------------------------------------
                                             /*后台sesion*/
    //流程:1.getRequest() 获取HttpServletRequest
    //     2.getSession() 获取session
    //     3.setStaff() 存值
    //     3.getStaff() 取值
    //--------------------------------------------------------------------------------------------

    /**
     * 后台存 session
     * @param staff
     */
    public static void setStaff(Staff staff){
        //ISysConstants.EMP:键
        getSession().setAttribute(ISysConstants.EMP,staff);
    }

    /**
     * 后台取 session
     * @return
     */
    public static Staff getStaff(){
        Staff staff= (Staff) getSession().getAttribute(ISysConstants.EMP);
        return staff;
    }

}
