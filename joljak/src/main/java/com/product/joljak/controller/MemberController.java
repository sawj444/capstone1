package com.product.joljak.controller;

import com.product.joljak.entity.Member;
import com.product.joljak.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private Member member;

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "signup";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }
//        member = new Member(member.setLoginId(),member.setPassword(),member.getNickname(),member.getEmail());
//        member.setLoginId();
//        member.setPassword();
//        member.setNickname();
//        member.setEmail();
//          memberRepository.save();
        return "redirect:/";
    }
}