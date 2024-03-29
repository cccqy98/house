package com.aaa.house.service;

import com.aaa.house.dao.AHouseMapping;
import com.aaa.house.entity.*;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: AHouseServiceImpl
 * Author:   曹康
 * Date:     2019/7/29 22:06
 * Description: 房屋
 */
@Service
@Transactional//事务
public class AHouseServiceImpl implements AHouseService{

    @Autowired
    private AHouseMapping aHouseMapping;

    /**
     * 查询客户发布未联系的房屋
     * @return
     */
    @Override
    public Map getHouseUserAll(Map map) {
        Map map1=new HashMap();
        map1.put("personlist",aHouseMapping.getHouseUserAll(map));
        map1.put("total",aHouseMapping.getHouseUserquall(map));
        return map1;
    }


    /**
     * 联系房源
     * @return
     */
    @Override
    public ResultUtil upHouseUser(Map map) {
        //获取当前登陆的经济人工号
        Staff staff = CusUtil.getStaff();
        map.put("uh_staff",staff.getStaff_num());
        //交互类
        ResultUtil resultUtil=new ResultUtil();
        int i = aHouseMapping.upHouseUser(map);

        if (i>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setObject(i);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 查询当前经济人联系的房源
     * @return
     */
    @Override
    public  List<Map>  getStaffUser() {
        String uh_staff=CusUtil.getStaff().getStaff_num();
        System.out.println(uh_staff);
        return aHouseMapping.getStaffUser(uh_staff);
    }

    /**
     * 查询当前房东的房源
     * @return
     */
    @Override
    public List<HouseUser> getUserHouse(Map map) {
        return aHouseMapping.getUserHouse(map);
    }

    /**
     * 查询所有房屋
     * @return
     */
    @Override
    public Map getHouse(Map map) {
        Map map1=new HashMap();
        map1.put("personlist", aHouseMapping.getHouse(map));
        map1.put("total",aHouseMapping.getHouseNum(map));
        return map1;
    }

    @Override
    public int getHouseNum(Map map) {
        return aHouseMapping.getHouseNum(map);
    }

    /**
     * 修改房屋
     * @param map
     * @return
     */
    @Override
    public ResultUtil UpdaHouse(Map map) {
        int a= 0;
        ResultUtil resultUtil=new ResultUtil();//交互类
        try {//防止sql报错
            a = aHouseMapping.updaHouse(map);
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 修改我的房屋
     * @param map
     * @return
     */
    @Override
    public ResultUtil updaMyHouse(Map map) {
        int a= 0;
        ResultUtil resultUtil=new ResultUtil();//交互类
        try {//防止sql报错
            a = aHouseMapping.updaMyHouse(map);
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 房屋删除
     * @return
     */
    @Override
    public ResultUtil updateHouseDelete(Map map) {

        int a= 0;
        ResultUtil resultUtil=new ResultUtil();//交互类
        try {//防止sql报错
            a = aHouseMapping.deleteHouse(map);
        } catch (Exception e) {
            e.printStackTrace();
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            return resultUtil;
        }
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }


    /**
     * 添加房屋
     * @param map
     * @return
     */
    @Override
    public ResultUtil setHouse(Map map) {
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
            System.out.println("图片：0000"+aa);
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
        int img=aHouseMapping.setHouseImg(listimg);
        /*--------------修改house_user的添加状态 ----------------------*/


        aHouseMapping.updateHouseUser(uh_name);



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
        int lable=aHouseMapping.setHouseLable(list);
        //sql 在房屋表里添加
        int hou=aHouseMapping.setHouse(map);


        //判断
        if (img>0&&lable>0&&hou>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }




    /**
     * 查询我的房屋啊
     * @param map
     * @return
     */
    @Override
    public List<HouseLaIm> getMyHouse(Map map) {
        //从session 中获取经济人
        map.put("house_staffid",CusUtil.getStaff().getStaff_num());
        return aHouseMapping.getMyHouse(map);
    }

    /**
     * 经济人房屋数量
     * @param map
     * @return
     */
    @Override
    public int getMyhouseNum(Map map) {
        return aHouseMapping.getMyhouseNum(map);
    }

    /**
     * 房屋下架
     * @param map
     * @return
     */
    @Override
    public ResultUtil upMyHouseXiajia(Map map) {
        int a=aHouseMapping.upMyHouseXiajia(map);
        ResultUtil resultUtil=new ResultUtil();
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 房屋发布
     * @param map
     * @return
     */
    @Override
    public ResultUtil upMYHouse(Map map) {
        int aa=aHouseMapping.upMYHouse(map);
        ResultUtil resultUtil=new ResultUtil();
        if (aa>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 查询我的租客
     * @param map
     * @return
     */
    @Override
    public Map getpact(Map map) {
        return aHouseMapping.getpact(map);
    }

    /**
     * 预约人数+信息+分页=房源出租
     * @return
     */
    @Override
    public List<HouseLaIm> getLookMyHouse(Map map) {
        //从session 中获取经济人
        map.put("house_staffid",CusUtil.getStaff().getStaff_num());

        return aHouseMapping.getLookMyHouse(map);
    }

    /**
     * 出租  获取房无的数量
     * @param map
     * @return
     */
    @Override
    public int getLookMyHouseNum(Map map) {
        return aHouseMapping.getLookMyHouseNum(map);
    }

    /**
     * 获取该房屋的预约信息
     * @param map
     * @return
     */
    @Override
    public List<Map> getUserLookHouse(Map map) {
        return aHouseMapping.getUserLookHouse(map);
    }

    /**
     * 修改看房
     * @param map
     * @return
     */
    @Override
    public ResultUtil updalookHouse(Map map) {
        int aa=aHouseMapping.updalookHouse(map);
        ResultUtil resultUtil=new ResultUtil();
        if (aa>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 添加看房时间和状态
     * @param map
     * @return
     */
    @Override
    public ResultUtil upLookHousestate(Map map) {
        ResultUtil resultUtil=new ResultUtil();
        int aa=aHouseMapping.upLookHousestate(map);
        String state=map.get("lookstate").toString();
        System.out.println(state);
        //合同
        if (state.equals("3")){
            Staff staff=CusUtil.getStaff();
            map.put("staff_name",staff.getStaff_name());
            map.put("staff_phone",staff.getStaff_phone());
            map.put("staff_card",staff.getStaff_card());
            try {
                aHouseMapping.setPact(map);
            } catch (Exception e) {
                e.printStackTrace();
                resultUtil.setCode(ISysConstants.OTHERTIPS);
                return resultUtil;
            }
        }



        if (aa>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 删除
     * @param map
     * @return
     */
    @Override
    public ResultUtil deLookHouse(Map map) {
        int aa=aHouseMapping.deLookHouse(map);
        ResultUtil resultUtil=new ResultUtil();
        if (aa>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 合同添加时间
     * @param map
     * @return
     */
    @Override
    public ResultUtil upPact(Map map) {
        int aa=aHouseMapping.upPact(map);
        ResultUtil resultUtil=new ResultUtil();
        if (aa>0){
            aHouseMapping.upHousestate(map);
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

}
