package com.mytaxi.controller;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.*;
import com.mytaxi.service.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    private static final Logger LOG = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverServiceImpl driverServiceImpl;


    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable long driverId) {
        try {
            return new ResponseEntity<>(driverServiceImpl.getDriver(driverId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception  ", e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        try {
            return new ResponseEntity<>(driverServiceImpl.createDriver(driverDTO), HttpStatus.CREATED);
        } catch (ConstraintsViolationException e) {
            LOG.info("Exception  ", e);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @DeleteMapping("/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable long driverId) {
        try {
            driverServiceImpl.deleteDriver(driverId);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOG.info("Exception  ", e);
            return new ResponseEntity<>("Unable to delete", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{driverId}")
    public ResponseEntity<String> updateLocation(
            @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude) {
        try {
            driverServiceImpl.updateLocation(driverId, longitude, latitude);
            return new ResponseEntity<>("Location Updated", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Unable to update location ", HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping
    public ResponseEntity<List<DriverDTO>> findDrivers(@RequestParam OnlineStatus onlineStatus) {
        return new ResponseEntity<>(driverServiceImpl.findDrivers(onlineStatus), HttpStatus.OK);
    }

    @GetMapping("/{driverId}/select/{carId}")
    public ResponseEntity<String> selectCar(@PathVariable long driverId, @PathVariable long carId) {
        try {
            driverServiceImpl.selectCar(driverId, carId);
            return new ResponseEntity<>("Successfully selected", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Unable to delete", HttpStatus.NO_CONTENT);
        } catch (ConstraintsViolationException e) {
            return new ResponseEntity<>("Cannot Select car", HttpStatus.NOT_ACCEPTABLE);
        } catch (CarAlreadyInUseException e) {
            return new ResponseEntity<>("Car already in use", HttpStatus.BAD_REQUEST);
        } catch (DriverNotOnlineException e) {
            return new ResponseEntity<>("Driver is not online to select car", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{driverId}/deselect/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deSelectCar(@PathVariable long driverId, @PathVariable long carId) {
        try {
            driverServiceImpl.deSelectCar(driverId, carId);
            return new ResponseEntity<>("Successfully deSelected", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Unable to delete", HttpStatus.NO_CONTENT);
        } catch (ConstraintsViolationException e) {
            return new ResponseEntity<>("Cannot DeSelect car", HttpStatus.NOT_ACCEPTABLE);
        } catch (NoCarAssignedException e) {
            return new ResponseEntity<>("No Car Assigned for the driver", HttpStatus.BAD_REQUEST);
        }
    }
}
