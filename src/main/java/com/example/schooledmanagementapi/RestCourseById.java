package com.example.schooledmanagementapi;

import com.example.schooledmanagementapi.model.CourseDetails;

@FunctionalInterface
public interface RestCourseById {
  CourseDetails find(Long id);
}
