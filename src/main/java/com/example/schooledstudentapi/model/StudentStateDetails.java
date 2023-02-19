package com.example.schooledstudentapi.model;

import lombok.Getter;

@Getter
public class StudentStateDetails {
  private Student oldState;
  private Student newState;

  public StudentStateDetails(Student oldState, Student newState) {
    this.oldState = oldState.clone();
    this.newState = newState.clone();
  }
}
