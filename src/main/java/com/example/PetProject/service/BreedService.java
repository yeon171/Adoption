package com.example.PetProject.service;

import com.example.PetProject.domain.Breed;

import com.example.PetProject.repository.BreedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ComponentScan
@Service
@RequiredArgsConstructor
public class BreedService {
    private final BreedRepository breedRepository;

    @Transactional
    public List<Breed> getReportedAndDeletedBreeds() {
        return breedRepository.findByStateIn(Arrays.asList("신고", "삭제"));
    }

    //breed_view_id
    @Transactional
    public Breed getBreedById(Integer id) {
        return breedRepository.findById(id).orElseThrow(() -> new RuntimeException("Breed not found"));
    }

    @Transactional
    public int deleteBreed(List<Integer> breedId) {
        int delResult = 0;
        try {
            List<Breed> breedToDelete = breedRepository.findAllById(breedId);
            breedRepository.deleteAll(breedToDelete);
            delResult = 1;
        } catch (Exception e) {
            delResult = 0;
        } finally {
            return delResult;
        }
    }

    //수정
    @Transactional
    public Optional<Breed> viewBreed(Integer breedId){
        Optional<Breed> Breedoption = breedRepository.findById(breedId);
        return Breedoption;
    }

    @Transactional
    public int updatebreed(Breed breed_update) {
        try {
            Optional<Breed> breedOptional = breedRepository.findById(breed_update.getBreed_id());
            if (breedOptional.isPresent()) {
                Breed existingBreed = breedOptional.get();

                existingBreed.setMember_id(breed_update.getMember_id());
                existingBreed.setTitle(breed_update.getTitle());
                existingBreed.setContent(breed_update.getContent());
                // Update other fields as necessary
                existingBreed.setAdd_date(LocalDateTime.now().toString());
                existingBreed.setChange_date(LocalDateTime.now().toString());

                breedRepository.save(existingBreed); // Save the updated breed
                return 0; // Success
            } else {
                return 1; // Breed not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1; // Error occurred
        }
    }

    //등록
    @Transactional
    public int insertBreed(Breed breed_insert){

        breed_insert.setAdd_date(LocalDateTime.now().toString());
        breed_insert.setChange_date(LocalDateTime.now().toString());

        Breed insertBreedin = breedRepository.save(breed_insert);
        int resultCode = 0;
        if (insertBreedin != null) {
            resultCode = 0;
        } else {
            resultCode = 1;
        }
        return resultCode;
    }

    //breed_user_show
    public List<Breed> getAllBreeds() {
        return breedRepository.findAll();
    }

    //breed_user_search
    public List<Breed> findAllBreeds() {
        // 모든 품종 반환 구현
        return breedRepository.findAll();
    }


    /*@Value("${C:/upload/tmp}")
    private String uploadDir;

    // 파일 저장 서비스 메서드
    public String saveImage(MultipartFile file) throws IOException {
        // 파일을 저장할 디렉토리 경로 설정
        Path uploadPath = Paths.get(uploadDir);

        // 디렉토리가 존재하지 않으면 생성
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 파일명 생성 (UUID 사용)
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 파일 저장 경로 설정
        Path filePath = uploadPath.resolve(fileName);

        // 파일 저장
        Files.copy(file.getInputStream(), filePath);

        // 파일 저장 경로를 문자열로 반환
        return filePath.toString();
    }

    public void saveBreed(Breed breedData) {
        breedRepository.save(breedData);
    }*/
}

