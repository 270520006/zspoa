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
public class ActHiActinst implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("PROC_DEF_ID_")
    private String procDefId;

    @TableField("PROC_INST_ID_")
    private String procInstId;

    @TableField("EXECUTION_ID_")
    private String executionId;

    @TableField("ACT_ID_")
    private String actId;

    @TableField("TASK_ID_")
    private String taskId;

    @TableField("CALL_PROC_INST_ID_")
    private String callProcInstId;

    @TableField("ACT_NAME_")
    private String actName;

    @TableField("ACT_TYPE_")
    private String actType;

    @TableField("ASSIGNEE_")
    private String assignee;

    @TableField("START_TIME_")
    private Date startTime;

    @TableField("END_TIME_")
    private Date endTime;

    @TableField("DURATION_")
    private Long duration;

    @TableField("DELETE_REASON_")
    private String deleteReason;

    @TableField("TENANT_ID_")
    private String tenantId;


}
