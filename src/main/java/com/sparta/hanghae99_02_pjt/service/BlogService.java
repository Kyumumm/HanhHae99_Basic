package com.sparta.hanghae99_02_pjt.service;

import com.sparta.hanghae99_02_pjt.Dto.BlogRequestDto;
import com.sparta.hanghae99_02_pjt.models.Blog;
import com.sparta.hanghae99_02_pjt.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public Long update(Long id, BlogRequestDto blogRequestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        blog.update(blogRequestDto);
        return id;
    }
}
