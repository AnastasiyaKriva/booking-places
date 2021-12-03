package com.kpi.kriva.bookingplaces.repository;

import com.kpi.kriva.bookingplaces.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {

}
