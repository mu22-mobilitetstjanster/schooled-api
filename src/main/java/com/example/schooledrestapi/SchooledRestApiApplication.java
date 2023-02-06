package com.example.schooledrestapi;

import com.example.schooledrestapi.model.Course;
import com.example.schooledrestapi.model.Student;
import com.example.schooledrestapi.repository.JpaCourseRepository;
import com.example.schooledrestapi.repository.JpaStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchooledRestApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchooledRestApiApplication.class, args);
  }

  @Bean
  public CommandLineRunner run(JpaStudentRepository studentRepository, JpaCourseRepository jpaCourseRepository) {
    return args -> {
      System.out.println("-------initalized database-------");
      Student student = new Student();
      student.setName("Bert");
      student.setOnline(false);

      studentRepository.save(student);

      Course course = new Course();
      course.setName("Math");

      jpaCourseRepository.save(course);
    };
  }

}
