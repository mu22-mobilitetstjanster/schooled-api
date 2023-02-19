package com.example.schooledstudentapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.schooledstudentapi.model.Student;
import com.example.schooledstudentapi.repository.JpaStudentRepository;

@SpringBootApplication
public class SchooledStudentApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchooledStudentApiApplication.class, args);
  }

  @Bean
  public CommandLineRunner run(JpaStudentRepository studentRepository) {
    return args -> {
      Student student = new Student();
      student.setName("Bertil");

      studentRepository.save(student);
    };
  }
}
