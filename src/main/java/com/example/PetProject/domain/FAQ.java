package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "faq", catalog = "pet_data")
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id", nullable = false)
    private Integer faq_id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Column(name = "member_id", length = 50, nullable = false)
    private Integer member_id;

    @Builder
    public FAQ(Integer faq_id, String title, String content, String add_date, String change_date, Integer member_id) {
        this.faq_id = faq_id;
        this.title = title;
        this.content = content;
        this.add_date = add_date;
        this.change_date = change_date;
        this.member_id = member_id;
    }
}
