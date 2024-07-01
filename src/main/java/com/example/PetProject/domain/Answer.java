package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@Table(name = "answer", catalog = "pet_data")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id", nullable = false)
    private Integer a_id;

    @Column(name = "member_id", nullable = false)
    private Integer member_id;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "add_answerdate", length = 50)
    private String add_answerdate;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Column(name = "q_id", length = 255)
    private Integer qId;

    @Builder
    public Answer(Integer a_id, Integer member_id, String content, String add_answerdate, String change_date, Integer qId) {
        this.a_id = a_id;
        this.member_id = member_id;
        this.content = content;
        this.add_answerdate = add_answerdate;
        this.change_date = change_date;
        this.qId = qId;
    }
}
