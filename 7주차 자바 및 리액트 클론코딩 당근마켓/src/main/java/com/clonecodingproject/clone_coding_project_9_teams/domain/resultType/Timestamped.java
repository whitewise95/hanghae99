package com.clonecodingproject.clone_coding_project_9_teams.domain.resultType;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class })
public abstract class Timestamped {

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime modifiedAt;
}
