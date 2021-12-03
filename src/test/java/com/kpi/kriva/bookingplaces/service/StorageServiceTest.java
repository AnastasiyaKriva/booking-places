package com.kpi.kriva.bookingplaces.service;

import com.kpi.kriva.bookingplaces.entity.StorageEntity;
import com.kpi.kriva.bookingplaces.model.Location;
import com.kpi.kriva.bookingplaces.model.Room;
import com.kpi.kriva.bookingplaces.model.Storage;
import com.kpi.kriva.bookingplaces.model.StorageStatus;
import com.kpi.kriva.bookingplaces.repository.StorageRepository;
import com.kpi.kriva.bookingplaces.util.StorageEntityFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StorageServiceTest {

    @Autowired
    private StorageService storageService;

    @MockBean
    private StorageRepository storageRepository;

    @Test
    public void insertSuccessful() {
        Storage storage = new Storage();
        storage.setName("Test storage");
        storage.setPrice(150.0);
        Date date = new Date(System.currentTimeMillis());
        storage.setDate(date);
        storage.setLocation(new Location("Country", "City", "Street", "Build"));
        storage.setRoom(new Room("First room",20, 30));
        storage.setStatus(StorageStatus.NOT_CONFIRMED.toString());

        StorageEntity storageEntity = StorageEntityFactory.create(storage);

        boolean isStorageCreated = storageService.insert(storageEntity);

        assertTrue(isStorageCreated);

        Mockito.verify(storageRepository, Mockito.times(1)).save(storageEntity);
    }

}