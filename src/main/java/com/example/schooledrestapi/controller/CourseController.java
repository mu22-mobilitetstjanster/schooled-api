package com.example.schooledrestapi.controller;

import com.example.schooledrestapi.model.Course;
import com.example.schooledrestapi.model.CourseNameDetails;
import com.example.schooledrestapi.model.NullCourse;
import com.example.schooledrestapi.service.CourseService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

  private List<Course> courses;

  private CourseService courseService;

  public CourseController() {
    courseService = new CourseService();
  }

  @GetMapping("course/search/{query}")
  public List<Course> getSearchResult(@PathVariable String query) {
    return courses.stream().filter(course -> course.getName().contains(query)).toList();
  }

  @GetMapping("course")
  public ResponseEntity<List<Course>> getCourses() {
    List<Course> courses = courseService.getAll();

    if(courses.isEmpty()) {
      return ResponseEntity
              .status(204)
              .header("x-information", "No data was found in the database")
              .build();
    } else {
      return ResponseEntity.ok(courses);
    }
  }

  @GetMapping("course/{courseId}")
  public ResponseEntity<CourseNameDetails> getCourse(@PathVariable int courseId) {
    Course course = courseService.get(courseId);

    if(course instanceof NullCourse) {
      return ResponseEntity
              .status(204)
              .header("x-information", "Course did not exist")
              .body(new CourseNameDetails(course));
    }

    return ResponseEntity.ok(new CourseNameDetails(course));
  }

    /*
    Implementera en patchmapping
    Uppdatera en kurs med hjälp av dess Id
  */

  @PostMapping
  @PatchMapping
  @RequestMapping("course")
  public ResponseEntity<List<Course>> saveCourse(@RequestBody Course course) {
    courseService.save(course);

    List<Course> courses = courseService.getAll();
    return ResponseEntity.status(201).body(courses);
  }


  @DeleteMapping("course/{courseId}")
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
}
