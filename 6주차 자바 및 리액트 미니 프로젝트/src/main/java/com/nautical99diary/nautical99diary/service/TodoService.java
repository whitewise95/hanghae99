package com.nautical99diary.nautical99diary.service;

import com.nautical99diary.nautical99diary.config.jwt.JwtDecoder;
import com.nautical99diary.nautical99diary.domain.Todo;
import com.nautical99diary.nautical99diary.dto.*;
import com.nautical99diary.nautical99diary.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ch.qos.logback.classic.PatternLayout.HEADER_PREFIX;
import static com.nautical99diary.nautical99diary.config.handler.LoginSuccessHandler.TOKEN_TYPE;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final JwtDecoder jwtDecoder;
    private final TodoRepository todoRepository;
    private final TodoRepositoryEM repositoryJPQL;

    public List<Todo> getTodo(String day, String token) {
        String username = test(token);
        return todoRepository.findAllByGoalDayAndUsername(day, username);
    }

    public Todo createTodo(String day, TodoRequestDto todoRequestDto, String token) {
        String username = test(token);
        Todo todo = new Todo(day, todoRequestDto, username);
        todoRepository.save(todo);
        return todo;
    }

    public UpdateDto.TodoUpdate updateTodo(Long id, UpdateDto.TodoUpdate requestDto, String token) {
        Todo todo = todoRepository.findAllById(id);
        if (todo.getUsername().equals(test(token))) {
            return repositoryJPQL.updateTodo(requestDto);
        } else {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다");
        }

    }

    public UpdateDto.CompletionUpdate updateComplete(Long id, UpdateDto.CompletionUpdate requestDto, String token) {
        Todo todo = todoRepository.findAllById(id);
        if (todo.getUsername().equals(test(token))) {
            return repositoryJPQL.updateComplete(requestDto);
        } else {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다");
        }

    }

    public void deleteTodo(Long id, String token) {
        Todo todo = todoRepository.findAllById(id);
        if (todo.getUsername().equals(test(token))) {
            repositoryJPQL.deleteTodo(id);
        } else {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다");
        }

    }

    @Transactional
    public String test(String test) {
        validCheck(test);
        return jwtDecoder.decodeUsername(tokenProcess(test));
    }

    public String tokenProcess(String token) {
        String bearer = token.substring(
                0,
                TOKEN_TYPE.length()
        );

        if (bearer.equals("BEARER")) {
            return token.substring(
                    TOKEN_TYPE.length()
            );
        }
        throw new IllegalArgumentException("유효하지 않은 토큰");
    }

    private void validCheck(String token) {
        if (!validToken(token)) {
            throw new IllegalArgumentException("로그인이 필요한 기능입니다.");
        }
    }

    public boolean validToken(String token) {
        if (token == null || token.equals("") || token.length() < HEADER_PREFIX.length()) {
            return false;
        }
        return true;
    }
}
