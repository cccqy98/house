package com.aaa.house.controller;

import com.aaa.house.entity.Staff;
import com.aaa.house.service.AStaffService;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        //交互类
        ResultUtil resultUtil=new ResultUtil();
       if (staff1!=null) {
           //状态码
           resultUtil.setCode(ISysConstants.SUCCESSCODE);
           return resultUtil;
       }
        resultUtil.setCode(ISysConstants.ERRORCODE);
        resultUtil.setMsg("登录失败，用户名或密码错误");
        return resultUtil;
    }

    /**
     * 员工注销
     */
    @RequestMapping("removeStaff")
    public ResultUtil removeStaff(){
        //注销
        CusUtil.removeCusson();
        //返回执行成功状态码
        return new ResultUtil(ISysConstants.SUCCESSCODE,null,null);
    }

    /**
     * 验证是否登录 即获取session值传在前台
     * @return
     */
    @RequestMapping("selectSession")
    public ResultUtil selectSession(){
        //数据
        Staff staff = aStaffService.selectSession();
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


    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("getUser")
    public Map getUser(@RequestBody Map map){
        return aStaffService.getUser(map);
    }


    /**
     * 修改用户信息
     * @param map
     * @return
     */
    @RequestMapping("upUser")
    public ResultUtil upUser(@RequestBody Map map){
        return aStaffService.upUser(map);
    }

}
