package com.services;

import com.entities.AccountEntity;
import com.entities.AdvertisementEntity;
import com.exceptions.EntityNotFoundException;
import com.repositories.AccountRepository;
import com.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    private AccountRepository accountRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository, AccountRepository accountRepository) {
        this.advertisementRepository = advertisementRepository;
        this.accountRepository = accountRepository;
    }

    public void addAdvertisement(AdvertisementEntity advertisementEntity) {
        advertisementRepository.save(advertisementEntity);
    }

    public void deleteAdvertisement(int advertisementId) {
        AdvertisementEntity advertisement = advertisementRepository.getAdvertisementEntityByAdId(advertisementId);
        if (advertisement == null) {
            throw new EntityNotFoundException("Nie znaleziono takiego ogłoszenia");
        }

        advertisementRepository.delete(advertisement);
    }

    public List<AdvertisementEntity> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    public List<AdvertisementEntity> getAllAdvertisementsByTeacher(int teacherId) {
        AccountEntity teacherEntity = accountRepository.getAccountEntityByAccountId(teacherId);
        if (teacherEntity == null) {
            throw new EntityNotFoundException("Nie znaleziono nauczyciela o takim id" + teacherId);
        }

        return advertisementRepository.getAdvertisementEntitiesByTeacher(teacherEntity);
    }
}
