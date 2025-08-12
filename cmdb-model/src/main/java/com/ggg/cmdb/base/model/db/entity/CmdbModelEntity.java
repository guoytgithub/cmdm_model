package com.ggg.cmdb.base.model.db.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 配置模型表
 *
 * @author guoyt
 * @email guoyitao@nantian.com.cn
 * @date 2023-06-09 18:34:54
 */
@Data
@TableName("cmdb_model")
public class CmdbModelEntity extends com.ggg.cmdb.base.model.db.entity.CmdbBaseEntity {
    /**
     * 模型ID
     */
    @TableId
    @TableField("CM_MODEL_ID")
    private String cmModelId;
    /**
     * 模型名称
     */
    @TableField("CM_MODEL_NAME")
    private String cmModelName;
    /**
     * 模型说明
     */
    @TableField("CM_MODEL_DESC")
    private String cmModelDesc;
    /**
     * 父类模型ID
     */
    @TableField("PARENT_ID")
    private String parentId;
    /**
     * 是否继承父类属性，1-继承；0-不继承
     */
    @TableField("IS_EXTEND_PARENT_ATTR")
    private String isExtendParentAttr;
    /**
     * 模型树显示，0-否；1-是
     */
    @TableField("IS_TREE_VIEW")
    private String isTreeView;
    /**
     * 模型树ID
     */
    @TableField("ECODE")
    private String ecode;
    /**
     * 父类树ID
     */
    @TableField("EPARCODE")
    private String eparcode;
    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private String userId;
    /**
     * 最后更新时间
     */
    @TableField("LAST_UPDATE_TIME")
    private Date lastUpdateTime;
    /**
     * 模型分类
     */
    @TableField("ECATEGORY")
    private String ecategory;
    /**
     *
     */
    @TableField("ICON")
    private String icon;
    /**
     * 逻辑删除标识 1-被删除 0-未删除
     */
    @TableField("IS_DELETE")
    private String isDelete;
    /**
     * 创建人
     */
    @TableField("CREATE_USER")
    private String createUser;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 分组级别
     */
    @TableField("GROUP_LEVEL")
    private String groupLevel;
    /**
     * 模型名称映射
     */
    @TableField("NAME_MAPPING")
    private String nameMapping;
    /**
     * 内置模型 1-是  0-不是
     */
    @TableField("MODEL_SOURCE")
    private String modelSource;
    /**
     * 指标编码
     */
    @TableField("TARGET_CODE")
    private String targetCode;
    /**
     * 资产模型标识 1-是资产模型 0-不是
     */
    @TableField("ASSET_MODEL_FLAG")
    private String assetModelFlag;
    /**
     * 映射文件名
     */
    @TableField("FILE_NAME")
    private String fileName;
    /**
     * 显示名称是否使用模型名打头 0-使用 1-不使用
     */
    @TableField("SHOW_NAME_FLAG")
    private String showNameFlag;
    /**
     * 公共模型ID,多个以逗号分隔
     */
    @TableField("PUBLIC_MODEL_ID")
    private String publicModelId;
    /**
     * 名称实时同步开关  0-使用 1-不使用
     */
    @TableField("SHOW_NAME_SYNC_FLAG")
    private String showNameSyncFlag;
    /**
     * 子模型标识
     */
    @TableField("SON_MODEL_FLAG")
    private String sonModelFlag;
    /**
     * 监控模型树显示，0-否；1-是
     */
    @TableField("IS_MONITOR_TREE_VIEW")
    private String isMonitorTreeView;

    /**
     * 模型实例留存年限
     */
    @TableField("ITEM_SAVE_MONTH")
    private Integer itemSaveMonth;

    /**
     * 模型实例最大版本
     */
    @TableField("ITEM_MAX_VERSION")
    private Integer itemVersion;

    @TableField(exist = false)
    private String  uuid;

    @TableField(exist = false)
    private String parentName;

    /**
     * 是否是子模型
     * @return
     */
    public boolean modelIsSon() {
        if (ObjectUtil.isEmpty(this.sonModelFlag)) {
            return false;
        } else {
            return "1".equals(this.sonModelFlag);
        }
    }

    public boolean hasItemSaveMonth() {
        if (ObjectUtil.isEmpty(this.itemSaveMonth)) {
            return true;
        } else {
            return this.itemSaveMonth > 0;
        }
    }

    public boolean hasItemVersion() {
        if (ObjectUtil.isEmpty(this.itemVersion)) { //没有配置默认记录his
            return true;
        } else { //配置负数不记录his
            return this.itemVersion > 0;
        }
    }


    /**
     * 配置负数不记录历史版本，没有配置按照默认值
     * @return
     */
    public boolean hasSaveItemVersion() {
        return  this.hasItemSaveMonth() && this.hasItemVersion();
    }

    @TableField(exist = false)
    private String id;

    @TableField(exist = false)
    private String pid;

    @TableField(exist = false)
    private String name;
}
