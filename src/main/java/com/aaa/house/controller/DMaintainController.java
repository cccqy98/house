package com.aaa.house.controller;

import com.aaa.house.entity.Maintain;
import com.aaa.house.service.DMaintainService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName DMaintainController
 * @Author 龚志博
 * @Date 2019/7/28 13:23
 * @Version 1.0
 */
@RestController
public class DMaintainController {
    @Autowired
    private DMaintainService service;

    /**
     * 根据报修状态查询
     * @param map
     * @return
     */
    @RequestMapping("queryStatic")
    public Object queryStatic(@RequestBody Map map){

        Map mapResult = new HashMap();
        mapResult.put("mainList",service.queryStatic(map));
        mapResult.put("total",service.queryPageCount(map));
        return mapResult;
    }

   /**
     * 根据审核状态查询
     * @return
     */
    @RequestMapping("queryAudit")
    public Object queryAudit(@RequestBody Map map){
        Map mapResult = new HashMap();
        mapResult.put("auditList",service.queryAudit(map));
        mapResult.put("total",service.queryPageCount1(map));
        return mapResult;
    }


    /**
     * 修改报修单
     * @param maintain
     * @return
     */
    @RequestMapping("updateMa")
    public Object updateMa(@RequestBody  Maintain maintain){
        return service.updateMa(maintain);
    }

    /**
     * 审核驳回
     * @param maintain
     * @return
     */
    @RequestMapping("updateAu")
    public Object updateAu(@RequestBody  Maintain maintain){
        return service.updateAu(maintain);
    }

    @RequestMapping("list")
    public String list() {
        return "list";
    }

    /**
     * 报修状态
     * @param map
     * @return
     */
    @RequestMapping("upMaintain")
    public ResultUtil upMaintain(@RequestBody Map map){
        int i = service.upMaintain(map);
        //交互类
        ResultUtil resultUtil1=new ResultUtil();
        if (i>0){
            resultUtil1.setCode(ISysConstants.SUCCESSCODE);
            resultUtil1.setObject(i);
            return resultUtil1;
        }
        resultUtil1.setCode(ISysConstants.OTHERTIPS);
        return resultUtil1;
    }

    /**
     * 审核状态
     * @param map
     * @return
     */
    @RequestMapping("upAudit")
    public ResultUtil upAudit(@RequestBody Map map){
        int i = service.upAudit(map);
        //交互类
        ResultUtil resultUtil1=new ResultUtil();
        if (i>0){
            resultUtil1.setCode(ISysConstants.SUCCESSCODE);
            resultUtil1.setObject(i);
            return resultUtil1;
        }
        resultUtil1.setCode(ISysConstants.OTHERTIPS);
        return resultUtil1;
    }

    /**
     * 审核
     * @return
     */
    @RequestMapping("queryCode")
    public List<Map> queryCode(){
        return service.queryCode();
    }

    /**
     * 报修
     * @return
     */
    @RequestMapping("queryCode1")
    public List<Map> queryCode1(){
        return service.queryCode1();
    }
}
