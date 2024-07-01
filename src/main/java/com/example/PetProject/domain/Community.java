package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "community", catalog = "pet_data")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commu_id", nullable = false)
    private Integer commu_id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Column(name = "member_id", length = 50, nullable = false)
    private Integer member_id;

    @Column(name = "state")
    private String state;

    @Builder
    public Community(Integer commu_id, String title, String content,
            String add_date, String change_date, Integer member_id, String state){
        this.commu_id = commu_id;
        this.title = title;
        this.content = content;
        this.add_date = add_date;
        this.change_date = change_date;
        this.member_id = member_id;
        this.state = state;
    }
}
