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

    @PostMapping("/delete/adById")
    ResponseEntity deleteAdvertisementById(@PathVariable int adId) {
        advertisementService.deleteAdvertisement(adId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/get/allAds")
    ResponseEntity<List<AdvertisementEntity>> getAllAdvertisements() {
        List<AdvertisementEntity> listOfAds = advertisementService.getAllAdvertisements();
        return new ResponseEntity<>(listOfAds, HttpStatus.OK);
    }
}