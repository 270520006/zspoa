package com.zsp.zspoamember.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "member")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Column(name = "user_name",columnDefinition = "varchar(40) COMMENT '这是用户账号'")
    private String userName;

    @NotNull
    @Column(name = "user_password",columnDefinition = "varchar(40) COMMENT '这是用户密码'")
    private String userPassword;


    @Column(name = "user_phone",columnDefinition = "int(40) COMMENT '用户电话'")
    private int  userPhone=10086;


    @Column(name = "user_email",columnDefinition = "varchar(40) COMMENT '这是用户邮箱'")
    private String  userEmail;

    @NotNull
    @Column(name = "user_level",columnDefinition = "int(2) COMMENT '这是用户性别'")
    private int  userLevel=1;


    @Column(name = "user_gender",columnDefinition = "int(2) COMMENT '这是用户性别'")
    private int  userGender=0;







}
