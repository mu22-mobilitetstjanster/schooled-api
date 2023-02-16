package com.example.schooledmanagementapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class RegistrationDetails {
  private String studentName;
  private String courseName;
  private Date registrationDate;
}
