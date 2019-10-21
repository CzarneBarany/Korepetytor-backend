package com.repositories;

import com.entities.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Integer> {
    AdvertisementEntity getAdvertisementEntityByAdId(int adId);
}
