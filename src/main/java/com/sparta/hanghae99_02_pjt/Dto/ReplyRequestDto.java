package com.sparta.hanghae99_02_pjt.Dto;


import lombok.Getter;

@Getter
public class ReplyRequestDto {
    private Long postid;
    private String username;
    private String reply;
    private Long userId;
}
