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
public class ActHiProcinst implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("PROC_INST_ID_")
    private String procInstId;

    @TableField("BUSINESS_KEY_")
    private String businessKey;

    @TableField("PROC_DEF_ID_")
    private String procDefId;

    @TableField("START_TIME_")
    private Date startTime;

    @TableField("END_TIME_")
    private Date endTime;

    @TableField("DURATION_")
    private Long duration;

    @TableField("START_USER_ID_")
    private String startUserId;

    @TableField("START_ACT_ID_")
    private String startActId;

    @TableField("END_ACT_ID_")
    private String endActId;

    @TableField("SUPER_PROCESS_INSTANCE_ID_")
    private String superProcessInstanceId;

    @TableField("DELETE_REASON_")
    private String deleteReason;

    @TableField("TENANT_ID_")
    private String tenantId;

    @TableField("NAME_")
    private String name;


}
