package com.aaa.house.controller;

import com.aaa.house.service.ARoleService;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * FileName: AMenuController
 * Author:   曹康
 * Date:     2019/7/28 20:28
 * Description: 角色
 */
@RestController
public class ARoleController {
    @Autowired
    private ARoleService aRoleService;
    /**
     * 全部权限角色
     * @return
     */
    @RequestMapping("getRoleAll")
    public ResultUtil getRoleAll(){
        return aRoleService.getRoleAll();
    }

    /**
     * 添加角色
     * @param map
     * @return
     */
    @RequestMapping("inRole")
    public ResultUtil inRole(@RequestBody Map map) {
        return aRoleService.inRole(map);
    }

    /**
     * 修改角色
     * @param map
     * @return
     */
    @RequestMapping("upRole")
    public ResultUtil upRole(@RequestBody Map map) {
        return aRoleService.upRole(map);
    }

    /**
     * 删除角色
     * @param role_id
     * @return
     */
    @RequestMapping("deleteRole")
    public ResultUtil deleteRole(int role_id) {
        return aRoleService.deleteRole(role_id);
    }

    /**
     * 查询角色权限
     * @param rid
     * @return
     */
    @RequestMapping("getPowersByRoleId")
    public List<Integer> getPowersByRoleId(int rid) {
        return aRoleService.getPowersByRoleId(rid);
    }

}
