package com.naveed.emsbackendv2.model.repository;

import com.naveed.emsbackendv2.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUuid(String uuid);
    Optional<User> findByEmail(String email); // Forget password/Auth کے لیے
}