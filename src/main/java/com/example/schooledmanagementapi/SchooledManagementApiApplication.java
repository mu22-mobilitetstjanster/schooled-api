package com.example.schooledmanagementapi;

import com.example.schooledmanagementapi.model.CourseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SchooledManagementApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchooledManagementApiApplication.class, args);
  }

  @Bean
  public RestCourseById restCourseById() {
    return id -> restTemplate().getForEntity("http://localhost:9092/course/" + id, CourseDetails.class).getBody();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
