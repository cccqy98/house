package com.aaa.house.service;

import com.aaa.house.dao.AMenuMapping;
import com.aaa.house.entity.Tree;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FileName: AMenuSeviceImpl
 * Author:   曹康
 * Date:     2019/7/28 16:13
 * Description: 权限菜单
 */
@Service
public class AMenuSeviceImpl implements AMenuService{
    @Autowired
    private AMenuMapping aMenuMapping;

    /**
     * 权限菜单
     * @return
     */
    @Override
    public ResultUtil getPowers() {
        //查询得到数据
        List<Tree> powers = aMenuMapping.getPowers(CusUtil.getStaff().getStaff_id());

        //定义临时集合，存放并拼装后台数据
        List<Tree> tmpData=new ArrayList<>();

        //判断是否查到值
        if (powers!=null&&powers.size()>0){
            //遍历值
            for (Tree power : powers) {
                //判断是否是一级节点
                if (power.getPer_id()==0){
                    //添加一级节点
                    tmpData.add(power);
                    //查找孩子节点 并绑定
                    bingClidren(power,powers);
                }
            }
        }
        //调用交互类
        ResultUtil resultUtil=new ResultUtil();
        //设置状态码
        resultUtil.setCode(ISysConstants.SUCCESSCODE);
        //数据
        resultUtil.setObject(tmpData);
        return resultUtil;
    }

    @Override
    public ResultUtil getPowersAll() {

        //查询得到数据
        List<Tree> powers = aMenuMapping.getPowersAll();
        //定义临时集合，存放并拼装后台数据
        List<Tree> tmpData=new ArrayList<>();

        //判断是否查到值
        if (powers!=null&&powers.size()>0){
            //遍历值
            for (Tree power : powers) {
                //判断是否是一级节点
                if (power.getPer_id()==0){
                    //添加一级节点
                    tmpData.add(power);
                    //查找孩子节点 并绑定
                    bingClidren(power,powers);
                }
            }
        }
        //调用交互类
        ResultUtil resultUtil=new ResultUtil();
        //设置状态码
        resultUtil.setCode(ISysConstants.SUCCESSCODE);
        //数据
        resultUtil.setObject(tmpData);
        return resultUtil;
    }

    /**
     * 添加权限
     * @return
     */
    @Override
    public ResultUtil inPowers(Map map) {
        //获取session
        map.put("per_staffName",CusUtil.getStaff().getStaff_num());

        int a=aMenuMapping.inPowers(map);
        ResultUtil resultUtil=new ResultUtil();
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 修改权限
     * @param map
     * @return
     */
    @Override
    public ResultUtil upPowers(Map map) {
        int a=aMenuMapping.upPowers(map);
        ResultUtil resultUtil=new ResultUtil();
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @Override
    public ResultUtil deletePowers(int id) {
        int a=aMenuMapping.deletePowers(id);
        ResultUtil resultUtil=new ResultUtil();
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 递归绑定孩子节点
     * @param tree
     * @param powers
     */
    private void bingClidren(Tree tree,List<Tree> powers){
       //创建子节点 用于保存
        List<Tree> temChildrens=null;
        //遍历所有节点
        for (Tree power : powers) {
            //判断 如果父节点id等于当前节点pid,那就是该节点父节点
            if (tree.getId()==power.getPer_id()){
                power.setPer_name(tree.getLabel());
                //获取所有子节点
                List<Tree> children = tree.getChildren();
                //判断子节点是否有孩子 没有就创建
                if (children==null||children.size()==0){
                    //创建
                    temChildrens=new ArrayList<>();
                    //添加子节点
                    temChildrens.add(power);
                    //在父节点中设置孩子节点
                    tree.setChildren(temChildrens);
                }else {
                    //存在孩子
                    children.add(power);
                }
                //递归 无线节点
                bingClidren(power,powers);

            }
        }
    }

}
