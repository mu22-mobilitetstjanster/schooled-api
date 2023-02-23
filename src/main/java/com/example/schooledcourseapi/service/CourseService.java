package com.example.schooledcourseapi.service;

import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.springframework.stereotype.Service;

import com.example.schooledcourseapi.model.Course;
import com.example.schooledcourseapi.repository.JpaCourseRepository;


@Service
@Log4j2
public class CourseService {

  private JpaCourseRepository jpaCourseRepository;

  public CourseService(JpaCourseRepository jpaCourseRepository) {
    this.jpaCourseRepository = jpaCourseRepository;
  }

  public List<Course> getAll() {
    log.debug("All courses is being fetched");
    List<Course> courses = jpaCourseRepository.findAll();

    if(courses == null) {
      log.fatal("Courses was null, this is not supposed to happen");
    }

    if(courses.isEmpty()) {
      log.warn("Courses returned empty, verify with administrator");
    }

    return jpaCourseRepository.findAll(); // SELECT * FROM course;
  }

  public Course get(int id) {
    return jpaCourseRepository.getReferenceById((long) id); // SELECT * FROM course WHERE id=?
  }

  public Course save(Course course) {
    return jpaCourseRepository.save(course);
  }

  public Course getBy(String name) {
    return jpaCourseRepository.findCourseByName(name);
  }

  public List<Course> getAllByCourseName(String name) {
    return jpaCourseRepository.findCoursesByNameContains(name);
  }

  public List<Course> getAllBy(String query) {
    return jpaCourseRepository.findCoursesByNameContainsOrDescriptionContains(query, query);
  }

}
