package com.clonecodingproject.clone_coding_project_9_teams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CloneCodingProject9TeamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloneCodingProject9TeamsApplication.class, args);
    }

}
