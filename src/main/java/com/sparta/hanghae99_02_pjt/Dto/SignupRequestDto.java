package com.sparta.hanghae99_02_pjt.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String re_password;
    private String email;

    public SignupRequestDto(String username, String password, String re_password, String email) {
        this.username = username;
        this.password = password;
        this.re_password = re_password;
        this.email = email;
    }
}