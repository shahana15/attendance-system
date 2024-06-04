package com.threeaxislabs.ams.repository;

import com.threeaxislabs.ams.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetail, Integer> {
    Optional<UserDetail> findByName(String username);
}
