package com.mytaxi.dao.driver;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverDaoImpl implements DriverDao {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverDaoImpl.class);

    private final DriverRepository driverRepository;


    public DefaultDriverDaoImpl(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO save(DriverDO driverDO) throws ConstraintsViolationException {
        DriverDO driver;
        try {
            driver = driverRepository.save(driverDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus) {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

    @Override
    public DriverDO find(String username){
        return driverRepository.findByUsername(username);
    }

    @Override
    public List<DriverDO> findAll() throws EntityNotFoundException {
        List<DriverDO> driverDOS = new ArrayList<>();
        try{
            driverRepository.findAll().forEach(driver -> driverDOS.add(driver));
        }catch (DataAccessException e){
            LOG.warn("Unable to fetch all drivers ", e);
            throw new EntityNotFoundException("Unable to fetch all drivers", e);
        }
        return driverDOS;
    }


}
