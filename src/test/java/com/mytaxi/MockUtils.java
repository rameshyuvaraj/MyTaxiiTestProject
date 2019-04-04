package com.mytaxi;

import com.mytaxi.datatransferobject.*;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.ManufatureDO;

import java.time.ZonedDateTime;

public class MockUtils {

    public static CarDTO sampleCarDTO(){
        CarDTO carDTO = new CarDTO();
        carDTO.setAirBagCount(2);
        carDTO.setCarName("Ignis");
        carDTO.setCarRating(CarRating.FOUR);
        carDTO.setConvertible(false);
        carDTO.setEngineType(EngineType.HYBRID);
        carDTO.setGearType(GearType.MANUAL);
        carDTO.setGpsEnabled(true);
        carDTO.setLicensePlate("AB1233");
        carDTO.setId(1L);
        carDTO.setSeat_count(4);
        ManufatureDTO manufatureDTO = new ManufatureDTO();
        manufatureDTO.setId(1L);
        manufatureDTO.setModel("IM2017");
        manufatureDTO.setModelYear("2017");
        manufatureDTO.setName("Suzuki");
        carDTO.setManufatureDTO(manufatureDTO);
        return carDTO;
    }

    public static CarDO sampleCarEntity(){
        CarDO carEntity = new CarDO();
        carEntity.setAirBagCount(2);
        carEntity.setAssignedToDriver(true);
        carEntity.setCarName("Ignis");
        carEntity.setConvertible(false);
        carEntity.setDateCreated(ZonedDateTime.now());
        carEntity.setEngineType(EngineType.HYBRID);
        carEntity.setGpsEnabled(true);
        carEntity.setGearType(GearType.MANUAL);
        carEntity.setId(1L);
        carEntity.setLicensePlate("AB1233");
        carEntity.setSeat_count(4);
        carEntity.setRating(CarRating.FOUR);
        ManufatureDO manufatureDO = new ManufatureDO();
        manufatureDO.setId(1L);
        manufatureDO.setModel("IM2017");
        manufatureDO.setModelYear("2017");
        manufatureDO.setName("Suzuki");
        carEntity.setManufatureDO(manufatureDO);
        return carEntity;
    }

    public static DriverDO driverEntityUnAssigned() {
        DriverDO driverDO = new DriverDO("user","user");
        return driverDO;
    }

    public static DriverDO driverEntityAssigned(){
        DriverDO driverDO = new DriverDO("user","user");
        driverDO.setCarDO(MockUtils.sampleCarEntity());
        return driverDO;
    }

}
