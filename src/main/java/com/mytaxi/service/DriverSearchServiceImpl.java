package com.mytaxi.service;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dao.car.CarDao;
import com.mytaxi.dao.driver.DriverDao;
import com.mytaxi.datatransferobject.CarRating;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverSearchServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(DriverSearchServiceImpl.class);

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private CarDao carDao;

    public DriverDTO getDriversByUserName(String username) {
        return DriverMapper.makeDriverDTO(driverDao.find(username));
    }

    public DriverDTO getDriversByCarLicensePlate(String licensePlateNumber)
            throws EntityNotFoundException {
        DriverDTO driverDTO;
        CarDO carDO = carDao.findByLicensePlateNo(licensePlateNumber);
        List<DriverDO> driverWithCarNumber = driverDao.findAll().stream().filter(driverDO -> {
            if (driverDO.getCarDO() != null &&
                    driverDO.getCarDO().getLicensePlate().equals(carDO.getLicensePlate())) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(driverWithCarNumber)) {
            driverDTO = DriverMapper.makeDriverDTO(driverWithCarNumber.get(0));
        }else {
            throw new EntityNotFoundException("No driver using car with given numberplate");
        }
        return driverDTO;
    }

    public List<DriverDTO> getDriversWithGivenCarRating(int rating) throws EntityNotFoundException {
        CarRating carRating = CarRating.values()[rating];
        List<CarDO> carDOList = carDao.findByCarRating(carRating);
        List<DriverDO> driverDOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(carDOList)) {
            driverDOList = driverDao.findAll().stream().filter(driverDO -> {
                boolean matches = false;
                for (CarDO carDO : carDOList) {
                    if(driverDO.getCarDO()!=null){
                        if (carDO.getId() == driverDO.getCarDO().getId()) {
                            matches = true;
                            break;
                        }
                    }else{
                        break;
                    }
                }
                return matches;
            }).collect(Collectors.toList());
        }
        return DriverMapper.makeDriverDTOList(driverDOList);
    }

    public List<DriverDTO> getDriversWithCarsAssigned() throws EntityNotFoundException {
        List<DriverDO> driverDOList;
        driverDOList = driverDao.findAll().stream().filter(driverDO -> {
            if (driverDO.getCarDO() != null) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        return DriverMapper.makeDriverDTOList(driverDOList);
    }

    public List<DriverDTO> getDriversWithCarsUnAssigned() throws EntityNotFoundException {
        List<DriverDO> driverDOList;
        driverDOList = driverDao.findAll().stream().filter(driverDO -> {
            if (driverDO.getCarDO() == null) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        return DriverMapper.makeDriverDTOList(driverDOList);
    }

}
