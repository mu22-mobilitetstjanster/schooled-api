package com.example.schooledrestapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Course {
  private long id;
  private String name;
  private String description;
  private int attendees;
}
