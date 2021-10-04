package com.sparta.hanghae99_02_pjt.controller;

import com.sparta.hanghae99_02_pjt.models.Blog;
import com.sparta.hanghae99_02_pjt.models.BlogRepository;
import com.sparta.hanghae99_02_pjt.models.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogRestController {
    private final BlogRepository blogRepository;

    // 게시물 전체 조회하기
    @GetMapping("/api/blogs")
    public List<Blog> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 하나 조회하기
    @GetMapping("/api/blogs/{id}")
    public Long findUserBlog(@PathVariable Long id) {
        blogRepository.findById(id);
        return id;
    }

    //게시글 작성 코드
    @PostMapping("/api/blogs")
    public Blog creatBlog(@RequestBody BlogRequestDto blogRequestDto) {
        Blog blog = new Blog(blogRequestDto);
        return blogRepository.save(blog);
    }


}
