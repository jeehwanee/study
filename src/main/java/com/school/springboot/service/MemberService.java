package com.school.springboot.service;

import com.school.springboot.domain.Role;
import com.school.springboot.model.Member;
import com.school.springboot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder encoder) {
        this.memberRepository = memberRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void 회원가입(Member member) {
        String rawPassword = member.getPassword(); //패스워드 원문
        String encPassword = encoder.encode(rawPassword); //해시된 패스워드
        member.setPassword(encPassword); //패스워드 db에 저장
        member.setRole(Role.MEMBER); //역할 db에 저장
        memberRepository.save(member);
    }

}
