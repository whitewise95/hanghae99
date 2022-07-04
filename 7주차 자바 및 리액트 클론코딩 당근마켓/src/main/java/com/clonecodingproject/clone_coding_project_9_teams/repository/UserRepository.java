package com.clonecodingproject.clone_coding_project_9_teams.repository;

import com.clonecodingproject.clone_coding_project_9_teams.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String authorization);
}
