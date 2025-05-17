package com.chungquoc.xtpqredis.repository;

import com.chungquoc.xtpqredis.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Nếu DB có user username = "chung" → in ra true
     * Nếu không có → in ra false.
     * @param username
     * @return true or false
     */
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsById(String id);
}
