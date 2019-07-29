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
     * 查询全部报修信息
     * @return
     */
    List<Maintain> queryAll();

    /**
     * 录入报修信息
     * @return
     */
    int AddMaintain(Maintain maintain);
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
     * 删除已维修和审核未通过报修信息
     * @return
     */
   int delete();
    /**
     *修改维修员工和不维修的原因
     * @return
     */
   int updateMa(Maintain maintain);
}
