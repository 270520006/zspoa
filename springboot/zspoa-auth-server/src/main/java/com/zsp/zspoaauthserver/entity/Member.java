package com.zsp.zspoaauthserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class Member implements Serializable {
    private Long Id;
    private String userName;
    private String userPassword;
    private Long  userPhone;
    private String  userEmail;
    private int  userLevel;
    private int  userGender;
}
