package com.example.schooledrestapi.repository;

import com.example.schooledrestapi.db.MysqlDatabase;
import com.example.schooledrestapi.model.Course;
import com.example.schooledrestapi.model.NullCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
  private Connection conn;

  public CourseRepository() {
    conn = MysqlDatabase.getInstance().getConnection();
  }

  private Course transform(ResultSet rs) throws SQLException {
    Course course = new Course();
    course.setId(rs.getLong("id"));
    course.setName(rs.getString("name"));
    course.setDescription(rs.getString("description"));
    course.setAttendees(rs.getInt("attendees"));

    return course;
  }

  public List<Course> getAll() {
    List<Course> courses = new ArrayList<>();
    String sql = "SELECT * FROM courses";

    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      while(rs.next()) {
        Course course = transform(rs);
        courses.add(course);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return courses;
  }

  public Course get(int id) {
    Course course = null;
    String sql = "SELECT * FROM courses WHERE id=?";

    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if(!rs.next()) {
        return new NullCourse();
      }

      course = transform(rs);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return course;
  }

  public Course save(Course course) {
    String sql = "INSERT INTO courses (name, description, attendees) VALUES (?, ?, ?)";
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, course.getName());
      pstmt.setString(2, course.getDescription());
      pstmt.setInt(3, course.getAttendees());

      pstmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return course;
  }
}
