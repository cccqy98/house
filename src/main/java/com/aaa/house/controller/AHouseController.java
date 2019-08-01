package com.aaa.house.controller;

import com.aaa.house.entity.HouseUser;
import com.aaa.house.service.AHouseService;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.FtpUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: AHouseController
 * Author:   曹康
 * Date:     2019/7/29 22:17
 * Description: 房屋
 */
@RestController
public class AHouseController {

    @Autowired
    private AHouseService aHouseService;
    @Autowired
    private FtpUtil ftpUtil;

    /**
     * 获取所有未联系的房源 加模糊查询
     * @return
     */
    @RequestMapping("getHouseUser")
    public Object getHouseUser(@RequestBody Map map){
        Map map1=new HashMap();
        map1.put("personlist",aHouseService.getHouseUserAll(map));
        map1.put("total",aHouseService.getHouseUserquall(map));
        return map1;
    }

    /**
     * 经济人联系房源
     * @return
     */
    @RequestMapping("upHouseUser")
    public ResultUtil upHouseUser(@RequestBody Map map){
        System.out.println("sdfsdfdsfds"+map);
        int i = aHouseService.upHouseUser(map);
        //交互类
        ResultUtil resultUtil=new ResultUtil();
        if (i>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setObject(i);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 获取当前登陆的经济人信息 添加房屋用
     * @return
     */
    @RequestMapping("getSessionStaff")
    public Object getSessionStaff(){
        Object object=CusUtil.getStaff();
        return object;
    }

    /**
     * 查询当前经济人联系的房源
     * @return
     */
    @RequestMapping("getStaffUser")
    public  List<Map>  getStaffUser(){
        return aHouseService.getStaffUser();
    }

    /**
     * 查询当前房东的房源
     * @return
     */
    @RequestMapping("getUserHouse")
    public List<HouseUser> getUserHouse(@RequestBody Map map){
        System.out.println(aHouseService.getUserHouse(map));
        return aHouseService.getUserHouse(map);
    }

    /**
     * 添加房屋
     * @param map
     * @return
     */
    @RequestMapping("setHouse")
    public int setHouse(@RequestBody Map map){
        System.out.println(map);
        return 1;
    }

    /**
     * 房屋封面
     * @param headPic
     * @return
     */
    @RequestMapping("uploadHeadPic")
    public Object updateStaff(@RequestParam MultipartFile headPic){
        //原始名
        String originalFilename = headPic.getOriginalFilename();
        //新文件路径
        String newFileName=ftpUtil.upLoad(headPic);
        Map map =new HashMap();
        map.put("originalFilename",originalFilename);
        map.put("newFileName",newFileName);

        return map;
    }
    /**
     * 房产图
     * @param headPic1
     * @return
     */
    @RequestMapping("uploadHeadPic1")
    public Object updateStaff1(@RequestParam MultipartFile headPic1){
        //原始名
        String originalFilename = headPic1.getOriginalFilename();
        //新文件路径
        String newFileName=ftpUtil.upLoad(headPic1);
        Map map =new HashMap();
        map.put("originalFilename",originalFilename);
        map.put("newFileName",newFileName);

        return map;
    }
    /**
     * 房图
     * @param headPic2
     * @return
     */
    @RequestMapping("uploadHeadPic2")
    public Object updateStaff2(@RequestParam MultipartFile headPic2){
        //原始名
        String originalFilename = headPic2.getOriginalFilename();
        //新文件路径
        String newFileName=ftpUtil.upLoad(headPic2);
        System.out.println("sdfffffffffdfsfsdfsdfs"+newFileName);
        Map map =new HashMap();
        map.put("originalFilename",originalFilename);
        map.put("newFileName",newFileName);

        return map;
    }

}
