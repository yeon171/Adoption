package com.example.PetProject.service;

import com.example.PetProject.domain.UserDTO;
import com.example.PetProject.entity.UserEntity;
import com.example.PetProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;// jpa, MySQL dependency 추가

    public void save(UserDTO userDTO) {
        // request -> DTO -> Entity -> Repository에서 save
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }
}