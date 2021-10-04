package com.sparta.hanghae99_02_pjt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Blog extends Timestamped{
    //제목, 작성자명, 작성 날짜, 작성 내용
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String comment;

    public Blog(BlogRequestDto blogRequestDto){
        this.title = blogRequestDto.getTitle();
        this.name = blogRequestDto.getName();
        this.comment = blogRequestDto.getComment();
    }


}
