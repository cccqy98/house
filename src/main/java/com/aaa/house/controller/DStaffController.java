package com.aaa.house.controller;/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 19:06 2019/8/7
 * @Modified By:
 */

import com.aaa.house.entity.Staff;
import com.aaa.house.service.DStaffService;
import com.aaa.house.utils.FtpConfig;
import com.aaa.house.utils.FtpUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DStaffController
 * @Author 龚志博
 * @Date 2019/8/7 19:06
 * @Version 1.0
 */
@RestController
public class DStaffController {
    @Autowired
    private DStaffService staffService;
    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private FtpConfig ftpConfig;

    //spring core io里面提供的资源加载器
    @Autowired
    private ResourceLoader resourceLoader;
    /**
     * 单个
     * @param
     * @return
     */
    @RequestMapping("queryStaffId")
    public Object queryStaffId(){
        List<Staff> emp = staffService.queryStaffId();
        if(emp!=null){
            return new ResultUtil(ISysConstants.SUCCESSCODE,null,emp.get(0));
        }
        return new ResultUtil(ISysConstants.ERRORCODE,null,null);
    }
    @RequestMapping("updateStaff")
    public Object updateStaff(Staff staff){
        int staff_id = staffService.updateStaff(staff);
        if(staff_id>0){
            return new ResultUtil(ISysConstants.SUCCESSCODE,"修改成功,请重新登陆",null);
        }
        return new ResultUtil(ISysConstants.ERRORCODE,"修改失败",null);
    }
    /**
     * 显示头像
     * @param fileName
     * @return
     */
    @RequestMapping("/showStaff")
    public ResponseEntity show(String fileName){
        //ftp://admin:admin@192.168.11.116/ftp-dir-xn/84c7f5ed-17e1-4f9a-a775-552254d66386.jpg
        return ResponseEntity.ok(resourceLoader.getResource("ftp://"+ftpConfig.getFtpUserName()+":"+
                ftpConfig.getFtpPassWord()+"@"+ftpConfig.getRemoteIp()+ftpConfig.getRemotePath()+"/"+fileName));
    }

    @RequestMapping("/uploadHeadPicStaff")
    public Object uploadHeadPic(@RequestParam MultipartFile headPic5){
        System.out.println("......................");
        String originalFilename = headPic5.getOriginalFilename();
        String newFileName = ftpUtil.upLoad(headPic5);
        Map map  = new HashMap();
        map.put("originalFilename",originalFilename);
        map.put("newFileName",newFileName);
        return map;
    }
}
