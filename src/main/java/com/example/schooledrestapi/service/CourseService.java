package com.example.schooledrestapi.service;

import com.example.schooledrestapi.model.Course;
import com.example.schooledrestapi.repository.CourseRepository;

import java.util.List;

public class CourseService {
  private CourseRepository courseRepository;

  public CourseService() {
    courseRepository = new CourseRepository();
  }

  public List<Course> getAll() {
    return courseRepository.getAll();
  }

  public Course get(int id) {
    return courseRepository.get(id);
  }

  public Course save(Course course) {
    if(course.getId() == 0) {
      return courseRepository.save(course);
    } else {
      return courseRepository.update(course);
    }
  }

}
