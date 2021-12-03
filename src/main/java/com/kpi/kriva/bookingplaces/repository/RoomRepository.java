package com.kpi.kriva.bookingplaces.repository;

import com.kpi.kriva.bookingplaces.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

    boolean existsByName(String name);

}
