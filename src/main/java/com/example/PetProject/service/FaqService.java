package com.example.PetProject.service;

import com.example.PetProject.domain.FAQ;
import com.example.PetProject.repository.FaqRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    // 페이지에 목록 조회
    @Transactional
    public List<FAQ> getList() {
        return faqRepository.findAll();
    }

    // 선택된 체크박스 가져와서 delete
    @Transactional
    public int deleteFAQs(List<Integer> faqIds) {
        int delResult = 0;
        try {
            List<FAQ> faqsToDelete = faqRepository.findAllById(faqIds);
            faqRepository.deleteAll(faqsToDelete);
            delResult=1;
        }catch (Exception e){
                delResult=0;
        } finally {
            return delResult;
        }
    }

    @Transactional
    public Optional<FAQ> detailFAQs(Integer faqId){
        Optional<FAQ> faqOptional = faqRepository.findById(faqId);
        return faqOptional;
    }

    @Transactional
    public int updateFAQs(FAQ faq_updateOp){

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentTime.format(formatter);

        FAQ faqToUpdate = faqRepository.findById(faq_updateOp.getFaq_id()).orElse(null);
        System.out.println(faq_updateOp);
        int resultCode = 0;
        if (faqToUpdate != null) {
            // 엔터티를 찾았다면 UpdateData의 값으로 엔터티를 업데이트합니다.
            faqToUpdate.setMember_id(faq_updateOp.getMember_id());
            faqToUpdate.setAdd_date(faq_updateOp.getAdd_date());
            faqToUpdate.setChange_date(formattedDateTime);
            faqToUpdate.setTitle(faq_updateOp.getTitle());
            faqToUpdate.setContent(faq_updateOp.getContent());

            // FAQ 엔터티를 저장하여 데이터베이스를 업데이트합니다.
            faqRepository.save(faqToUpdate);
            resultCode = 0;
        } else {
            // 해당 id를 가진 엔터티가 없는 경우에 대한 처리
            // 예를 들어 예외를 던지거나, 로그를 남기거나, 메시지를 반환할 수 있습니다.
            resultCode = 1;

        }
        return resultCode;
    }

    @Transactional
    public int insertFAQs(FAQ faq_insertOp){

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentTime.format(formatter);

        faq_insertOp.setAdd_date(formattedDateTime);

        FAQ insertedFaq = faqRepository.save(faq_insertOp);
        int resultCode = 0;
        if (insertedFaq != null) {
            resultCode = 0;
        } else {
            resultCode = 1;
        }
        return resultCode;
    }
}