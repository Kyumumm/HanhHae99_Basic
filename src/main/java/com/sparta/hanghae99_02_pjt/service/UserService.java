package com.sparta.hanghae99_02_pjt.service;

import com.sparta.hanghae99_02_pjt.Dto.SignupRequestDto;
import com.sparta.hanghae99_02_pjt.models.User;
import com.sparta.hanghae99_02_pjt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String re_password = requestDto.getRe_password();
        String email = requestDto.getEmail();
        String error = "";

// 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);

        String pattern = "^[a-zA-Z0-9]*$";

        if (found.isPresent()) {
            return "중복된 id 입니다.";
        }

        if (username.length() < 3) {
            return "닉네임을 3자 이상 입력하세요";
        } else if (!Pattern.matches(pattern, username)) {
            return "알파벳 대소문자와 숫자로만 입력하세요";
        } else if (!password.equals(re_password)) {
            return "비밀번호가 일치하지 않습니다";
        } else if (password.length() < 4) {
            return "비밀번호를 4자 이상 입력하세요";
        } else if (password.contains(username)) {
            return "비밀번호에 닉네임을 포함할 수 없습니다.";
        } else if (email.length() < 1) {
            return "이메일을 입력하세요";
        } else if (email.contains("<") || email.contains(">") || email.contains("script")) {
            return "안돼요 하지마세요ㅠㅠ";
        }
        // 패스워드 인코딩
        password = passwordEncoder.encode(password);
        requestDto.setPassword(password);

        User user = new User(username, password, email);
        userRepository.save(user);
        return error;
    }

    public void isValidPassword(
            String password,
            String re_password,
            String username,
            String email,
            String usernaem
    ) {
        // 최소 8자, 최대 20자 상수 선언
        final int MIN = 4;
        final int MAX = 20;

        // 공백 문자 정규식
        final String BLANKPT = "(\\s)";

        //문자열에 공백 혹은 특수문자가 입력된 경우
        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
        if(!Pattern.matches(pattern, usernaem)){
            throw new IllegalArgumentException("아이디는 대문자, 소문자, 숫자로만 구성돼야 합니다 !!!");
        }

        // 정규식 검사객체
        Matcher matcher;

        if(password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에 아이디가 포함됩니다 !!");
        }


        if (!password.equals(re_password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다 !");
        }

        // 공백 체크
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("이메일을 입력해주쇼 !");
        }

        // 공백 체크
        if (password == null || "".equals(password)) {
            throw new IllegalArgumentException("비밀번호를 입력해주쇼 !");
        }

        // ASCII 문자 비교를 위한 UpperCase
        String tmpPw = password.toUpperCase();
        // 문자열 길이
        int strLen = tmpPw.length();

        // 글자 길이 체크
        if (strLen > 20 || strLen < 4) {
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다");
        }

        // 공백 체크
        matcher = Pattern.compile(BLANKPT).matcher(tmpPw);
        if (matcher.find()) {
            throw new IllegalArgumentException("비밀번호에 공백이 있습니다 !");
        }


    }
}



