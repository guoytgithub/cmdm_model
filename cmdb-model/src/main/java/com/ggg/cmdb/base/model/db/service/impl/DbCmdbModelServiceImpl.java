package com.ggg.cmdb.base.model.db.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ggg.cmdb.base.model.db.dao.DbCmdbModelDao;
import com.ggg.cmdb.base.model.db.entity.CmdbModelEntity;
import com.ggg.cmdb.base.model.db.service.DbCmdbModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("DbcmdbModelService")
@Slf4j
 public class DbCmdbModelServiceImpl extends ServiceImpl<DbCmdbModelDao, CmdbModelEntity> implements DbCmdbModelService {

    @Override
    public List<CmdbModelEntity> queryByPublicModelId(String publicModelId) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(CmdbModelEntity::getPublicModelId, publicModelId);
        wrapper.and(qw -> qw.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete));
        return this.baseMapper.selectList(wrapper);
    }


    @Override
     public CmdbModelEntity getById(String id) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getCmModelId, id);
        wrapper.and(qw -> qw.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete));
        CmdbModelEntity modelEntity = this.baseMapper.selectOne(wrapper);
        return modelEntity;
    }

    @Override
    public List<CmdbModelEntity> getByName(String name) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getCmModelName, name);
        wrapper.and(qw -> qw.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete));
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<CmdbModelEntity> getByNameLike(String name) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(CmdbModelEntity::getCmModelName, name);
        wrapper.and(qw -> qw.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete));
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<CmdbModelEntity> listByParentId(String parentId){
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getParentId, parentId);
        wrapper.eq(CmdbModelEntity::getIsDelete, "0");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
     public List<CmdbModelEntity> listAll() {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete);
        wrapper.orderByAsc(CmdbModelEntity::getEcode);
        List<CmdbModelEntity> list = this.list(wrapper);

        List<CmdbModelEntity> result = new ArrayList<>();

        if(ObjectUtil.isNotEmpty(list)){
            result = list.stream()
                    .filter(f->ObjectUtil.isNotEmpty(f.getEcode())).collect(Collectors.toList());
//          fixbug: ecode重复，model回被过滤掉
//                    .collect(Collectors.groupingBy(CmdbModelEntity::getEcode))
//                    .forEach((k, v) -> result.add(v.get(0)));
        }

        return result;

    }

    @Override
    public CmdbModelEntity getByEcode(String ecode) {
        List<CmdbModelEntity> modelList = listAll();
        for (CmdbModelEntity model : modelList) {
            if(model.getEcode().equals(ecode)){
                return model;
            }
        }
        return null;
    }

    @Override
    public List<CmdbModelEntity> getByModelIdAndParentId(String parentId,String modelId) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getParentId, parentId);
        wrapper.eq(CmdbModelEntity::getCmModelId, modelId);
        wrapper.eq(CmdbModelEntity::getIsDelete, "0");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public CmdbModelEntity getByIdName(String id,String cmModelName) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getCmModelId, id);
        wrapper.like(ObjectUtil.isNotEmpty(cmModelName),CmdbModelEntity::getCmModelName, cmModelName);
        wrapper.and(qw -> qw.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete));
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<CmdbModelEntity> listByParentIds(List<String> parentIds) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(CmdbModelEntity::getParentId, parentIds);
        wrapper.eq(CmdbModelEntity::getIsDelete, "0");
        return this.baseMapper.selectList(wrapper);    }

    @Override
    public List<CmdbModelEntity> getByIds(List<String> modelIds) {
        if(modelIds==null || modelIds.size()==0){
            return null;
        }
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(CmdbModelEntity::getCmModelId, modelIds);
        wrapper.eq(CmdbModelEntity::getIsDelete, "0");
        return this.baseMapper.selectList(wrapper);

    }

    @Override
    public List<CmdbModelEntity> listLikeEcode(String ecode) {
        LambdaQueryWrapper<CmdbModelEntity> querywrapper = new LambdaQueryWrapper<>();
        querywrapper.likeRight(CmdbModelEntity::getEcode,ecode);
        querywrapper.and(e -> e.eq(CmdbModelEntity::getIsDelete,"0").or().eq(CmdbModelEntity::getIsDelete,null).or().eq(CmdbModelEntity::getIsDelete,""));
        List<CmdbModelEntity> list = this.list(querywrapper);
        return list;
    }

    @Override
    public CmdbModelEntity  getParentIdByModelId(String modelId) {
        LambdaQueryWrapper<CmdbModelEntity> querywrapper = new LambdaQueryWrapper<>();
        querywrapper.eq(CmdbModelEntity::getCmModelId,modelId);
        querywrapper.and(e -> e.eq(CmdbModelEntity::getIsDelete,"0").or().eq(CmdbModelEntity::getIsDelete,null).or().eq(CmdbModelEntity::getIsDelete,""));
        CmdbModelEntity cmdbModelEntity = baseMapper.selectOne(querywrapper);
        return cmdbModelEntity;
    }


    @Override
    public List<CmdbModelEntity>  getSonModelById(String modelId) {
        LambdaQueryWrapper<CmdbModelEntity> querywrapper = new LambdaQueryWrapper<>();
        querywrapper.eq(CmdbModelEntity::getParentId,modelId);
        querywrapper.and(e -> e.eq(CmdbModelEntity::getIsDelete,"0").or().eq(CmdbModelEntity::getIsDelete,null).or().eq(CmdbModelEntity::getIsDelete,""));
        return this.list(querywrapper);
    }

    @Override
    public void saveOrUpdateModel(CmdbModelEntity cmdbModelEntity) {
        this.saveOrUpdate(cmdbModelEntity);
    }

    @Override
     public void updateModelById(CmdbModelEntity cmdbModelEntity) {
        this.updateById(cmdbModelEntity);
    }

    @Override
     public boolean updateModelBatchById(List<CmdbModelEntity> updateCmdbModelEntities) {
        return this.updateBatchById(updateCmdbModelEntities);
    }

    @Override
    public List<String> getAllChildModelIds() {
        LambdaQueryWrapper<CmdbModelEntity> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.select(CmdbModelEntity::getCmModelId);
        queryWrapper.eq(CmdbModelEntity::getSonModelFlag, "1");
        queryWrapper.ne(CmdbModelEntity::getIsDelete, "1");
        List<CmdbModelEntity> list = this.list(queryWrapper);
        return list.stream().map(CmdbModelEntity::getCmModelId).collect(Collectors.toList());
    }

    @Override
    public List<CmdbModelEntity> getByEparcode(String eparcode) {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getEparcode,eparcode);
        wrapper.and(qw -> qw.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete));
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<CmdbModelEntity> getByParentIdAndName(String parentId, String modelName) {
        LambdaQueryWrapper<CmdbModelEntity> querywrapper = new LambdaQueryWrapper<>();
        querywrapper.eq(CmdbModelEntity::getParentId,parentId);
        querywrapper.eq(CmdbModelEntity::getCmModelName, modelName);
        querywrapper.and(e -> e.eq(CmdbModelEntity::getIsDelete,"0").or().eq(CmdbModelEntity::getIsDelete,null).or().eq(CmdbModelEntity::getIsDelete,""));
        return this.list(querywrapper);
    }

    @Override
    public String queryMaxEcode(String eparcode) {
        return this.baseMapper.queryMaxEcode(eparcode);
    }

    @Override
    public List<CmdbModelEntity> getAssetModels() {
        LambdaQueryWrapper<CmdbModelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmdbModelEntity::getAssetModelFlag,"1");
        wrapper.and(qw -> qw.eq(CmdbModelEntity::getIsDelete, "0").or().isNull(CmdbModelEntity::getIsDelete));
        return this.baseMapper.selectList(wrapper);
    }

    @Override
     public void clearNameMapping(String modelId) {
        LambdaUpdateWrapper<CmdbModelEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CmdbModelEntity::getCmModelId, modelId);
        wrapper.set(CmdbModelEntity::getNameMapping, null);
        this.update(wrapper);
    }

    @Override
    public List<CmdbModelEntity> getByParentNameAndName(String parentName, String modelName) {
        return this.baseMapper.queryByParentNameAndName(parentName, modelName);
    }

    @Override
    public List<CmdbModelEntity>  cmdb_asset_detail_selectModelFlow(List<String> modelIdList) {
        LambdaQueryWrapper<CmdbModelEntity> querywrapper = new LambdaQueryWrapper<>();
        querywrapper.in(CmdbModelEntity::getCmModelId,modelIdList);
        querywrapper.eq(CmdbModelEntity::getAssetModelFlag,"1");
        querywrapper.and(e -> e.eq(CmdbModelEntity::getIsDelete,"0").or().eq(CmdbModelEntity::getIsDelete,null).or().eq(CmdbModelEntity::getIsDelete,""));
        return this.list(querywrapper);
    }

    @Override
    public List<CmdbModelEntity> listByGroupLevel(String groupLevel) {
        LambdaQueryWrapper<CmdbModelEntity> querywrapper = new LambdaQueryWrapper<>();
        querywrapper.isNotNull(CmdbModelEntity::getGroupLevel);
        if(StrUtil.isNotEmpty(groupLevel)){
            querywrapper.eq(CmdbModelEntity::getGroupLevel,groupLevel);
        }
        return this.list(querywrapper);
    }

    @Override
    public List<CmdbModelEntity> getByLikeEcode(String ecode) {
        LambdaQueryWrapper<CmdbModelEntity> querywrapper = new LambdaQueryWrapper<>();
        querywrapper.likeRight(CmdbModelEntity::getEcode,ecode);
        querywrapper.eq(CmdbModelEntity::getIsDelete,"0");
        return this.list(querywrapper);
    }
}
