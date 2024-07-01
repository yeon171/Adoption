package com.example.PetProject.service;


import com.example.PetProject.domain.Breed;
import com.example.PetProject.domain.Community;
import com.example.PetProject.domain.FAQ;
import com.example.PetProject.repository.CommunityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public List<Community> getList() {
        return communityRepository.findAll();
    }

    @Transactional
    public int deletecomm(List<Integer> commId) {
        int delResult = 0;
        try {
            List<Community> commToDelete = communityRepository.findAllById(commId);
            communityRepository.deleteAll(commToDelete);
            delResult = 1;
        } catch (Exception e) {
            delResult = 0;
        } finally {
            return delResult;
        }
    }

    //수정
    @Transactional
    public Optional<Community> viewcomm(Integer commId) {
        Optional<Community> commoption = communityRepository.findById(commId);
        return commoption;
    }

    @Transactional
    public int updatecomm(Community commUpdate) {
        try {
            Optional<Community> commOptional = communityRepository.findById(commUpdate.getCommu_id());
            if (commOptional.isPresent()) {
                Community existingComm = commOptional.get();

                existingComm.setMember_id(commUpdate.getMember_id());
                existingComm.setTitle(commUpdate.getTitle());
                existingComm.setContent(commUpdate.getContent());
                // Update other fields as necessary
                existingComm.setAdd_date(LocalDateTime.now().toString());
                existingComm.setChange_date(LocalDateTime.now().toString());

                communityRepository.save(existingComm); // Save the updated breed
                return 0; // Success
            } else {
                return 1; // Breed not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1; // Error occurred
        }
    }

   /* @Transactional
    public List<Community> getList() {
        return communityRepository.findAll();
    }*/

    /*@Transactional
    public int deleteComm(List<Integer> commIds) {
        int delResult = 0;
        try {
            List<Community> deleteComm = communityRepository.findAllById(commIds);
            communityRepository.deleteAll(deleteComm);
            delResult=1;
        }catch (Exception e){
            delResult=0;
        } finally {
            return delResult;
        }
    }*/
}
