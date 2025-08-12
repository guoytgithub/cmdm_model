package com.ggg.cmdb.base.model.api;

import com.ggg.cmdb.base.model.db.entity.CmdbModelEntity;
import com.ggg.cmdb.base.model.db.service.DbCmdbModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "模型维护", description = "提供模型的增查能力")
public class CmdbModelApi {

    private final DbCmdbModelService dbCmdbModelService;

    /**
     * 新增或更新模型
     */
    @PostMapping
    @Operation(summary = "新增或更新模型", description = "根据传入信息创建或更新模型")
    public CmdbModelEntity save(@RequestBody CmdbModelEntity entity) {
        dbCmdbModelService.saveOrUpdateModel(entity);
        return entity;
    }

    /**
     * 根据ID查询模型
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询模型")
    public CmdbModelEntity detail(@Parameter(description = "模型ID") @PathVariable String id) {
        return dbCmdbModelService.getById(id);
    }

    /**
     * 返回模型类型，体现多态行为
     */
    @GetMapping("/{id}/type")
    @Operation(summary = "返回模型类型", description = "体现多态行为")
    public String type(@Parameter(description = "模型ID") @PathVariable String id) {
        CmdbModelEntity entity = dbCmdbModelService.getById(id);
        return entity.getType();
    }
}
