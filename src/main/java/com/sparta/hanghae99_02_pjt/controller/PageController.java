package com.sparta.hanghae99_02_pjt.controller;

import com.sparta.hanghae99_02_pjt.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/detail.html")
    public String home(
            Model model, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        if(userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        return "detail";
    }
}