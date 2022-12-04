package com.product.joljak.controller;

import com.product.joljak.entity.Login;
import com.product.joljak.entity.Member;
import com.product.joljak.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login/login")
    public String login(@ModelAttribute("login") Login loginen) {

        return "login/login";
    }

    @PostMapping("/login/login")
    public String login(@Valid @ModelAttribute Login loginen, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login/login";
        }
        Member loginMember = loginService.login(loginen.getLoginId(), loginen.getPassword());

        log.info("login? {}", loginMember);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/login";
        }

        return "redirect:/";

    }
}
