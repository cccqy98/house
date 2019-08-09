package com.aaa.house.service;

import com.aaa.house.dao.AStaffMappng;
import com.aaa.house.entity.Staff;
import com.aaa.house.entity.StaffRole;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.PasswordHelper;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: AStaffServiceImpl
 * Author:   曹康
 * Date:     2019/7/26 10:16
 * Description: 员工
 */
@Service
public class AStaffServiceImpl implements AStaffService{
    @Autowired
    private AStaffMappng aStaffMappng;

    public Staff staffAll=null;

    //员工登录
    @Override
    public Staff selectStaff(Staff staff) {
        //md5加密
        PasswordHelper.encryptPassword(staff);
        //执行sql
         staffAll=aStaffMappng.selectStaff(staff);
        if (staffAll!=null){
            //存session
            CusUtil.setStaff(staffAll);
            return staffAll;
        }
        return null;
    }

    /**
     * 判断员工是否登录
     * @return
     */
    @Override
    public Staff selectSession() {
        //调用工具类CusUtill
         staffAll=CusUtil.getStaff();
         if (staffAll==null){
             return null;
         }
        return staffAll;
    }



    /**
     * 查询所有用户
     * @return
     */
    @Override
    public Map getUser(Map map) {
        Map map1=new HashMap();
        map1.put("personlist",aStaffMappng.getUser( map));
        map1.put("total",aStaffMappng.getUserNum(map));
        return map1;
    }

    /**
     * 修改用户信息
     * @param map
     * @return
     */
    @Override
    public ResultUtil upUser(Map map) {
        int aa=aStaffMappng.upUser(map);
        ResultUtil resultUtil=new ResultUtil();
        if (aa>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 查询所有员工信息
     * @return
     */
    @Override
    public Map getStaffAll(Map map) {
        Map map1=new HashMap();
        map1.put("personlist",aStaffMappng.getStaffAll(map));
        map1.put("total",aStaffMappng.getStaffNum(map));
        return map1;
    }

    /**
     * 添加员工
     * @param map
     * @return
     */
    @Override
    public ResultUtil insertStaff(Map map) {
        //交互类
        ResultUtil resultUtil=new ResultUtil();
        //获取  加密
        String password=map.get("staff_password").toString();
        String name=map.get("staff_num").toString();
        String staff_password=PasswordHelper.passwordStaff(name,password);
        map.put("staff_password",staff_password);
        int aa=aStaffMappng.insertStaff(map);

        /*--------------添加角色----------------------*/
        //临时用
        List<StaffRole> list=new ArrayList<>();
        try {
            //角色
            List checked= (List) map.get("rid");
            for (Object check : checked) {
                StaffRole staffRole =new StaffRole();
                staffRole.setSid(map.get("staffid").toString());//当前用户的id
                staffRole.setRid(check.toString());//角色
                list.add(staffRole);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }

        //角色用户中间表
        int staffRole=aStaffMappng.updateRoleStaff(list);



        if (aa>0&&staffRole>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 获取最后一个编号
     * @return
     */
    @Override
    public Map getStaffnum() {
        Map map=new HashMap();
        Map aa=aStaffMappng.getStaffnum();
        String staffnum=aa.get("staff_num").toString();
        int num= Integer.parseInt(staffnum)+1;
        String staff_num="QY"+num;
        System.out.println("编号"+staff_num);
        map.put("staff_num",staff_num);
        return map;
    }

    /**
     * 修改员工信息
     * @param map
     * @return
     */
    @Override
    public ResultUtil updateStaff(Map map) {
        //交互类
        ResultUtil resultUtil=new ResultUtil();
        //获取员工id
        String staffId= null;
        try {
            staffId = map.get("staff_id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }


        /*--------------删除角色----------------------*/
        int sr=aStaffMappng.deleteRoleStaff(staffId);


        /*--------------修改角色----------------------*/
        //临时用
        List<StaffRole> list=new ArrayList<>();
        try {
            //角色
            List checked= (List) map.get("rid");
            for (Object check : checked) {
                StaffRole staffRole =new StaffRole();
                staffRole.setSid(staffId);
                staffRole.setRid(check.toString());
                list.add(staffRole);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }

        //sql在角色用户中间表
        int staffRole=aStaffMappng.updateRoleStaff(list);
        //获取  加密
        String password=map.get("staff_password").toString();
        String name=map.get("staff_num").toString();
        String staff_password=PasswordHelper.passwordStaff(name,password);
        map.put("staff_password",staff_password);

        int aa=aStaffMappng.updateStaff(map);

        if (aa>0&&staffRole>0&&sr>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }


}
