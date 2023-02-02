package com.example.schooledrestapi.service;

import com.example.schooledrestapi.model.Course;
import com.example.schooledrestapi.repository.JpaCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

  @Autowired
  private JpaCourseRepository jpaCourseRepository;

  public List<Course> getAll() {
    return jpaCourseRepository.findAll();
  }

  public Course get(int id) {
    return jpaCourseRepository.getReferenceById((long) id);
  }

  public Course save(Course course) {
    return jpaCourseRepository.save(course);
  }

}
