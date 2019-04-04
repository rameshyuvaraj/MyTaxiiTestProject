package com.mytaxi.service;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dao.car.CarDao;
import com.mytaxi.dao.driver.DriverDao;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@Service
public class DriverServiceImpl {

    private final DriverDao driverDao;

    @Autowired
    private CarDao carDao;


    @Autowired
    public DriverServiceImpl(final DriverDao driverDao) {
        this.driverDao = driverDao;
    }


    public DriverDTO getDriver(long driverId) throws EntityNotFoundException {
        return DriverMapper.makeDriverDTO(driverDao.find(driverId));
    }


    public DriverDTO createDriver(DriverDTO driverDTO) throws ConstraintsViolationException {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverDao.save(driverDO));
    }


    public void deleteDriver(long driverId) throws EntityNotFoundException {
        driverDao.delete(driverId);
    }


    public void updateLocation(long driverId, double longitude, double latitude)
            throws EntityNotFoundException {
        driverDao.updateLocation(driverId, longitude, latitude);
    }


    public List<DriverDTO> findDrivers(OnlineStatus onlineStatus) {
        return DriverMapper.makeDriverDTOList(driverDao.find(onlineStatus));
    }

    public void selectCar(long driverId, long carId) throws EntityNotFoundException,
            ConstraintsViolationException, CarAlreadyInUseException, DriverNotOnlineException {
        List<DriverDO> driverDOS = driverDao.find(OnlineStatus.ONLINE);
        DriverDO driverDO = driverDao.find(driverId);
        if (driverDOS.contains(driverDO)) {
            synchronized (driverDao) {
                if (driverDO.getCarDO() == null) {
                    CarDO carDO = carDao.find(carId);
                    if (carDO.isAssignedToDriver()) {
                        throw new CarAlreadyInUseException("This car is already assigned to another driver. " +
                                "Please select different car.");
                    }
                    carDO.setAssignedToDriver(true);
                    driverDO.setCarDO(carDO);
                    carDao.save(carDO);
                    driverDao.save(driverDO);
                } else {
                    throw new CarAlreadyInUseException("Driver is already using the car " + driverDO.getCarDO().getCarName()
                            + ". " + "Please deselect the car to select another car");
                }
            }
        } else {
            throw new DriverNotOnlineException("Driver is not available online to select a car");
        }
    }

    public void deSelectCar(long driverId, long carId) throws EntityNotFoundException,
            ConstraintsViolationException, NoCarAssignedException {
        DriverDO driverDO = driverDao.find(driverId);
        if (driverDO.getCarDO() != null) {
            CarDO carDO = carDao.find(carId);
            driverDO.setCarDO(null);
            carDO.setAssignedToDriver(false);
            carDao.save(carDO);
            driverDao.save(driverDO);
        } else {
            throw new NoCarAssignedException("Driver has no car selected. Please select a car first");
        }
    }

}
