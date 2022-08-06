package com.nautical99diary.nautical99diary.domain;

import com.nautical99diary.nautical99diary.domain.resultType.Timestamped;
import com.nautical99diary.nautical99diary.dto.TodoRequestDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Todo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private boolean checkComplete;

    @Column(nullable = false)
    private String todo;

    @Column(nullable = false)
    private String goalDay;

    @Column(nullable = false)
    private String username;

    public Todo(String day, TodoRequestDto todoRequestDto, String username) {
        this.goalDay = day;
        this.todo = todoRequestDto.getTodo();
        this.checkComplete = todoRequestDto.isCheckComplete();
        this.username = username;
    }
}
