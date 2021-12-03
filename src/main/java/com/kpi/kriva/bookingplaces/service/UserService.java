package com.kpi.kriva.bookingplaces.service;

import com.kpi.kriva.bookingplaces.repository.RoleRepository;
import com.kpi.kriva.bookingplaces.repository.UserRepository;
import com.kpi.kriva.bookingplaces.entity.RoleEntity;
import com.kpi.kriva.bookingplaces.entity.UserEntity;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void insert() {
        RoleEntity roleUser = roleRepository.findByName("MANAGER");
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleUser);

        UserEntity user = new UserEntity();
        user.setUsername("Manager");
        user.setEmail("manager@gmail.com");
        user.setRoles(roles);
        userRepository.save(user);
    }

    public UserEntity get(long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Can not to get user by id: " + userId));
    }

}
