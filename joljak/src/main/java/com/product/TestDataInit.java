package com.product;

import com.product.joljak.entity.Member;
import com.product.joljak.repository.BoardRepository;
import com.product.joljak.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private MemberRepository memberRepository;
    private BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setNickname("tester");
        member.setEmail("test@test.com");
        memberRepository.save(member);
    }
}
