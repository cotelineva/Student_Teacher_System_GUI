package com.example.tema6_v2.Repository;

import com.example.tema6_v2.Model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentInMemoryRepo implements CRUD_Repo<Student> {
    private List<Student> students = new ArrayList<>();

    @Override
    public Student findOne(int id) {
        if(students.contains(students.get(id)))
            return students.get(id);
        return null;
    }

    @Override
    public Iterable<Student> findAll() {
        return students;
    }

    @Override
    public Student save(Student entity) {
        for(Student s:students){
            if(s.equals(entity)){
                return s;
            }
        }
        students.add(entity);
        return null;
    }

    @Override
    public Student delete(int id) {
        if(students.contains(students.get(id))){
            Student s = students.get(id);
            students.remove(students.get(id));
            return s;
        }
        return null;
    }

    @Override
    public Student update(Student entity) {
        if(students.contains(entity)){
            return null;
        }
        return entity;
    }

    @Override
    public String toString() {
        return "StudentInMemoryRepo{" +
                "students=" + students +
                '}';
    }
}
