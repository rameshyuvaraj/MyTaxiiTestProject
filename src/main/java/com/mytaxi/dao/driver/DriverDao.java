package com.mytaxi.dao.driver;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

public interface DriverDao {

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO save(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    DriverDO find(String username);

    List<DriverDO> findAll() throws EntityNotFoundException;


}
