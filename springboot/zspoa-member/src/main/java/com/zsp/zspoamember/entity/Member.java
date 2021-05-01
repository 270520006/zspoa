package com.zsp.zspoamember.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "member")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "user_name",columnDefinition = "varchar(40) COMMENT '这是用户账号'")
    private String userName;

    @NotNull
    @Column(name = "user_password",columnDefinition = "varchar(40) COMMENT '这是用户密码'")
    private String userPassword;
}
