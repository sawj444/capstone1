package com.product.joljak.repository;

import com.product.joljak.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.reflection.XMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Member member){
        em.persist(member);
    }

    private static Map<Long, Member> store = new HashMap<>();

    private static long sequence = 0L;

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    private List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
