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
@Table(name = "question", catalog = "pet_data")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "q_id", nullable = false)
    private Integer q_id;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Builder
    public Question(Integer q_id, Integer memberId, String content, String add_date, String change_date ) {
        this.q_id = q_id;
        this.memberId = memberId;
        this.content = content;
        this.add_date = add_date;
        this.change_date = change_date;
    }
}
