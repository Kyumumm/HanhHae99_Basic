package com.sparta.hanghae99_02_pjt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.hanghae99_02_pjt.Dto.SignupRequestDto;
import com.sparta.hanghae99_02_pjt.service.KakaoService;
import com.sparta.hanghae99_02_pjt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;


    @Autowired
    public UserController(UserService userService, KakaoService kakaoService) {
        this.userService = userService;
        this.kakaoService = kakaoService;
    }
    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
        }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model){
        if (userService.registerUser(requestDto).equals("") ) {
            return "login";
        } else {
            model.addAttribute("errortext" ,userService.registerUser(requestDto));
            return  "signup";
        }

    }

    //카카오 로그인 시작점
    @GetMapping("/user/kakao/callback")
    public String kakaologin(@RequestParam String code) throws JsonProcessingException {
        kakaoService.kakaologin(code);
        //로그인 처리가 잘 됐으면 redirect 처리를 해줘야함
        return "redirect:/";
    }

}