package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufatureDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufatureDO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    @Autowired
    private ModelMapper modelMapper;

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarDO transformToCarDO(CarDTO carDTO) {
        CarDO carData = modelMapper.map(carDTO, CarDO.class);
        ManufatureDO manufatureEntity = modelMapper.map(carDTO.getManufatureDTO(), ManufatureDO.class);
        carData.setManufatureDO(manufatureEntity);
        return carData;
    }

    public CarDTO transformToCarDTO(CarDO carDO) {
        CarDTO carDTO = modelMapper.map(carDO, CarDTO.class);
        ManufatureDTO manufatureDTO = modelMapper.map(carDO.getManufatureDO(), ManufatureDTO.class);
        carDTO.setManufatureDTO(manufatureDTO);
        return carDTO;
    }
}
