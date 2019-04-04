package com.mytaxi.car;


import com.mytaxi.MockUtils;
import com.mytaxi.service.CarServiceImpl;
import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.dao.car.CarDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarServiceImplTest.class)
public class CarServiceImplTest {

    private CarServiceImpl carServiceImpl = null;

    private CarDao mockCarDao = null;

    private CarDO mockcarDO;

    private CarMapper mockCarMapper;

    private List<CarDO> mockCarList = new ArrayList<>();

    private static final Logger LOG = LoggerFactory.getLogger(CarServiceImplTest.class);

    @Before
    public void setup(){
        carServiceImpl = new CarServiceImpl();
        mockCarDao = Mockito.mock(CarDao.class);
        mockCarMapper = Mockito.mock(CarMapper.class);
        ReflectionTestUtils.setField(carServiceImpl,"carDao", mockCarDao);
        ReflectionTestUtils.setField(carServiceImpl,"carMapper",mockCarMapper);
        try{
            mockcarDO  = MockUtils.sampleCarEntity();
            Mockito.when(mockCarDao.find(Mockito.anyLong())).thenReturn(mockcarDO);
            mockCarList.add(mockcarDO);
            Mockito.when(mockCarDao.findAll()).thenReturn(mockCarList);
            Mockito.when(mockCarMapper.transformToCarDTO(Mockito.any(CarDO.class))).thenReturn(MockUtils.sampleCarDTO());
            Mockito.when(mockCarDao.findByAvailability(Mockito.anyBoolean())).thenReturn(mockCarList);
        }catch (EntityNotFoundException e){
            LOG.warn("Exception while setting mock objects",e.getMessage(),e);
        }
    }

    @Test
    public void testGetCar() throws EntityNotFoundException {
        CarDTO carDTO = carServiceImpl.getCar(1L);
        Assert.assertNotNull(carDTO);
    }

    @Test
    public void testCreateCar_success() throws ConstraintsViolationException {
        CarDTO carDTO = carServiceImpl.saveCar(MockUtils.sampleCarDTO());
        Assert.assertNotNull(carDTO);
    }

    @Test
    public void testDeleteCar_success() throws EntityNotFoundException {
        carServiceImpl.deleteCar(1L);
    }

    @Test
    public void testGetAllCar_success() throws EntityNotFoundException {
        List<CarDTO> carDTOList;
        carDTOList = carServiceImpl.getAllCars();
        Assert.assertNotNull(carDTOList);
        Assert.assertEquals(1,carDTOList.size());
    }

    @Test
    public void testCarByAvailability_succes() throws EntityNotFoundException{
        List<CarDTO> carDTOList;
        carDTOList = carServiceImpl.getAllCars(true);
        Assert.assertNotNull(carDTOList);
        Assert.assertEquals(1,carDTOList.size());
    }

}
