package com.sparta.hanghae99_02_pjt.controller;

import com.sparta.hanghae99_02_pjt.models.Blog;
import com.sparta.hanghae99_02_pjt.repository.BlogRepository;
import com.sparta.hanghae99_02_pjt.Dto.BlogRequestDto;
import com.sparta.hanghae99_02_pjt.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogRestController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    // 게시물 전체 조회하기
    @GetMapping("/api/blogs")
    public List<Blog> getBlogs() {

        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 하나 조회하기
    @GetMapping("/api/blogs/{id}")
    public Blog findUserBlog(@PathVariable Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 존재하지 않습니다")
        );
        return blog;
    }

    //게시글 작성 코드
    @PostMapping("/api/blogs")
    public Blog creatBlog(@RequestBody BlogRequestDto blogRequestDto) {
        Blog blog = new Blog(blogRequestDto);
        return blogRepository.save(blog);
    }

    //게시글 수정
    @PutMapping("/api/blogs/{id}")
    public Long updateblog(@PathVariable Long id, @RequestBody BlogRequestDto blogRequestDto) {
        blogService.update(id, blogRequestDto);
        return id;
    }

    //게시글 삭제
    @DeleteMapping("/api/blogs/{id}")
    public Long deleteblog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
    }


}
