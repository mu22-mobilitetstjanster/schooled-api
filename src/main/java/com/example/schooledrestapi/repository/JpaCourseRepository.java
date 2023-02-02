package com.example.schooledrestapi.repository;

import com.example.schooledrestapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Long> { //Objekt och id datatyp
}
