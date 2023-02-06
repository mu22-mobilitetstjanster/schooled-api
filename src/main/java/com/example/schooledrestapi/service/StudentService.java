package com.example.schooledrestapi.service;

import com.example.schooledrestapi.model.Student;
import com.example.schooledrestapi.repository.JpaStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

  @Autowired
  private JpaStudentRepository studentRepository;

  public List<Student> getAll() {
    return studentRepository.findAll();
  }

  public Student get(long id) {
    return studentRepository.getReferenceById(id);
  }

  public Student save(Student student) {
    return studentRepository.save(student);
  }

  public void delete(long studentId) {
    studentRepository.deleteById(studentId);
  }
}
