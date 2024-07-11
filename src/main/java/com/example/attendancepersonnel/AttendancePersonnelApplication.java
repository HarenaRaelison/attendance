package com.example.attendancepersonnel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AttendancePersonnelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendancePersonnelApplication.class, args);
    }

}
