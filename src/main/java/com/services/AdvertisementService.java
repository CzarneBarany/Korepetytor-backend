package com.services;

import com.entities.AdvertisementEntity;
import com.exceptions.EntityNotFoundException;
import com.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public void addAdvertisement(AdvertisementEntity advertisementEntity){
        advertisementRepository.save(advertisementEntity);
    }

    public void deleteAdvertisement(int advertisementId){
        AdvertisementEntity advertisement = advertisementRepository.getAdvertisementEntityByAdId(advertisementId);
        if(advertisement != null){
        advertisementRepository.delete(advertisement);
        }
        else throw new EntityNotFoundException("Nie znaleziono takiego ogłoszenia");
    }

    public List<AdvertisementEntity> getAllAdvertisements(){
        return advertisementRepository.findAll();
    }
}
