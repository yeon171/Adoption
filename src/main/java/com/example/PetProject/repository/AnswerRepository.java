package com.example.PetProject.repository;

import com.example.PetProject.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Optional<Answer> findByqId(Integer qId);
}
