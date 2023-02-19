package com.example.schooledcourseapi.model;

import lombok.Getter;

@Getter
public class CourseNameDetails {
  private String name;
  private String description;

  public CourseNameDetails(Course course) {
    this.name = course.getName();
    this.description = course.getDescription();
  }
}
