package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByEmailAndPassword(String email, String password);

    @Query(value = "select * from users\n"+
                    "where User_id= :userid ", nativeQuery = true)
    Object getUserById(@Param("UserId")UUID userid) ;





    User findAllByUsernameAndPassword(String username, String password);
}
