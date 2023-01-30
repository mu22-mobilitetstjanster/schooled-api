package com.example.schooledrestapi.model;

public class NullCourse extends Course {

  public NullCourse() {
    super(-1,
            "Null course",
            "Course was not identified",
            -1);
    /*this.setId(-1);
    this.setName("Null course");
    this.setDescription("Course was not identified");
    this.setAttendees(-1);*/
  }
}
