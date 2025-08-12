package com.ggg.cmdb.base.model.db.dao;

import com.ggg.cmdb.base.model.db.entity.CmdbModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 配置模型表
 * 
 * @author guoyt
 * @email guoyitao@nantian.com.cn
 * @date 2023-06-13 14:47:15
 */
@Mapper
public interface DbCmdbModelDao extends CmdbBaseMapper<CmdbModelEntity> {

    @Select(" SELECT MAX( ECODE ) AS ECODE FROM cmdb_model WHERE EPARCODE = #{eparcode}")
    String queryMaxEcode(@Param("eparcode") String eparcode);

    @Select("  SELECT * FROM cmdb_model WHERE CM_MODEL_NAME = #{modelName}  AND IS_DELETE = '0' AND PARENT_ID in (SELECT CM_MODEL_ID FROM cmdb_model WHERE CM_MODEL_NAME = #{parentName} AND IS_DELETE ='0')")
    List<CmdbModelEntity> queryByParentNameAndName(@Param("parentName") String parentName, @Param("modelName") String modelName);
}
