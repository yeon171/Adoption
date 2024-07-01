package com.example.PetProject.entity;

import com.example.PetProject.domain.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member", catalog = "pet_data")
public class UserEntity {
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

    @Builder
    public static UserEntity toUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();

        if (userDTO.getMember_id() != null) {
            userEntity.member_id = Integer.valueOf(userDTO.getMember_id());
        }
        userEntity.name = userDTO.getName();
        userEntity.pw = userDTO.getPw();
        userEntity.phone = userDTO.getPhone();
        userEntity.birth = userDTO.getBirth();
        userEntity.email = userDTO.getEmail();
        userEntity.add_date = userDTO.getAdd_date();
        userEntity.change_date = userDTO.getChange_date();
        userEntity.mem_type = userDTO.getMemType();

        return userEntity;
    }
}
