package com.zsp.zspoamember.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "member_activity")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MemberActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id",columnDefinition = "bigint COMMENT '这是用户id'")
    private Long userId;
    @NotNull
    @Column(name = "activity_id",columnDefinition = "bigint  COMMENT '这是活动id'")
    private Long  activityId;
    @Column(name = "activity_status",columnDefinition = "varchar(40) COMMENT '当前的流程进行到哪'")
    private String  activityStatus="暂未开始";
    @Column(name = "activity_finished",columnDefinition = "int(2) COMMENT '当前流程完成状态'")
    private int  activityFinished=0;


}
