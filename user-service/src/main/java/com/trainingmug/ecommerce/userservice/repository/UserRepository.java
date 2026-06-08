package com.trainingmug.ecommerce.userservice.repository;

import com.trainingmug.ecommerce.userservice.entity.UserReponseDto;
import com.trainingmug.ecommerce.userservice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserReponseDto, Integer> {
    Optional<UserReponseDto> findByEmail(String email);

    @Modifying
    @Query("UPDATE UserReponseDto u SET u.lastLoggedIn = CURRENT_TIMESTAMP WHERE u.id = :id")
    void updateLastLoggedIn(@Param("id") int id);

    @Modifying
    @Query("UPDATE UserReponseDto u SET u.status = :status WHERE u.id = :id")
    void updateStatus(@Param("id") int id, @Param("status") Status status );
}
