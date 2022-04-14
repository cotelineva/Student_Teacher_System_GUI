package com.example.tema6_v2.GUI;

import com.example.tema6_v2.Controller.RegistrationSystem;
import com.example.tema6_v2.Model.Course;
import com.example.tema6_v2.Model.Person;
import com.example.tema6_v2.Model.Student;
import com.example.tema6_v2.Model.Teacher;
import com.example.tema6_v2.Repository.CourseInMemoryRepo;
import com.example.tema6_v2.Repository.StudentInMemoryRepo;
import com.example.tema6_v2.Repository.TeacherInMemoryRepo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainTema6 extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StudentInMemoryRepo sr = new StudentInMemoryRepo();
        Student s1 = new Student("Paul","Popescu",1000,10, Collections.<Course>emptyList());
        Student s2 = new Student("Andrei", "Popovici", 1020, 0, Collections.<Course>emptyList());
        Student s3 = new Student("Maria","Lovinescu",1050,0,Collections.<Course>emptyList());

        sr.save(s1);
        sr.save(s2);
        sr.save(s3);

        CourseInMemoryRepo cr = new CourseInMemoryRepo();
        Person p1 = new Person("Ion","Ionescu");
        Person p3 = new Person("Ana","Enescu");
        Course c1 = new Course("BD", p1, 5, Collections.<Student>emptyList(), 10);
        Course c2 = new Course("MAP", p3,7,Collections.<Student>emptyList(),10);
        Course c3 = new Course("FP", p1, 4,Collections.<Student>emptyList(),13);
        Course c4 = new Course("ASC",p3,3,Collections.<Student>emptyList(),17);

        cr.save(c1);
        cr.save(c2);
        cr.save(c3);
        cr.save(c4);

        TeacherInMemoryRepo tr = new TeacherInMemoryRepo();

        Teacher t1 = new Teacher("Ion","Ionescu",Collections.<Course>emptyList());
        Teacher t2 = new Teacher("Ana","Enescu",Collections.<Course>emptyList());
        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c3);
        t1.setCourses(courses);
        t2.setCourses(List.of(c2,c4));
        tr.save(t1);
        tr.save(t2);


        RegistrationSystem r = new RegistrationSystem();

        r.register(c1,s1);
        r.register(c1,s2);
        r.register(c2,s3);

        StudentStage firstStage = new StudentStage(sr,cr,r);
        firstStage.setTitle("Student");

        TeacherStage secondStage = new TeacherStage(tr);
        secondStage.setTitle("Teacher");

    }
}
