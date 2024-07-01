package com.example.PetProject.service;

import com.example.PetProject.domain.Member;
import com.example.PetProject.repository.MemberRepository;
import com.example.PetProject.security.JwtUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
//@Transactional
//@RequestMapping("/login")
public class LoginService {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;

    @Autowired
    private HttpSession httpSession;

//    @Transactional
    public ResponseEntity<?> login(Map<String, String> logindata) {
        String email = logindata.get("input_email");
        String password = logindata.get("input_pw");
        Member member = memberRepository.findByEmailAndPw(email, password);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        // 로그인에 성공한 경우, 세션에 회원 ID와 회원 유형을 저장
        httpSession.setAttribute("memberId", member.getMember_id());
        httpSession.setAttribute("memType", member.getMem_type());

        Member info = modelMapper.map(member, Member.class);

        String accessToken = jwtUtil.crateAccessToken(info);

        // 세션 데이터를 Map으로 생성
        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("memberId", member.getMember_id());
        sessionData.put("memType", member.getMem_type());

        // 토큰과 세션 데이터를 함께 담은 Map을 생성하여 반환
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("accessToken", accessToken);
        responseBody.put("sessionData", sessionData);

        return ResponseEntity.ok(responseBody);
    }

   /* private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member login(Map<String, String> logindata, String email, String password){
        *//*email = logindata.get("email");
        password = logindata.get("pw");
        Member member = memberRepository.findByEmailAndPw(email, password);
        return member;*//*
        email = logindata.get("email");
        password = logindata.get("pw");
        Member member = memberRepository.findByEmailAndPw(email, password);
        *//*if(!password.equals(member.getPw())){}*//*
        return member;
    }*/

}