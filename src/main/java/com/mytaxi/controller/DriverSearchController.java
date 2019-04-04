package com.mytaxi.controller;


import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.DriverSearchServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/searchDriverBy")
public class DriverSearchController {

    private static final Logger LOG = LoggerFactory.getLogger(DriverSearchController.class);

    @Autowired
    private DriverSearchServiceImpl searchController;

    @GetMapping("/username/{username}")
    public ResponseEntity<DriverDTO> getDriversByUserName(@PathVariable String username) {
        return new ResponseEntity<>(searchController.getDriversByUserName(username), HttpStatus.OK);
    }

    @GetMapping("/carLicensePlate/{licensePlateNumber}")
    public ResponseEntity<DriverDTO> getDriversByCarLicensePlate(@PathVariable String licensePlateNumber){
        try {
            return new ResponseEntity<>(searchController.getDriversByCarLicensePlate(licensePlateNumber),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception",e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/CarRating/{rating}")
    public ResponseEntity<List<DriverDTO>> getDriversWithGivenCarRating(@PathVariable int rating) {
        try {
            return new ResponseEntity<>(searchController.getDriversWithGivenCarRating(rating),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception ",e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/carsAssigned")
    public ResponseEntity<List<DriverDTO>> getDriversWithCarsAssigned() {
        try {
            return new ResponseEntity<>(searchController.getDriversWithCarsAssigned(),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception ",e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/carsUnAssigned")
    public ResponseEntity<List<DriverDTO>> getDriversWithCarsUnAssigned() {
        try {
            return new ResponseEntity<>(searchController.getDriversWithCarsUnAssigned(),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception ",e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
