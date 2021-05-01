package com.zsp.zspoaactiviti.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zsp
 * @since 2021-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ActRuTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("REV_")
    private Integer rev;

    @TableField("EXECUTION_ID_")
    private String executionId;

    @TableField("PROC_INST_ID_")
    private String procInstId;

    @TableField("PROC_DEF_ID_")
    private String procDefId;

    @TableField("NAME_")
    private String name;

    @TableField("BUSINESS_KEY_")
    private String businessKey;

    @TableField("PARENT_TASK_ID_")
    private String parentTaskId;

    @TableField("DESCRIPTION_")
    private String description;

    @TableField("TASK_DEF_KEY_")
    private String taskDefKey;

    @TableField("OWNER_")
    private String owner;

    @TableField("ASSIGNEE_")
    private String assignee;

    @TableField("DELEGATION_")
    private String delegation;

    @TableField("PRIORITY_")
    private Integer priority;

    @TableField("CREATE_TIME_")
    private Date createTime;

    @TableField("DUE_DATE_")
    private Date dueDate;

    @TableField("CATEGORY_")
    private String category;

    @TableField("SUSPENSION_STATE_")
    private Integer suspensionState;

    @TableField("TENANT_ID_")
    private String tenantId;

    @TableField("FORM_KEY_")
    private String formKey;

    @TableField("CLAIM_TIME_")
    private Date claimTime;

    @TableField("APP_VERSION_")
    private Integer appVersion;


}
