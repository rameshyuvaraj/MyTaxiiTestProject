package com.mytaxi;


import com.google.common.collect.Lists;
import com.mytaxi.service.DriverSearchServiceImpl;
import com.mytaxi.datatransferobject.CarRating;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.dao.car.CarDao;
import com.mytaxi.dao.driver.DriverDao;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DriverSearchServiceImplTest.class)
public class DriverSearchServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(DriverSearchServiceImplTest.class);

    private DriverSearchServiceImpl driverSearchServiceImpl;

    private DriverDao mockDriverDao;

    private CarDao mockCarDao;

    @Before
    public void setUp(){

        driverSearchServiceImpl = new DriverSearchServiceImpl();
        mockDriverDao = Mockito.mock(DriverDao.class);
        mockCarDao = Mockito.mock(CarDao.class);
        ReflectionTestUtils.setField(driverSearchServiceImpl, "driverDao", mockDriverDao);
        ReflectionTestUtils.setField(driverSearchServiceImpl, "carDao", mockCarDao);

    }


    @Test
    public void testGetDriversByUserName(){
        Mockito.when(mockDriverDao.find(Mockito.anyString())).thenReturn(new DriverDO("test","test"));
        DriverDTO driverDTO = driverSearchServiceImpl.getDriversByUserName("testuser");
        Assert.assertNotNull(driverDTO);
        Assert.assertTrue(driverDTO.getUsername().equals("test"));
    }

    @Test
    public  void testGetDriversByCarLicensePlate() throws EntityNotFoundException {
        DriverDO driverDO = MockUtils.driverEntityAssigned();
        driverDO.setCarDO(MockUtils.sampleCarEntity());
        List<DriverDO> mockList = Lists.newArrayList(driverDO);
        Mockito.when(mockDriverDao.findAll()).thenReturn(mockList);
        Mockito.when(mockCarDao.findByLicensePlateNo(Mockito.anyString())).thenReturn(MockUtils.sampleCarEntity());
        DriverDTO driverDTO = driverSearchServiceImpl.getDriversByCarLicensePlate("AB1233");
        Assert.assertNotNull(driverDTO);
        Assert.assertTrue(driverDTO.getUsername().equals("user"));
    }

    @Test
    public void testGetDriversWithGivenCarRating() throws EntityNotFoundException {
        DriverDO driverDO = MockUtils.driverEntityAssigned();
        driverDO.setCarDO(MockUtils.sampleCarEntity());
        List<DriverDO> mockList = Lists.newArrayList(driverDO);
        Mockito.when(mockDriverDao.findAll()).thenReturn(mockList);
        List<CarDO> mockCarList = Lists.newArrayList(MockUtils.sampleCarEntity());
        Mockito.when(mockCarDao.findByCarRating(Mockito.any(CarRating.class))).thenReturn(mockCarList);
        List<DriverDTO> driverDTOs = driverSearchServiceImpl.getDriversWithGivenCarRating(CarRating.FOUR.ordinal());
        Assert.assertNotNull(driverDTOs);
        Assert.assertTrue(driverDTOs.get(0).getUsername().equals("user"));
    }

    @Test
    public void testGetDriversWithCarsAssigned() throws EntityNotFoundException {
        DriverDO driverDO = MockUtils.driverEntityAssigned();
        driverDO.setCarDO(MockUtils.sampleCarEntity());
        List<DriverDO> mockList = Lists.newArrayList(driverDO);
        Mockito.when(mockDriverDao.findAll()).thenReturn(mockList);
        List<DriverDTO> driverDTOs = driverSearchServiceImpl.getDriversWithCarsAssigned();
        Assert.assertNotNull(driverDTOs);
        Assert.assertTrue(driverDTOs.get(0).getUsername().equals("user"));
    }

    @Test
    public void testGetDriversWithCarsUnAssigned() throws EntityNotFoundException {
        DriverDO driverDO = MockUtils.driverEntityUnAssigned();
        List<DriverDO> mockList = Lists.newArrayList(driverDO);
        Mockito.when(mockDriverDao.findAll()).thenReturn(mockList);
        List<DriverDTO> driverDTOs = driverSearchServiceImpl.getDriversWithCarsUnAssigned();
        Assert.assertNotNull(driverDTOs);
        Assert.assertTrue(driverDTOs.get(0).getUsername().equals("user"));
    }

}
