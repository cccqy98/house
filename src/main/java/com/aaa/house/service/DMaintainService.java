package com.aaa.house.service;

import com.aaa.house.entity.Maintain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface DMaintainService {
    /**
     * 数量
     * @param map
     * @return
     */
    int queryPageCount1(Map map);
    /**
     * 修改审核
     * @param map
     * @return
     */
    int upAudit(Map map);
    /**
     * 修改维修后
     * @param map
     * @return
     */
    int upMaintain(Map map);
    /**
     * 根据报修状态查询，两表联查
     * @return
     */
    List<Map>  queryStatic(Map map);

    /**
     * 根据审核状态查询
     * @return
     */
   List<Map>  queryAudit(Map map);
    /**
     *修改维修员工和不维修的原因
     * @return
     */
   int updateMa(Maintain maintain);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    int queryPageCount(Map map);

    /**
     *审核
     * @return
     */
    List<Map> queryCode();

    /**
     * 报修
     * @return
     */
    List<Map> queryCode1();
}
