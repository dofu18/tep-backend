package com.main.timeshareexchangeplatform_backend.repository;

import com.main.timeshareexchangeplatform_backend.entity.Timeshare;
import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
//    Optional<User> findUserByEmailAndPassword(String email, String password);
//
//
//    User findUserByEmailAndPassword(String email, String password);

//    @Query(value = "select * from users\n"+
//                    "where User_id= :userid ", nativeQuery = true)
//    Optional<User> getUserById(@Param("UserId")UUID userid) ;

    @Query(value = "SELECT * FROM users WHERE user_id = :id", nativeQuery = true)
    User findUserByID(@Param("id") UUID id);

    @Query(value = "SELECT * FROM users u WHERE u.status = 0", nativeQuery = true)
    List<User> getAccountBanned();

    @Query(value = "SELECT * FROM users u WHERE u.status = 1", nativeQuery = true)
    List<User> getAccountActive();


    User findUserByUsername(String username);

    @Modifying
    @Query(value = "update users set password =?1 where username=?2 ", nativeQuery = true)
    void saveNewPassword(String newpassword, String email);


    User findAllByUsernameAndPassword(String username, String password);


    @Query(value = "SELECT * FROM users u WHERE u.user_id = :user_id", nativeQuery = true)
    User userDetail(@Param("user_id") UUID user_id);

    @Query("SELECT u FROM User u WHERE u.createDate >= :startDate AND u.createDate <= :endDate")
    List<User> findUsersCreatedWithinLast30Days(LocalDate startDate, LocalDate endDate);
}
