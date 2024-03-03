package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByEmailAndPassword(String email, String password);

//    @Query(value = "select * from users\n"+
//                    "where User_id= :userid ", nativeQuery = true)
//    Optional<User> getUserById(@Param("UserId")UUID userid) ;



    User findUserByUsername(String username);

    @Modifying
    @Query(value = "update users set password =?1 where username=?2 ", nativeQuery = true)
    void saveNewPassword(String newpassword, String email);


    User findAllByUsernameAndPassword(String username, String password);
}
