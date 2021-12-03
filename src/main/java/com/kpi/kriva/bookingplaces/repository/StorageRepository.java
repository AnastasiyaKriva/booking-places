package com.kpi.kriva.bookingplaces.repository;

import com.kpi.kriva.bookingplaces.entity.StorageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface StorageRepository extends CrudRepository<StorageEntity, Long> {

    boolean existsByNameAndDate(String name, Date date);

}
