package com.example.schooledcourseapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Course {

  @Id //primary key
  @GeneratedValue(strategy = GenerationType.AUTO) // AUTO_INCREMENT
  private long id;

  private String name;
  private String description;
  private int attendees;
}
