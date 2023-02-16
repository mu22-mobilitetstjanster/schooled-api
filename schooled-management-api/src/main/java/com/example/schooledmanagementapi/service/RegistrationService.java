package com.example.schooledmanagementapi.service;

import com.example.schooledmanagementapi.RestCourseById;
import com.example.schooledmanagementapi.model.CourseDetails;
import com.example.schooledmanagementapi.model.Registration;
import com.example.schooledmanagementapi.model.RegistrationDetails;
import com.example.schooledmanagementapi.model.StudentDetails;
import com.example.schooledmanagementapi.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class RegistrationService {

  @Autowired
  private RegistrationRepository registrationRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RestCourseById restCourseById;

  public RegistrationDetails save(Registration registration) {
    CourseDetails courseDetails = restCourseById.find(registration.getId());
    /*ResponseEntity<CourseDetails> courseEntity = restTemplate
            .getForEntity("http://localhost:9092/course/" + registration.getCourseId(), CourseDetails.class);*/

    ResponseEntity<StudentDetails> studentEntity = restTemplate
            .getForEntity("http://localhost:9091/student/" + registration.getStudentId(), StudentDetails.class);

    System.out.println("Course name: " + courseDetails.getName());
    System.out.println("Student name: " + studentEntity.getBody().getName());

    registrationRepository.save(registration);

    return new RegistrationDetails(
            studentEntity.getBody().getName(),
            courseDetails.getName(),
            new Date());
  }
}
