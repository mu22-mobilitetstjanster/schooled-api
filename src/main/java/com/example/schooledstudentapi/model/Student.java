package com.example.schooledstudentapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter @Getter
public class Student implements Cloneable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  private boolean isOnline;

  @Override
  public Student clone() {
    try {
      return (Student) super.clone();
    } catch (CloneNotSupportedException e) {
      Student student = new Student();

      student.setId(this.getId());
      student.setName(this.getName());
      student.setOnline(this.isOnline());

      return student;
    }
  }
}
