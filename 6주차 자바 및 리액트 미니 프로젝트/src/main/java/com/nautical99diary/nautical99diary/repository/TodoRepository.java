package com.nautical99diary.nautical99diary.repository;

import com.nautical99diary.nautical99diary.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findById(Long id);

    List<Todo> findAllByGoalDayAndUsername(String day, String username);

    Todo findAllById(Long id);

}
