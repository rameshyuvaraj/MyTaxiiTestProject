package com.mytaxi.car;

import com.google.common.collect.Lists;
import com.mytaxi.MockUtils;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.dao.car.CarDao;
import com.mytaxi.dao.car.DefaultCarDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DefaultCarDaoImplTest.class)
public class DefaultCarDaoImplTest {

    private CarDao defaultCarDao;

    private CarRepository mockCarRepository;

    @Before
    public void setUp() {
        mockCarRepository = Mockito.mock(CarRepository.class);
        defaultCarDao = new DefaultCarDaoImpl(mockCarRepository);
        ReflectionTestUtils.setField(defaultCarDao, "carRepository", mockCarRepository);
        List<CarDO> list = Lists.newArrayList(MockUtils.sampleCarEntity());
        Mockito.when(mockCarRepository.findAll()).thenReturn(list);
        Mockito.when(mockCarRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(MockUtils.
                sampleCarEntity()));
        Mockito.when(mockCarRepository.save(Mockito.any(CarDO.class))).thenReturn(MockUtils.sampleCarEntity());
    }

    @Test
    public void testfindAll() throws EntityNotFoundException {
        List<CarDO> list = defaultCarDao.findAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testFind() throws EntityNotFoundException{
        CarDO carDO = defaultCarDao.find(1L);
        Assert.assertEquals(MockUtils.sampleCarEntity().getLicensePlate(),carDO.getLicensePlate());
    }
}
