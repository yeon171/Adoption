package com.example.PetProject.service;

import com.example.PetProject.domain.Banner;
import com.example.PetProject.domain.FAQ;
import com.example.PetProject.domain.Question;
import com.example.PetProject.repository.BannerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    @Transactional
    public List<Banner> getList() {
        return bannerRepository.findAll();
    }

    @Transactional
    public Optional<Banner> detailbanners(Integer bannerId){
        Optional<Banner> bannerOptional = bannerRepository.findById(bannerId);
        return bannerOptional;
    }

    @Transactional
    public int insertBanners(Banner banner_insertOp){

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentTime.format(formatter);

        banner_insertOp.setAdd_date(formattedDateTime);

        Banner insertedBanner = bannerRepository.save(banner_insertOp);
        int resultCode = 0;
        if (insertedBanner != null) {
            resultCode = 0;
        } else {
            resultCode = 1;
        }
        return resultCode;
    }

    // 선택된 체크박스 가져와서 delete
    @Transactional
    public int deleteBanners(List<Integer> bannerIds) {
        int delResult = 0;
        try {
            List<Banner> bannersToDelete = bannerRepository.findAllById(bannerIds);
            bannerRepository.deleteAll(bannersToDelete);
            delResult=1;
        }catch (Exception e){
            delResult=0;
        } finally {
            return delResult;
        }
    }

    @Transactional
    public int updateBanners(Banner banner_updateOp){

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentTime.format(formatter);

        Banner bannerToUpdate = bannerRepository.findById(banner_updateOp.getBanner_id()).orElse(null);
        int resultCode = 0;
        if (bannerToUpdate != null) {
            // 엔터티를 찾았다면 UpdateData의 값으로 엔터티를 업데이트합니다.
            bannerToUpdate.setMember_id(banner_updateOp.getMember_id());
            bannerToUpdate.setAdd_date(banner_updateOp.getAdd_date());
            bannerToUpdate.setChange_date(formattedDateTime);
            bannerToUpdate.setTitle(banner_updateOp.getTitle());
            bannerToUpdate.setImg(banner_updateOp.getImg());

            // FAQ 엔터티를 저장하여 데이터베이스를 업데이트합니다.
            bannerRepository.save(bannerToUpdate);
            resultCode = 0;
        } else {
            // 해당 id를 가진 엔터티가 없는 경우에 대한 처리
            // 예를 들어 예외를 던지거나, 로그를 남기거나, 메시지를 반환할 수 있습니다.
            resultCode = 1;

        }
        return resultCode;
    }
}
