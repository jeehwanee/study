package com.school.springboot.repository;

import com.school.springboot.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    //Optional<MemberEntity> findByEmail(String userEmail);
}