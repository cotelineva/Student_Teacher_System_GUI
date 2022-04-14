package com.example.tema6_v2.Repository;

import java.util.ArrayList;
import java.util.List;
import com.example.tema6_v2.Model.Course;

public class CourseInMemoryRepo implements CRUD_Repo<Course>{
    private List<Course> courses = new ArrayList<>();

    @Override
    public Course findOne(int id) {
        if(courses.contains(courses.get(id)))
            return courses.get(id);
        return null;
    }

    @Override
    public Iterable<Course> findAll() {
        return courses;
    }

    @Override
    public Course save(Course entity) {
        for(Course c:courses){
            if(c.equals(entity)){
                return c;
            }
        }
        courses.add(entity);
        return null;
    }

    @Override
    public Course delete(int id) {
        if(courses.contains(courses.get(id))){
            Course c = courses.get(id);
            courses.remove(courses.get(id));
            return c;
        }
        return null;
    }

    @Override
    public Course update(Course entity) {
        if(courses.contains(entity)){
            return null;
        }
        return entity;
    }

    @Override
    public String toString() {
        return "CourseInMemoryRepo{" +
                "courses=" + courses +
                '}';
    }
}
