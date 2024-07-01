package com.example.PetProject.service;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Member;
import com.example.PetProject.repository.FaqRepository;
import com.example.PetProject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MemberRepository;

    @Transactional
    public List<Member> getList() {
        return MemberRepository.findAll();
    }

    // 선택된 체크박스 가져와서 delete
    @Transactional
    public int deleteMember(List<Integer> faqIds) {
        int delResult = 0;
        try {
            List<Member> membersToDelete = MemberRepository.findAllById(faqIds);
            MemberRepository.deleteAll(membersToDelete);
            delResult=1;
        }catch (Exception e){
            delResult=0;
        } finally {
            return delResult;
        }
    }
    
    //관리자 등록
    @Transactional
    public int insertMember(Member mem_insertOp){

        mem_insertOp.setName(mem_insertOp.getName());
        mem_insertOp.setPw(mem_insertOp.getPw());
        mem_insertOp.setPhone(mem_insertOp.getPhone());
        mem_insertOp.setBirth(mem_insertOp.getBirth());
        mem_insertOp.setEmail(mem_insertOp.getEmail());
        mem_insertOp.setAdd_date(LocalDateTime.now().toString());
        mem_insertOp.setChange_date(null);
        mem_insertOp.setMem_type("admin");


        Member insertedMem = MemberRepository.save(mem_insertOp);
        int resultCode = 0;
        if (insertedMem != null) {
            resultCode = 0;
        } else {
            resultCode = 1;
        }
        return resultCode;
    }


}
