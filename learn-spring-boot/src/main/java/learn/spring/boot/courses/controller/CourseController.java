package learn.spring.boot.courses.controller;

import learn.spring.boot.courses.bean.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    static List<Course> courseList = List.of(
            new Course(1, "Learn Microservices", "In28Minutes"),
            new Course(2, "Learn Full Stack with React", "In28Minutes")
    );

    // http://localhost:8080/courses
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseList;
    }

    // http://localhost:8080/courses/1
    @GetMapping("/courses/1")
    public Course getCourse() {
        return courseList.get(0);
    }
}
