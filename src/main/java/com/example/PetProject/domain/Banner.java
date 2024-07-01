package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "banner", catalog = "pet_data")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id", nullable = false)
    private Integer banner_id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "img", length = 255)
    private String img;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Column(name = "member_id", length = 50, nullable = false)
    private Integer member_id;

    @Builder
    public Banner(Integer banner_id, String title, String img, String add_date, String change_date, Integer member_id) {
        this.banner_id = banner_id;
        this.title = title;
        this.img = img;
        this.add_date = add_date;
        this.change_date = change_date;
        this.member_id = member_id;
    }
}
