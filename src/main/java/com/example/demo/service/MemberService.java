package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //JPA 사용하기 위해서 필요(JPA를 통한 데이터변경은 모두 트랜잭션 안에서 실행)
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복회원 검증
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("Already used");
                });
    }

    public List<Member> fineMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID){
        return memberRepository.findById(memberID);
    }
}
