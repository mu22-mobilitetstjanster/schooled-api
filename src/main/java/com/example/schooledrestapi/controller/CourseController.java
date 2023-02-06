package com.example.schooledrestapi.controller;

import com.example.schooledrestapi.model.Course;
import com.example.schooledrestapi.model.CourseNameDetails;
import com.example.schooledrestapi.model.NullCourse;
import com.example.schooledrestapi.service.CourseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

  @Autowired
  private CourseService courseService;

  @GetMapping("course")
  public ResponseEntity<List<Course>> getCourses() {
    List<Course> courses = courseService.getAll();

    if (courses.isEmpty()) {
      return ResponseEntity
              .status(204)
              .header("x-information", "No data was found in the database")
              .build();
    } else {
      return ResponseEntity.ok(courses);
    }
  }

  @GetMapping("course/name/{name}")
  public ResponseEntity<Course> getCourse(@PathVariable String name) {
    return ResponseEntity.ok(courseService.getBy(name));
  }

  @GetMapping("courses/name/{name}/{description}")
  public ResponseEntity<List<Course>> getCourses(@PathVariable String name, @PathVariable String description) {
    return ResponseEntity.ok(courseService.getAllBy(name));
  }

  @GetMapping("course/{courseId}")
  public ResponseEntity<CourseNameDetails> getCourse(@PathVariable int courseId) {
    Course course = courseService.get(courseId);

    if (course instanceof NullCourse) {
      return ResponseEntity
              .status(204)
              .header("x-information", "Course did not exist")
              .body(new CourseNameDetails(course));
    }

    return ResponseEntity.ok(new CourseNameDetails(course));
  }

  @PostMapping("course")
  public ResponseEntity<List<Course>> createCourse(@RequestBody Course course) {
    courseService.save(course);

    // subscribe to new courses

    List<Course> courses = courseService.getAll();
    return ResponseEntity.status(201).body(courses);
  }
}



  /*@DeleteMapping("course/{courseId}")
  public ResponseEntity<Course> deleteCourse(@PathVariable int courseId) {

    if(courseId > courses.size()) {
      return ResponseEntity
              .status(400)
              .header("x-error-msg", "Id is out of bounds, course does not exist with such id")
              .build();
    }


    Course matchedCourse = courses.stream().filter(course -> course.getId() == courseId).findFirst().get();
    courses.removeIf(course -> course.getId() == courseId);

    return ResponseEntity.status(200).body(matchedCourse);
  }
}*/
