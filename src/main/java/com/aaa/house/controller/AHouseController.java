package com.aaa.house.controller;

import com.aaa.house.entity.HouseImg;
import com.aaa.house.entity.HouseLaIm;
import com.aaa.house.entity.HouseLable;
import com.aaa.house.entity.HouseUser;
import com.aaa.house.service.AHouseService;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.FtpUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
        return aHouseService.getHouseUserAll(map);
    }

    /**
     * 经济人联系房源
     * @return
     */
    @RequestMapping("upHouseUser")
    public ResultUtil upHouseUser(@RequestBody Map map){
        return aHouseService.upHouseUser(map);
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
        return aHouseService.getUserHouse(map);
    }

    /**
     * 添加房屋
     * @param map
     * @return
     */
    @RequestMapping("setHouse")
    @Transactional//事务
    public ResultUtil setHouse(@RequestBody Map map){
        //交互类
        ResultUtil resultUtil=new ResultUtil();

        //房屋id
        String uh_name= null;
        try {//防止获取不到值
            uh_name = map.get("house_id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;

        }


        /*--------------添加房屋组图片表----------------------*/
        List<HouseImg> listimg=new ArrayList<>();
        try {//防止获取不到值
            String aa= (String) map.get("headPic2");
            String[] imgg=aa.split(",");
            for (String s : imgg) {
                HouseImg houseImg=new HouseImg();
                houseImg.setHid(uh_name);
                houseImg.setHimg(s);
                listimg.add(houseImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }
        //添加房屋组图表
        int img=aHouseService.setHouseImg(listimg);




      /*--------------添加房屋标签----------------------*/
        //临时用
        List<HouseLable> list=new ArrayList<>();

        //房屋 标签
        try {
            List checked= (List) map.get("checkedCities");
            for (Object check : checked) {
                 HouseLable houseLable =new HouseLable();
                houseLable.setHo_id(uh_name);
                houseLable.setLa_id(check.toString());
                list.add(houseLable);
             }
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }
        //sql在房屋标签中间表
        int lable=aHouseService.setHouseLable(list);
        //sql 在房屋表里添加
        int hou=aHouseService.setHouse(map);


        //判断
        if (img>0&&lable>0&&hou>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
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
        Map map =new HashMap();
        map.put("originalFilename",originalFilename);
        map.put("newFileName",newFileName);

        return map;
    }


    /**
     * 查询所有房屋
     *
     * @return
     */
    @RequestMapping("getHouse")
    public Map getHouse(@RequestBody Map map){
        return aHouseService.getHouse(map);
    }



    /**
     * 获取我的房屋
     * @param map
     * @return
     */
    @RequestMapping("getMyHouse")
    public  Map getMyHouse(@RequestBody Map map){
        Map map1=new HashMap();
        map1.put("personlist",aHouseService.getMyHouse(map));
        map1.put("total",aHouseService.getMyhouseNum(map));
        return map1;
    }


    /**
     * MY房屋修改
     * @return
     */
    @RequestMapping("setHouseMyAll")
    public ResultUtil setHouseMyAll(@RequestBody Map map){
        ResultUtil resultUtil=aHouseService.updaMyHouse(map);
        return resultUtil;
    }

    /**
     * MY房屋下架
     * @return
     */
    @RequestMapping("setHouseMyXiajia")
    public ResultUtil setHouseMyXiajia(@RequestBody Map map){
        ResultUtil resultUtil=aHouseService.upMyHouseXiajia(map);
        return resultUtil;
    }

    /**
     * 出租
     * @param map
     * @return
     */
    @RequestMapping("getLookMyHouse")
    public Map getLookMyHouse(@RequestBody Map map){
        Map map1=new HashMap();
        map1.put("personlist",aHouseService.getLookMyHouse(map));
        map1.put("total",aHouseService.getLookMyHouseNum(map));
        return map1;
    }

    /**
     * 获取该房屋的预约信息
     * @return
     */
    @RequestMapping("getUserLookHouse")
    public List<Map> getUserLookHouse(@RequestBody Map map){
        return aHouseService.getUserLookHouse(map);
    }

    /**
     * 修改为以看房
     * @return
     */
    @RequestMapping("updaLookHouse")
    public ResultUtil updaLookHouse(@RequestBody Map map){
        return aHouseService.updalookHouse(map);
    }

    /**
     * 添加看房时间和状态
     * @return
     */
    @RequestMapping("upLookHousestate")
    public ResultUtil upLookHousestate(@RequestBody Map map){
        return aHouseService.upLookHousestate(map);
    }

    /**
     *删除无意租的看房用户
     * @return
     */
    @RequestMapping("deLookHouse")
    public ResultUtil deLookHouse(@RequestBody Map map){
        return aHouseService.deLookHouse(map);
    }

    /**
     * 合同添加时间
     * @param map
     * @return
     */
    @RequestMapping("upPact")
    public ResultUtil upPact(@RequestBody Map map){
        return aHouseService.upPact(map);
    }

    /**
     * 合同添加时间
     * @param map
     * @return
     */
    @RequestMapping("upMYHouse")
    public ResultUtil upMYHouse(@RequestBody Map map){
        return aHouseService.upMYHouse(map);
    }

    /**
     * 查询我的租客
     * @return
     */
    @RequestMapping("getpact")
    public Map getpact(@RequestBody Map map){
        return aHouseService.getpact(map);
    }

}
