package com.example.schooledstudentapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schooledstudentapi.model.Student;
import com.example.schooledstudentapi.service.StudentService;
import com.example.schooledstudentapi.ws.StudentStateSocketHandler;

@RestController
@RequestMapping("/student/*")
public class StudentController {

  @Autowired
  private StudentStateSocketHandler studentStateSocketHandler;

  @Autowired
  private StudentService studentService;

  @GetMapping("{studentId}")
  public ResponseEntity<Student> getStudentById(@PathVariable long studentId) {
    return ResponseEntity.ok(studentService.get(studentId));
  }

  @GetMapping
  public ResponseEntity<List<Student>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAll());
  }

  @PostMapping
  @PatchMapping
  @PutMapping
  public ResponseEntity<List<Student>> addStudent(@RequestBody Student student) {
    studentService.save(student);
    studentStateSocketHandler.broadcast("new-student", student.getName() + " was created");
    return getAllStudents();
  }

  @DeleteMapping
  public ResponseEntity<List<Student>> deleteStudent(@RequestBody Student student) {
    studentService.delete(student.getId());
    return getAllStudents();
  }

  @DeleteMapping("{studentId}")
  public ResponseEntity<List<Student>> deleteStudent(@PathVariable long studentId) {
    studentService.delete(studentId);
    return getAllStudents();
  }

  @PatchMapping("online/{state}/{studentId}")
  public void setOnlineState(@PathVariable String state, @PathVariable long studentId) {
    Student newStudent = studentService.get(studentId);
    Student oldStudent = newStudent.clone();

    switch (state) {
      case "online":
        newStudent.setOnline(true);
        break;
      case "offline":
        newStudent.setOnline(false);
        break;
      default:
        throw new IllegalStateException(state + " was illdefined");
    }

    studentService.save(newStudent);

    // broadcast student changes
    if (newStudent.isOnline()) {
      studentStateSocketHandler.broadcast("online", oldStudent, newStudent);
    } else {
      studentStateSocketHandler.broadcast("offline", oldStudent, newStudent);
    }
  }
}
