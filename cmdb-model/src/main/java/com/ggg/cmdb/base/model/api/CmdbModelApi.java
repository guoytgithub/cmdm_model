package com.ggg.cmdb.base.model.api;

import com.ggg.cmdb.base.model.db.entity.CmdbModelEntity;
import com.ggg.cmdb.base.model.db.service.DbCmdbModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 模型维护接口，提供基本的增查能力。
 */
@RestController
@RequestMapping("api/v1/model")
@Slf4j
@RequiredArgsConstructor
public class CmdbModelApi {

    private final DbCmdbModelService dbCmdbModelService;

    /**
     * 新增或更新模型
     */
    @PostMapping
    public CmdbModelEntity save(@RequestBody CmdbModelEntity entity) {
        dbCmdbModelService.saveOrUpdateModel(entity);
        return entity;
    }

    /**
     * 根据ID查询模型
     */
    @GetMapping("/{id}")
    public CmdbModelEntity detail(@PathVariable String id) {
        return dbCmdbModelService.getById(id);
    }

    /**
     * 返回模型类型，体现多态行为
     */
    @GetMapping("/{id}/type")
    public String type(@PathVariable String id) {
        CmdbModelEntity entity = dbCmdbModelService.getById(id);
        return entity.getType();
    }
}
