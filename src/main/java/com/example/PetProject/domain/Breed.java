package com.example.PetProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "breed", catalog = "pet_data")
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breed_id", nullable = false)
    private Integer breed_id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "img")
    private String img;

    @Column(name = "breed")
    private String breed;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Column(name = "member_id", length = 50, nullable = false)
    private Integer member_id;

    @Column(name = "state", length = 20)
    private String state;

   @Column(name = "imagepath")
    private String imagepath;

    @Builder
    public Breed(Integer breed_id, String title, String content, String img,
                 String breed, String add_date, String change_date, Integer member_id, String state, String imagepath){
        this.breed_id = breed_id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.breed = breed;
        this.add_date = add_date;
        this.change_date = change_date;
        this.member_id = member_id;
        this.state = state;
        this.imagepath = imagepath;
    }


    public String getType() {
        return breed;
    }
/*
    public Breed  setTitle(String title) {
        this.title=title;
        return this;
    }

    public void setContent(String content) {
    }

    public Breed setStatus(String active) {
        this.state = state;
        return this;
    }

    public Breed setBreed(String breed) {
        this.breed = breed;
        return this;
    }*/

}
