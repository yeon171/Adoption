package com.example.PetProject.service;

import com.example.PetProject.domain.Answer;
import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Question;
import com.example.PetProject.repository.AnswerRepository;
import com.example.PetProject.repository.QuestionRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("questionService")
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    private HttpSession httpSession;

    @Transactional
    public List<Question> getList() {
        return questionRepository.findAll();
    }

    @Transactional
    public List<Question> getListById() {
        int member_id = (Integer) httpSession.getAttribute("memberId");
        return questionRepository.findByMemberId(member_id);
    }

    @Transactional
    public Optional<Question> getUserList(Integer memberId) {
        return questionRepository.findById(memberId);
    }

    @Transactional
    public List<Answer> get_answerList() {
        return answerRepository.findAll();
    }

    @Transactional
    public Optional<Answer> getUser_answerList(Integer qId) {
        return answerRepository.findById(qId);
    }

    // 선택된 체크박스 가져와서 delete
    @Transactional
    public int deleteQs(List<Integer> qIds) {
        int delResult = 0;
        try {
            List<Question> QuestionsToDelete = questionRepository.findAllById(qIds);
            questionRepository.deleteAll(QuestionsToDelete);
            delResult=1;
        }catch (Exception e){
            delResult=0;
        } finally {
            return delResult;
        }
    }

    @Transactional
    public int insertQs(Question question_insertOp){

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentTime.format(formatter);

        question_insertOp.setAdd_date(formattedDateTime);

        Question insertedQuestion= questionRepository.save(question_insertOp);
        int resultCode = 0;
        if (insertedQuestion != null) {
            resultCode = 0;
        } else {
            resultCode = 1;
        }
        return resultCode;
    }

   @Transactional
   public Optional<Question> detail_questionQs(Integer qId){
       Optional<Question> questionOptional = questionRepository.findById(qId);
       return questionOptional;
   }

    @Transactional
    public Optional<Answer> detail_answerQs(Integer qId){
        Optional<Answer> answerOptional = answerRepository.findByqId(qId);
        return answerOptional;
    }

   /* @Transactional
    public int updateUserQs(Question question_updateOp){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentTime.format(formatter);

        Question questionToUpdate = questionRepository.findById(question_updateOp.getQ_id()).orElse(null);
        int resultCode = 0;
        if (questionToUpdate != null) {

            // 엔터티를 찾았다면 UpdateData의 값으로 엔터티를 업데이트합니다.
            questionToUpdate.setQ_id(questionToUpdate.getQ_id());
            questionToUpdate.setMemberId(questionToUpdate.getMemberId());
            questionToUpdate.setAdd_date(questionToUpdate.getAdd_date());
            questionToUpdate.setChange_date(formattedDateTime);
            questionToUpdate.setContent(questionToUpdate.getContent());


            // FAQ 엔터티를 저장하여 데이터베이스를 업데이트합니다.
            questionRepository.save(questionToUpdate);

            resultCode = 0;
        }else {
            // 해당 id를 가진 엔터티가 없는 경우에 대한 처리
            // 예를 들어 예외를 던지거나, 로그를 남기거나, 메시지를 반환할 수 있습니다.
            resultCode = 1;

        }
        return resultCode;
    }*/

    @Transactional
    public int updateQs(Question question_updateOp, Answer answer_updateOp){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentTime.format(formatter);

        Question questionToUpdate = questionRepository.findById(question_updateOp.getQ_id()).orElse(null);
        Answer answerToUpdate = answerRepository.findById(answer_updateOp.getA_id()).orElse(null);
        int resultCode = 0;
        if (questionToUpdate != null && answerToUpdate != null) {

            // 엔터티를 찾았다면 UpdateData의 값으로 엔터티를 업데이트합니다.
            questionToUpdate.setQ_id(questionToUpdate.getQ_id());
            questionToUpdate.setMemberId(questionToUpdate.getMemberId());
            questionToUpdate.setAdd_date(questionToUpdate.getAdd_date());
            questionToUpdate.setChange_date(formattedDateTime);
            questionToUpdate.setContent(question_updateOp.getContent());

            // FAQ 엔터티를 저장하여 데이터베이스를 업데이트합니다.
            questionRepository.save(questionToUpdate);

            answerToUpdate.setA_id(answerToUpdate.getA_id());
            answerToUpdate.setMember_id(answerToUpdate.getMember_id());
            answerToUpdate.setAdd_answerdate(answerToUpdate.getAdd_answerdate());
            answerToUpdate.setChange_date(formattedDateTime);
            answerToUpdate.setContent(answer_updateOp.getContent());
            answerToUpdate.setQId(answerToUpdate.getQId());
            answerRepository.save(answerToUpdate);
            resultCode = 0;
        }else {
            // 해당 id를 가진 엔터티가 없는 경우에 대한 처리
            // 예를 들어 예외를 던지거나, 로그를 남기거나, 메시지를 반환할 수 있습니다.
            resultCode = 1;

        }
        return resultCode;
    }

}
