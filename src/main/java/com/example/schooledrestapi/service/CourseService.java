package com.example.schooledrestapi.service;

import com.example.schooledrestapi.model.Course;
import com.example.schooledrestapi.repository.JpaCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

  @Autowired
  private JpaCourseRepository jpaCourseRepository;

  public List<Course> getAll() {
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
