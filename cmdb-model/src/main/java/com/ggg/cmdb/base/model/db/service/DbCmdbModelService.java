package com.ggg.cmdb.base.model.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ggg.cmdb.base.model.db.entity.CmdbModelEntity;

import java.util.List;

/**
 * 配置模型表
 *
 * @author guoyt
 * @email guoyitao@nantian.com.cn
 * @date 2023-06-13 14:47:15
 */
public interface DbCmdbModelService extends IService<CmdbModelEntity> {

    List<CmdbModelEntity> queryByPublicModelId(String publicModelId);

    CmdbModelEntity getById(String id);

    List<CmdbModelEntity> getByName(String name);

    List<CmdbModelEntity> getByNameLike(String name);

    List<CmdbModelEntity> listByParentId(String parentId);

    List<CmdbModelEntity> listAll();

    CmdbModelEntity getByEcode(String ecode);

    List<CmdbModelEntity> getByModelIdAndParentId(String parentId,String modelId);

    CmdbModelEntity getByIdName(String id, String cmModelName);


    List<CmdbModelEntity> listByParentIds(List<String> parentIds);


    List<CmdbModelEntity> getByIds(List<String> modelIds);

    List<CmdbModelEntity> listLikeEcode(String ecode);

    CmdbModelEntity  getParentIdByModelId(String modelId);

    List<CmdbModelEntity> getSonModelById(String cmModelId);

    void saveOrUpdateModel(CmdbModelEntity cmdbModelEntity);

    void updateModelById(CmdbModelEntity extendPublicModel);


    boolean updateModelBatchById(List<CmdbModelEntity> updateCmdbModelEntities);

    List<String> getAllChildModelIds();

    List<CmdbModelEntity> getByEparcode(String eparcode);

    List<CmdbModelEntity> getByParentIdAndName(String parentId, String modelName);

    String queryMaxEcode(String eparcode);

    List<CmdbModelEntity> getAssetModels();

    void clearNameMapping(String modelId);

    List<CmdbModelEntity> getByParentNameAndName(String parentName, String modelName);

    List<CmdbModelEntity>  cmdb_asset_detail_selectModelFlow(List<String> modelIdList);

    List<CmdbModelEntity> listByGroupLevel(String groupLevel);

    List<CmdbModelEntity>  getByLikeEcode(String ecode);
}

