package com.example.tema6_v2.Repository;

import java.util.ArrayList;
import java.util.List;
import com.example.tema6_v2.Model.Teacher;

public class TeacherInMemoryRepo implements CRUD_Repo<Teacher>{
    private List<Teacher> teachers = new ArrayList<>();

    @Override
    public Teacher findOne(int id) {
        if(teachers.contains(teachers.get(id)))
            return teachers.get(id);
        return null;
    }

    @Override
    public Iterable<Teacher> findAll() {
        return teachers;
    }

    @Override
    public Teacher save(Teacher entity) {
        for(Teacher t:teachers){
            if(t.equals(entity)){
                return t;
            }
        }
        teachers.add(entity);
        return null;
    }

    @Override
    public Teacher delete(int id) {
        if(teachers.contains(teachers.get(id))){
            Teacher t = teachers.get(id);
            teachers.remove(teachers.get(id));
            return t;
        }
        return null;
    }

    @Override
    public Teacher update(Teacher entity) {
        if(teachers.contains(entity)){
            return null;
        }
        return entity;
    }

    @Override
    public String toString() {
        return "TeacherInMemoryRepo{" +
                "teachers=" + teachers +
                '}';
    }
}
