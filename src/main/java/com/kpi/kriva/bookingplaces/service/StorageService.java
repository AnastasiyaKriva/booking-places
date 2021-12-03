package com.kpi.kriva.bookingplaces.service;

import com.kpi.kriva.bookingplaces.repository.StorageRepository;
import com.kpi.kriva.bookingplaces.entity.StorageEntity;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public boolean insert(StorageEntity storage) {
        if (storageRepository.existsByNameAndDate(storage.getName(), storage.getDate())) {
            return false;
        }
        storageRepository.save(storage);
        return true;
    }

}
