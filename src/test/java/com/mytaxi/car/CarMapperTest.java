package com.mytaxi.car;


import com.mytaxi.MockUtils;
import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.*;
import com.mytaxi.domainobject.CarDO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarMapperTest.class)
public class CarMapperTest {

    private CarMapper carMapperObject = null;

    @Before
    public void setUp(){
        carMapperObject = new CarMapper();
        ModelMapper modelMapper = new ModelMapper();
        ReflectionTestUtils.setField(carMapperObject,"modelMapper",modelMapper);
    }

    @Test
    public void test_transformCarDTOtoCarDO(){
        CarDTO carDTO = MockUtils.sampleCarDTO();
        CarDO carDO= carMapperObject.transformToCarDO(carDTO);
        Assert.assertNotNull(carDO);
        Assert.assertEquals(MockUtils.sampleCarEntity().getLicensePlate(),carDO.getLicensePlate());
        Assert.assertEquals(MockUtils.sampleCarEntity().getManufatureDO().getModel(),carDO.getManufatureDO().getModel());
    }

    @Test
    public void test_transformCarDOtoCarDTO(){
        CarDO carDO  = MockUtils.sampleCarEntity();
        CarDTO carDTO = carMapperObject.transformToCarDTO(carDO);
        Assert.assertNotNull(carDTO);
        Assert.assertEquals(MockUtils.sampleCarDTO().getLicensePlate(),carDTO.getLicensePlate());
        Assert.assertEquals(MockUtils.sampleCarDTO().getManufatureDTO().getModel(),carDTO.getManufatureDTO().getModel());
    }



}
