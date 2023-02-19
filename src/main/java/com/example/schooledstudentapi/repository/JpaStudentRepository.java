package com.example.schooledstudentapi.repository;

import com.example.schooledstudentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public interface JpaStudentRepository extends JpaRepository<Student, Long> {
}
