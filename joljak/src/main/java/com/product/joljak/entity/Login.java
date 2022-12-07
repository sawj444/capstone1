package com.product.joljak.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Login {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

//    @NotEmpty
//    private String nickname;
//
//    @NotEmpty
//    private String email;

}
