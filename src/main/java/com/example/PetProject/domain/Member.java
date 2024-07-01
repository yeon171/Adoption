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
@Table(name = "Member", catalog = "pet_data")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Integer member_id;

    @Column(name = "name", length = 10)
    private String name;

    @Column(name = "pw", length = 12)
    private String pw;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "birth", length = 20)
    private String birth;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "add_date", length = 50)
    private String add_date;

    @Column(name = "change_date", length = 50)
    private String change_date;

    @Column(name = "mem_type", length = 10)
    private String mem_type;

    /*@OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Board> board = new ArrayList<>();*/

    //빌더
    @Builder
    public Member(Integer member_id, String name, String pw, String phone, String birth, String email,
                  String add_date, String change_date, String mem_type) {
        this.member_id = member_id;
        this.name = name;
        this.pw = pw;
        this.phone = phone;
        this.birth = birth;
        this.email = email;
        this.add_date = add_date;
        this.change_date = change_date;
        this.mem_type = mem_type;
    }
}