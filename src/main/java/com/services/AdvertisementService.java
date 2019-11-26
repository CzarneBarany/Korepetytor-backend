package com.services;

import com.entities.AccountEntity;
import com.entities.AdvertisementEntity;
import com.exceptions.EntityNotFoundException;
import com.repositories.AccountRepository;
import com.repositories.AdvertisementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
        if (accountRepository.getAccountEntityByAccountId(teacherId) == null) {
            throw new EntityNotFoundException("Nie znaleziono nauczyciela o takim id: " + teacherId);
        }

        return advertisementRepository.getAdvertisementEntitiesByTeacherId(teacherId);
    }

    public void editAdvertisement(AdvertisementEntity advertisementEntity) {
        advertisementRepository.save(advertisementEntity);
    }

    public List<AdvertisementEntity> getAllAdvertisementsByStudent(int accountId) {
        AccountEntity studentEntity = accountRepository.getAccountEntityByAccountId(accountId);
        if (studentEntity == null) {
            throw new EntityNotFoundException("Nie znaleziono konta o takim id: " + accountId);
        }

        return advertisementRepository.getAdvertisementEntitiesByListOfStudents(studentEntity);
    }

    public List<AdvertisementEntity> getAllAdvertisementsByCategoryAndLevelOfEducation(String category, String levelOfEducation) {
        return advertisementRepository.getAdvertisementEntitiesByCategoryAndLevelOfEducation(category, levelOfEducation);
    }

    public void addStudentToAdvertisement(int adId, int studentId) {
        AccountEntity studentEntity = accountRepository.getAccountEntityByAccountId(studentId);
        AdvertisementEntity advertisementEntity = advertisementRepository.getAdvertisementEntityByAdId(adId);

        List<AccountEntity> listOfStudents = advertisementEntity.getListOfStudents();
        listOfStudents.add(studentEntity);
        advertisementEntity.setListOfStudents(listOfStudents);
        log.debug("Dodaje studenta o id: " + studentId + " do ogłoszenia o id: " + adId);
    }
}
