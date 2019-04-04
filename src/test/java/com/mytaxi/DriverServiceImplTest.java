package com.mytaxi;


import com.google.common.collect.Lists;
import com.mytaxi.car.CarServiceImplTest;
import com.mytaxi.service.DriverServiceImpl;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.*;
import com.mytaxi.dao.car.CarDao;
import com.mytaxi.dao.driver.DriverDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarServiceImplTest.class)
public class DriverServiceImplTest {

    private DriverServiceImpl driverServiceImpl;

    private DriverDao mockDriverDao;

    private CarDao mockCarDao;

    @Before
    public void setUp() {
        mockDriverDao = Mockito.mock(DriverDao.class);
        mockCarDao = Mockito.mock(CarDao.class);
        driverServiceImpl = new DriverServiceImpl(mockDriverDao);
        ReflectionTestUtils.setField(driverServiceImpl, "carDao", mockCarDao);


    }

    @Test
    public void testSelectCar_success() throws EntityNotFoundException, ConstraintsViolationException,
            CarAlreadyInUseException, DriverNotOnlineException {
        DriverDO driverDO = MockUtils.driverEntityUnAssigned();
        driverDO.setOnlineStatus(OnlineStatus.ONLINE);
        Mockito.when(mockDriverDao.find(Mockito.anyLong())).thenReturn(driverDO);
        Mockito.when(mockDriverDao.find(Mockito.any(OnlineStatus.class))).thenReturn(Lists.newArrayList(driverDO));
        CarDO carEntity = MockUtils.sampleCarEntity();
        carEntity.setAssignedToDriver(false);
        Mockito.when(mockCarDao.find(Mockito.anyLong())).thenReturn(carEntity);
        driverServiceImpl.selectCar(1L, 1L);
    }

    @Test(expected = DriverNotOnlineException.class)
    public void testSelectCar_ExpectDriverOnlineException() throws EntityNotFoundException, ConstraintsViolationException,
            CarAlreadyInUseException, DriverNotOnlineException {
        Mockito.when(mockDriverDao.find(Mockito.anyLong())).thenReturn(MockUtils.driverEntityUnAssigned());
        CarDO carEntity = MockUtils.sampleCarEntity();
        carEntity.setAssignedToDriver(false);
        Mockito.when(mockCarDao.find(Mockito.anyLong())).thenReturn(carEntity);
        driverServiceImpl.selectCar(1L, 1L);
    }

    @Test
    public void testDeselectCar_success() throws EntityNotFoundException, ConstraintsViolationException,
            NoCarAssignedException {
        Mockito.when(mockDriverDao.find(Mockito.anyLong())).thenReturn(MockUtils.driverEntityAssigned());
        Mockito.when(mockCarDao.find(Mockito.anyLong())).thenReturn(MockUtils.sampleCarEntity());
        driverServiceImpl.deSelectCar(1L, 1L);
    }

    @Test(expected = CarAlreadyInUseException.class)
    public void testSelectCar_ExpectCarAlreadyInUseException() throws EntityNotFoundException,
            ConstraintsViolationException, CarAlreadyInUseException, DriverNotOnlineException {
        DriverDO driverDO = MockUtils.driverEntityAssigned();
        driverDO.setOnlineStatus(OnlineStatus.ONLINE);
        Mockito.when(mockDriverDao.find(Mockito.anyLong())).thenReturn(driverDO);
        Mockito.when(mockDriverDao.find(Mockito.any(OnlineStatus.class))).thenReturn(Lists.newArrayList(driverDO));
        CarDO carEntity = MockUtils.sampleCarEntity();
        carEntity.setAssignedToDriver(false);
        Mockito.when(mockCarDao.find(Mockito.anyLong())).thenReturn(carEntity);
        driverServiceImpl.selectCar(1L, 1L);
    }

    @Test(expected = NoCarAssignedException.class)
    public void testDeselectCar_ExpectNoCarAssignedException() throws EntityNotFoundException,
            ConstraintsViolationException, NoCarAssignedException {
        Mockito.when(mockDriverDao.find(Mockito.anyLong())).thenReturn(MockUtils.driverEntityUnAssigned());
        driverServiceImpl.deSelectCar(1L, 1L);
    }

}
