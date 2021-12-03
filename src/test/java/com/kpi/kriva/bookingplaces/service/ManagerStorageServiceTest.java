package com.kpi.kriva.bookingplaces.service;

import com.kpi.kriva.bookingplaces.model.dto.ManagerStorageDTO;
import com.kpi.kriva.bookingplaces.repository.ManagerStorageRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ManagerStorageServiceTest {

    @Autowired
    private ManagerStorageService managerStoragesService;

    @MockBean
    private ManagerStorageRepository managerStoragesRepository;

    @MockBean
    private UserService userService;

    @Test
    public void addStorage_ManagerStoragesEntity_CallAddStorageMethodWithManagerStoragesEntity() throws NotFoundException {
        ManagerStorageDTO managerStorageDTO = new ManagerStorageDTO();
        managerStorageDTO.setManagerId(1L);

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("Status", "added");

        ResponseEntity<Map<String, Object>> expectedResponseEntity =
                new ResponseEntity<>(expectedMap, HttpStatus.OK);

        ResponseEntity<Map<String, Object>> actualResponseEntity = managerStoragesService.addStorage(managerStorageDTO);

        assertEquals(expectedResponseEntity, actualResponseEntity);

        verify(managerStoragesRepository, times(1)).save(ArgumentMatchers.any());
        verify(userService,  times(1)).get(1L);
    }

    @Test
    public void addStorage_ManagerStoragesEntity_CallAddStorageMethodWithException() throws NotFoundException {
        ManagerStorageDTO managerStorageDTO = new ManagerStorageDTO();
        managerStorageDTO.setManagerId(1L);

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("Status", "error");

        ResponseEntity<Map<String, Object>> expectedResponseEntity =
                new ResponseEntity<>(expectedMap, HttpStatus.OK);

        when(userService.get(anyLong())).thenThrow(new NotFoundException(anyString()));

        ResponseEntity<Map<String, Object>> actualResponseEntity = managerStoragesService.addStorage(managerStorageDTO);

        assertEquals(expectedResponseEntity, actualResponseEntity);

        verify(userService,  times(1)).get(1L);
        verify(managerStoragesRepository, times(0)).existsByStorage(any());
    }

    @Test
    public void addStorage_ManagerStoragesEntity_CallAddStorageMethodWithManagerStoragesEntityExisted()
            throws NotFoundException {
        ManagerStorageDTO managerStorageDTO = new ManagerStorageDTO();
        managerStorageDTO.setManagerId(1L);

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("Status", "existed");

        ResponseEntity<Map<String, Object>> expectedResponseEntity =
                new ResponseEntity<>(expectedMap, HttpStatus.OK);

        when(managerStoragesRepository.existsByStorage(any())).thenReturn(true);

        ResponseEntity<Map<String, Object>> actualResponseEntity = managerStoragesService.addStorage(managerStorageDTO);

        assertEquals(expectedResponseEntity, actualResponseEntity);

        verify(userService,  times(1)).get(1L);
        verify(managerStoragesRepository, times(1)).existsByStorage(any());
    }

}