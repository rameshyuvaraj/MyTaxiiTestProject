package com.mytaxi.dao.car;

import com.google.common.collect.Lists;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.datatransferobject.CarRating;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultCarDaoImpl implements CarDao {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCarDaoImpl.class);

    private CarRepository carRepository;

    public DefaultCarDaoImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
        try {
            return carRepository.findById(carId).get();
        } catch (DataAccessException e) {
            LOG.info("Error while fetching car with id ", carId, e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public void save(CarDO carDO) throws ConstraintsViolationException {
        try {
            carRepository.save(carDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Exception while creating Car ", carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }

    @Override
    public void delete(Long carId) throws EntityNotFoundException {
        try {
            carRepository.deleteById(carId);
        } catch (DataAccessException e) {
            LOG.warn("Exception while deleting Car with id ", carId, e);
            throw new EntityNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public List<CarDO> findAll() throws EntityNotFoundException {
        List<CarDO> carDOList = new ArrayList<>();
        try {
            carRepository.findAll().forEach(e -> carDOList.add(e));
        } catch (DataAccessException e) {
            LOG.warn("Unable to fetch all cars ", e);
            throw new EntityNotFoundException("Unable to fetch all cars", e);
        }
        return carDOList;
    }

    @Override
    public List<CarDO> findByAvailability(boolean assignedToDriver) throws EntityNotFoundException {
        try {
            return Lists.newArrayList(carRepository.findAll()).stream().filter(carDO -> {
                if (carDO.isAssignedToDriver() == assignedToDriver) {
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.warn("Unable to fetch car by availability ", e);
            throw new EntityNotFoundException("Unable to fetch car by availability ", e);
        }
    }

    @Override
    public CarDO findByLicensePlateNo(String plateNumber) throws EntityNotFoundException {
        List<CarDO> carDOList;
        try {
            carDOList = Lists.newArrayList(carRepository.findAll()).stream().filter(carDO -> {
                if(carDO.getLicensePlate().equals(plateNumber)){
                    return true;
                }else{
                    return false;
                }
            }).collect(Collectors.toList());
        } catch (DataAccessException e) {
            LOG.warn("Unable to fetch all cars ", e);
            throw new EntityNotFoundException("Unable to fetch all cars", e);
        }
        if(!carDOList.isEmpty()) {
            return carDOList.get(0);
        }else{
            throw new EntityNotFoundException("No Car with given license number");
        }
    }

    @Override
    public List<CarDO> findByCarRating(CarRating carRating) {
        return Lists.newArrayList(carRepository.findAll()).stream().filter(carDO -> {
            if (carDO.getRating().ordinal() >= carRating.ordinal()) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }

}
