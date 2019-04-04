package com.mytaxi.dao.car;

import com.mytaxi.datatransferobject.CarRating;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

public interface CarDao {

    CarDO find(Long carId) throws EntityNotFoundException;

    void save(CarDO CarDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    List<CarDO> findAll() throws EntityNotFoundException;

    List<CarDO> findByAvailability(boolean assignedToDriver) throws EntityNotFoundException;

    CarDO findByLicensePlateNo(String plateNumber) throws EntityNotFoundException;

    List<CarDO> findByCarRating(CarRating carRating);

}
