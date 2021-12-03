package com.kpi.kriva.bookingplaces.service;

import com.kpi.kriva.bookingplaces.model.dto.ManagerStorageDTO;
import com.kpi.kriva.bookingplaces.repository.ManagerStorageRepository;
import com.kpi.kriva.bookingplaces.entity.ManagerStorageEntity;
import com.kpi.kriva.bookingplaces.util.StorageEntityFactory;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ManagerStorageService {

    private final ManagerStorageRepository managerStoragesRepository;

    private final UserService userService;

    public ManagerStorageService(ManagerStorageRepository managerStoragesRepository, UserService userService) {
        this.managerStoragesRepository = managerStoragesRepository;
        this.userService = userService;
    }

    public boolean insert(ManagerStorageEntity managerStorage) {
        if (managerStoragesRepository.existsByStorage(managerStorage.getStorage())) {
            return false;
        }
        managerStoragesRepository.save(managerStorage);
        return true;
    }

    public ResponseEntity<Map<String, Object>> addStorage(ManagerStorageDTO managerStorage) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (insert(getManagerStoragesEntity(managerStorage))) {
                map.put("Status", "added");
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            map.put("Status", "existed");
        } catch (NotFoundException e) {
            log.error("Can not to add new storage for manager with id: {}", managerStorage.getManagerId());
            map.put("Status", "error");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ManagerStorageEntity getManagerStoragesEntity(ManagerStorageDTO managerStorage) throws NotFoundException {
        ManagerStorageEntity managerStorages = new ManagerStorageEntity();
        managerStorages.setManager(userService.get(managerStorage.getManagerId()));
        managerStorages.setStorage(StorageEntityFactory.create(managerStorage));
        return managerStorages;
    }

}
