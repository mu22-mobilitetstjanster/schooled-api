package com.example.schooledrestapi.controller;

import com.example.schooledrestapi.model.Student;
import com.example.schooledrestapi.service.StudentService;
import com.example.schooledrestapi.ws.StudentStateSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

  @Autowired
  private StudentStateSocketHandler studentStateSocketHandler;

  @Autowired
  private StudentService studentService;

  @GetMapping("student/{studentId}")
  public ResponseEntity<Student> getStudentById(@PathVariable long studentId) {
    return ResponseEntity.ok(studentService.get(studentId));
  }

  @GetMapping("student")
  public ResponseEntity<List<Student>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAll());
  }

  @PostMapping("student")
  @PatchMapping("student")
  @PutMapping("student")
  public ResponseEntity<List<Student>> addStudent(@RequestBody Student student) {
    studentService.save(student);
    studentStateSocketHandler.broadcast(student.getName() + " was created");
    return getAllStudents();
  }

  @DeleteMapping("student")
  public ResponseEntity<List<Student>> deleteStudent(@RequestBody Student student) {
    studentService.delete(student.getId());
    return getAllStudents();
  }

  @DeleteMapping("student/{studentId}")
  public ResponseEntity<List<Student>> deleteStudent(@PathVariable long studentId) {
    studentService.delete(studentId);
    return getAllStudents();
  }

  @PatchMapping("student/online/{state}/{studentId}")
  public void setOnlineState(@PathVariable String state, @PathVariable long studentId) {
    Student newStudent = studentService.get(studentId);
    Student oldStudent = newStudent.clone();

    switch(state) {
      case "online": newStudent.setOnline(true); break;
      case "offline": newStudent.setOnline(false); break;
      default: throw new IllegalStateException(state + " was illdefined");
    }


    studentService.save(newStudent);

    // broadcast student changes
    studentStateSocketHandler.broadcast(oldStudent, newStudent);
  }
}
