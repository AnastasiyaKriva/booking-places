package com.kpi.kriva.bookingplaces.repository;

import com.kpi.kriva.bookingplaces.entity.ManagerStorageEntity;
import com.kpi.kriva.bookingplaces.entity.StorageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerStorageRepository extends CrudRepository<ManagerStorageEntity, Long> {

    boolean existsByStorage(StorageEntity storage);

}
