package com.repositories;

import com.entities.AccountEntity;
import com.entities.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Integer> {
    AdvertisementEntity getAdvertisementEntityByAdId(int adId);
    List<AdvertisementEntity> getAdvertisementEntitiesByTeacher(AccountEntity accountEntity);
    List<AdvertisementEntity> getAdvertisementEntitiesByListOfStudents(AccountEntity accountEntity);
    List<AdvertisementEntity> getAdvertisementEntitiesByCategoryAndLevelOfEducation(String category, String levelOfEducation);
}
