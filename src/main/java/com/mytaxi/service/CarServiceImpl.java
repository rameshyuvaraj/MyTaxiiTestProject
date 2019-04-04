package com.mytaxi.service;


import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.dao.car.CarDao;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl {

    @Autowired
    private CarDao carDao;

    @Autowired
    private CarMapper carMapper;

    public CarDTO saveCar(CarDTO carDTO) throws ConstraintsViolationException {
        carDao.save(carMapper.transformToCarDO(carDTO));
        return carDTO;
    }

    public CarDTO getCar(Long carId) throws EntityNotFoundException {
        return carMapper.transformToCarDTO(carDao.find(carId));
    }

    public void deleteCar(long carId) throws EntityNotFoundException {
        carDao.delete(carId);
    }

    public List<CarDTO> getAllCars() throws EntityNotFoundException {
        List<CarDTO> carDTOList = new ArrayList<>();
        carDao.findAll().forEach(carDO -> carDTOList.add(carMapper.transformToCarDTO(carDO)));
        return carDTOList;
    }

    public List<CarDTO> getAllCars(boolean availability) throws EntityNotFoundException {
        boolean assignedToDriver = !availability;
        List<CarDTO> carDTOList = new ArrayList<>();
        carDao.findByAvailability(assignedToDriver).forEach(carDO -> carDTOList.add(carMapper.transformToCarDTO(carDO)));
        return carDTOList;
    }

}
