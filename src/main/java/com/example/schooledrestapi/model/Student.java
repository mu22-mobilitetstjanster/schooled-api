package com.example.schooledrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
