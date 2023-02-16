package com.example.schooledcourseapi.repository;

import com.example.schooledcourseapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Long> { //Objekt och id datatyp
  // SELECT * FROM course WHERE name=?
  Course findCourseByName(String name);

  //SELECT * FROM course LIKE name=%name%;
  List<Course> findCoursesByNameContains(String name);

  //SELECT * FROM course LIKE name=%query1% OR description=%query2%;
  List<Course> findCoursesByNameContainsOrDescriptionContains(String query1, String query2);


}
