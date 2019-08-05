package com.aaa.house.service;

import com.aaa.house.dao.AHouseMapping;
import com.aaa.house.entity.*;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AHouseServiceImpl implements AHouseService{

    @Autowired
    private AHouseMapping aHouseMapping;

    /**
     * 查询客户发布未联系的房屋
     * @return
     */
    @Override
    public List<HouseUser> getHouseUserAll(Map map) {
        return aHouseMapping.getHouseUserAll(map);
    }

    /**
     * 查询客户发布未联系的房屋数量
     * @param map
     * @return
     */
    @Override
    public int getHouseUserquall(Map map) {
        return aHouseMapping.getHouseUserquall(map);
    }


    /**
     * 联系房源
     * @return
     */
    @Override
    public int upHouseUser(Map map) {
        //获取当前登陆的经济人工号
        Staff staff = CusUtil.getStaff();
        map.put("uh_staff",staff.getStaff_num());
        return aHouseMapping.upHouseUser(map);
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
    public List<HouseLaIm> getHouse(Map map) {
        return aHouseMapping.getHouse(map);
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
    public int setHouse(Map map) {
        return aHouseMapping.setHouse(map);
    }

    /**
     * 添加房屋标签
     * @param houseLables
     * @return
     */
    @Override
    public int setHouseLable(List<HouseLable> houseLables) {
        return aHouseMapping.setHouseLable(houseLables);
    }

    /**
     * 添加房屋组图
     * @param list
     * @return
     */
    @Override
    public int setHouseImg(List<HouseImg> list) {
        return aHouseMapping.setHouseImg(list);
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
        String state=map.get("look_state").toString();
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
