package com.endpoints;

import com.entities.AdvertisementEntity;
import com.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AdvertisementEndpoint {

    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementEndpoint(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/add/ad")
    ResponseEntity addAdvertisement(@RequestBody AdvertisementEntity advertisementEntity) {
        advertisementService.addAdvertisement(advertisementEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/edit/ad")
    ResponseEntity editAdvertisement(@RequestBody AdvertisementEntity advertisementEntity) {
        advertisementService.editAdvertisement(advertisementEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/delete/ad/{adId}")
    ResponseEntity deleteAdvertisementById(@PathVariable int adId) {
        advertisementService.deleteAdvertisement(adId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get/allAds")
    ResponseEntity<List<AdvertisementEntity>> getAllAdvertisements() {
        List<AdvertisementEntity> listOfAds = advertisementService.getAllAdvertisements();
        return new ResponseEntity<>(listOfAds, HttpStatus.OK);
    }

    @GetMapping("/get/allAds/teacher/{teacherId}")
    ResponseEntity<List<AdvertisementEntity>> getAllAdvertisementsByTeacher(@PathVariable int teacherId) {
        List<AdvertisementEntity> listOfAdsByTeacher = advertisementService.getAllAdvertisementsByTeacher(teacherId);
        return new ResponseEntity<>(listOfAdsByTeacher, HttpStatus.OK);
    }

    @GetMapping("/get/allAds/student/{accountId}")
    ResponseEntity<List<AdvertisementEntity>> getAllAdvertisementsByStudentId(@PathVariable int accountId) {
        List<AdvertisementEntity> listOfAds = advertisementService.getAllAdvertisementsByStudent(accountId);
        return new ResponseEntity<>(listOfAds, HttpStatus.OK);
    }
}
