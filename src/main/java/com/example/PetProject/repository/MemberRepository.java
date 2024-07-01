package com.example.PetProject.repository;

import com.example.PetProject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByEmailAndPw(String email, String password);
//    Member findByEmail(String email);
}