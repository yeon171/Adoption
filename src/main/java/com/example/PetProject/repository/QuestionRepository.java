package com.example.PetProject.repository;

import com.example.PetProject.domain.Answer;
import com.example.PetProject.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByMemberId(Integer member_id);
}
