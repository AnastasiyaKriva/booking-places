package com.kpi.kriva.bookingplaces.repository;

import com.kpi.kriva.bookingplaces.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
