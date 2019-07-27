package com.aaa.house.controller;

import com.aaa.house.entity.Staff;
import com.aaa.house.service.AStaffService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.List;

/**
 * FileName: AStaffController
 * Author:   曹康
 * Date:     2019/7/26 10:19
 * Description: 员工
 */
@RestController
public class AStaffController {
    @Autowired
    private AStaffService aStaffService;

    /**
     * 员工登录
     * @param staff 员工实体类
     * @return
     */
    @RequestMapping("/staffid")
    public ResultUtil staffId(Staff staff){
        Staff staff1=aStaffService.selectStaff(staff);
        System.out.println(staff);
        //状态码
        ResultUtil resultUtil=new ResultUtil();
       if (staff1!=null) {
           resultUtil.setCode(ISysConstants.SUCCESSCODE);
           return resultUtil;
       }
        resultUtil.setCode(ISysConstants.ERRORCODE);
        resultUtil.setMsg("登录失败，用户名或密码错误");
        return resultUtil;
    }

    /**
     * 验证是否登录 即获取session值传在前台
     * @return
     */
    @RequestMapping("selectSession")
    public ResultUtil selectSession(){
        Staff staff = aStaffService.selectSession();
        System.out.println("session++++++"+staff);
        ResultUtil resultUtil=new ResultUtil();//创建交互类
        if (staff!=null){
            resultUtil.setObject(staff);//数据
            resultUtil.setCode(ISysConstants.SUCCESSCODE);//状态码
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }



    /**
     * 查询所有员工
     * @return
     */
    @RequestMapping("/selectStaffAll")
    public List<Staff> selectStaffAll(){
        return aStaffService.selectStaffAll();
    }


    /**
     * 员工添加
     * @param staff
     * @return
     */
    @RequestMapping("/insertStaff")
    public int insertStaff(Staff staff){
        return aStaffService.insertStaff(staff);
    }
}
