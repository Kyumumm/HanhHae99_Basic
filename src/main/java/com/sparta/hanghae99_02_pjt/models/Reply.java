package com.sparta.hanghae99_02_pjt.models;

import com.sparta.hanghae99_02_pjt.Dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Reply extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long postid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String reply;

    @Column(nullable = false)
    private Long userId;

    public Reply(Long postid, String username, String reply) {
        this.postid = postid;
        this.username = username;
        this.reply = reply;
    }

    public Reply(ReplyRequestDto requestDto) {
        this.postid = requestDto.getPostid();
        this.username = requestDto.getUsername();
        this.reply = requestDto.getReply();
    }

    public Reply(ReplyRequestDto requestDto, Long userId) {
        this.postid = requestDto.getPostid();
        this.reply = requestDto.getReply();
        this.username = requestDto.getUsername();
        this.userId = userId;
    }
    public Reply(ReplyRequestDto requestDto, Long userId, String reply) {
        this.postid = requestDto.getPostid();
        this.reply = reply;
        this.username = requestDto.getUsername();
        this.userId = userId;
    }

    public void update(ReplyRequestDto requestDto) {
        this.reply = requestDto.getReply();
    }
}