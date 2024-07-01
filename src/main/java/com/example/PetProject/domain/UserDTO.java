package com.example.PetProject.domain;

import com.example.PetProject.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private String member_id;
    private String name;
    private String pw;
    private String phone;
    private String birth;
    private String email;
    private String add_date;
    private String change_date;
    private String memType;

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(userEntity.getName());
        userDTO.setPw(userEntity.getPw());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setBirth(userEntity.getBirth());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setAdd_date(userEntity.getAdd_date());
        userDTO.setChange_date(userEntity.getChange_date());
        userDTO.setMemType("user");

        return userDTO;
    }
}
