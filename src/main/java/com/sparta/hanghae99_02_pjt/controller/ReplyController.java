package com.sparta.hanghae99_02_pjt.controller;

import com.sparta.hanghae99_02_pjt.Dto.ReplyRequestDto;
import com.sparta.hanghae99_02_pjt.models.Reply;
import com.sparta.hanghae99_02_pjt.repository.ReplyRepository;
import com.sparta.hanghae99_02_pjt.security.UserDetailsImpl;
import com.sparta.hanghae99_02_pjt.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;

    /// 게시글 id 로 댓글 조회
    @GetMapping("/api/reply/{postId}")
    public List<Reply> getReply(@PathVariable Long postId) {
        return replyService.getReply(postId);
    }

    @GetMapping("/api/reply")
    public List<Reply> getReply(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return replyService.getReply(userId);
    }

    // 댓글 작성
    @PostMapping("/api/reply")
    public boolean createReply(@RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        if (userDetails != null) {
            Long userId = userDetails.getUser().getId();
            replyService.createReply(requestDto, userId);
            return true;
        }
        return false;
    }

    // 댓글 삭제
    @DeleteMapping("/api/reply/{id}")
    public Long deleteReply(@PathVariable Long id) {
        replyRepository.deleteById(id);
        return id;
    }

    //댓글 수정
    @PutMapping("/api/reply/{id}")
    public Long updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto) {
        replyService.update(id, requestDto);
        return id;
    }
}
