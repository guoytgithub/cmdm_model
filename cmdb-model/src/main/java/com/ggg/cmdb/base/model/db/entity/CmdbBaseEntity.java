package com.ggg.cmdb.base.model.db.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象基础模型，演示封装与抽象。
 * 具体模型应继承该类并实现 {@link #getType()} 方法。
 */
@Getter
@Setter
public abstract class CmdbBaseEntity implements Model {

    /**
     * 基础模型ID，采用私有字段体现封装性
     */
    private String baseId;

    /**
     * 获取模型类型，由子类实现体现多态
     *
     * @return 模型类型
     */
    @Override
    public abstract String getType();
}
