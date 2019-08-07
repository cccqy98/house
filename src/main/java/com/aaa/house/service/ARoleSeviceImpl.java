package com.aaa.house.service;


import com.aaa.house.dao.ARoleMapping;

import com.aaa.house.entity.RolePerm;
import com.aaa.house.entity.TreeRole;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FileName: AMenuSeviceImpl
 * Author:   曹康
 * Date:     2019/7/28 16:13
 * Description: 角色
 */
@Service
@Transactional//事务
public class ARoleSeviceImpl implements ARoleService{
    @Autowired
    private ARoleMapping aRoleMapping;


    /**
     * 查询角色
     * @return
     */
    @Override
    public ResultUtil getRoleAll() {

        //查询得到数据
        List<TreeRole> powers = aRoleMapping.getRoleAll();
        //定义临时集合，存放并拼装后台数据
        List<TreeRole> tmpData=new ArrayList<>();

        //判断是否查到值
        if (powers!=null&&powers.size()>0){
            //遍历值
            for (TreeRole power : powers) {
                //判断是否是一级节点
                if (power.getRoleid()==0){
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
    public ResultUtil inRole(Map map) {
        //获取session
        map.put("add_staff",CusUtil.getStaff().getStaff_num());


        //添加角色
        int a=aRoleMapping.inRole(map);


        List<RolePerm> list=new ArrayList<>();
        //获取权限
        String url=map.get("url").toString();
        String[] pam=url.split(",");
        for (String s : pam) {
            RolePerm rolePerm=new RolePerm();
            rolePerm.setPid(s);
            rolePerm.setRid(map.get("role_id").toString());
            list.add(rolePerm);//添加
        }
        //添加角色权限中间表
        int rp= aRoleMapping.setRolePerm(list);


        ResultUtil resultUtil=new ResultUtil();
        if (a>0&&rp>0){
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
    public ResultUtil upRole(Map map) {
        //获取角色id
        String rid=map.get("role_id").toString();
        //删除中间表
        int deletePemRole=aRoleMapping.deletePemRole(Integer.parseInt(rid));



        List<RolePerm> list=new ArrayList<>();
        //获取权限
        String url=map.get("url").toString();
        String[] pam=url.split(",");
        for (String s : pam) {
            RolePerm rolePerm=new RolePerm();
            rolePerm.setPid(s);
            rolePerm.setRid(rid);
            list.add(rolePerm);//添加
        }
        //添加角色权限中间表
        int rp= aRoleMapping.setRolePerm(list);
        //修改
        int a=aRoleMapping.upRole(map);
        ResultUtil resultUtil=new ResultUtil();
        if (a>0&&rp>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 删除权限
     * @param role_id
     * @return
     */
    @Override
    public ResultUtil deleteRole(int role_id) {
        int a=aRoleMapping.deleteRole(role_id);
        aRoleMapping.deletePemRole(role_id);
        ResultUtil resultUtil=new ResultUtil();
        if (a>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }



    /**
     * 查询所对应的权限
     * @param rid
     * @return
     */
    @Override
    public List<Integer> getPowersByRoleId(int rid) {
        return aRoleMapping.getPowersByRoleId(rid);
    }

    /**
     * 递归绑定孩子节点
     * @param tree
     * @param powers
     */
    private void bingClidren(TreeRole tree,List<TreeRole> powers){
       //创建子节点 用于保存
        List<TreeRole> temChildrens=null;
        //遍历所有节点
        for (TreeRole power : powers) {
            //判断 如果父节点id等于当前节点pid,那就是该节点父节点
            if (tree.getRole_id()==power.getRoleid()){
                power.setRole_name(tree.getLabel());
                //获取所有子节点
                List<TreeRole> children = tree.getChildren();
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
