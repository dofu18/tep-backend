package com.main.timeshareexchangeplatform_backend.service;

import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User getUserByUsername(String username);

    @Query(
            value = "select * from User",
            nativeQuery = true)
    List<User> getAllUser();
}
