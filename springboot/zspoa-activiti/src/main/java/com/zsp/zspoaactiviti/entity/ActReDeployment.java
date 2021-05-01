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
public class ActReDeployment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("NAME_")
    private String name;

    @TableField("CATEGORY_")
    private String category;

    @TableField("KEY_")
    private String key;

    @TableField("TENANT_ID_")
    private String tenantId;

    @TableField("DEPLOY_TIME_")
    private Date deployTime;

    @TableField("ENGINE_VERSION_")
    private String engineVersion;

    @TableField("VERSION_")
    private Integer version;

    @TableField("PROJECT_RELEASE_VERSION_")
    private String projectReleaseVersion;


}
