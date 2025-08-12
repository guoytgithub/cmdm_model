package com.ggg.cmdb.base.model.db.entity;

/**
 * 模型抽象，定义公共行为。
 */
public interface Model {
    /**
     * 返回模型类型。
     *
     * @return 模型类型标识
     */
    String getType();
}
