package com.sparta.hanghae99_02_pjt.repository;

import com.sparta.hanghae99_02_pjt.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByModifiedAtDesc();
}
