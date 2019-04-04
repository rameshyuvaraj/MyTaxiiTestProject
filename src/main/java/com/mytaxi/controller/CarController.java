package com.mytaxi.controller;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarServiceImpl;
import com.mytaxi.service.DriverSearchServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/cars")
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarServiceImpl carServiceImpl;

    @PostMapping
    public ResponseEntity<CarDTO> saveCar(@Valid @RequestBody CarDTO carDTO) {
        try {
            return new ResponseEntity<>(carServiceImpl.saveCar(carDTO),HttpStatus.CREATED);
        } catch (ConstraintsViolationException e) {
            LOG.info("Constraint Exception  ",e);
            return new ResponseEntity<>(carDTO,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Long carId) {
        try {
            return new ResponseEntity<>(carServiceImpl.getCar(carId),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception  ",e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable long carId) {
        try {
            carServiceImpl.deleteCar(carId);
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception  ",e);
            return new ResponseEntity<>("Unable to delete",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        try {
            return new ResponseEntity<>(carServiceImpl.getAllCars(),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception  ",e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/availability/{availability}")
    public ResponseEntity<List<CarDTO>> getAllCars(@PathVariable boolean availability){
        try {
            return new ResponseEntity<>(carServiceImpl.getAllCars(availability),HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception  ",e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
