package com.example.tema6_v2.Controller;

import com.example.tema6_v2.Repository.*;
import com.example.tema6_v2.Model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        RegistrationSystem r = new RegistrationSystem();
        StudentInMemoryRepo sr = new StudentInMemoryRepo();
        TeacherInMemoryRepo tr = new TeacherInMemoryRepo();
        CourseInMemoryRepo cr = new CourseInMemoryRepo();

        Person p1 = new Person("Ion","Ionescu");
        Person p3 = new Person("Ana","Enescu");

        Student s1 = new Student("Paul","Popescu",1000,0,Collections.<Course>emptyList());
        Student s2 = new Student("Andrei", "Popovici", 1020, 0, Collections.<Course>emptyList());
        Student s3 = new Student("Maria","Lovinescu",1050,0,Collections.<Course>emptyList());

        Teacher t1 = new Teacher("Ion","Ionescu",Collections.<Course>emptyList());
        Teacher t2 = new Teacher("Ana","Enescu",Collections.<Course>emptyList());

        Course c1 = new Course("BD", p1, 1, Collections.<Student>emptyList(), 10);
        Course c2 = new Course("MAP", p3,3,Collections.<Student>emptyList(),10);
        Course c3 = new Course("FP", p1, 2,Collections.<Student>emptyList(),13);
        Course c4 = new Course("ASC",p3,5,Collections.<Student>emptyList(),17);


        t1.setCourses(Arrays.asList(c1,c3));
        t2.setCourses(Arrays.asList(c2,c4));

        sr.save(s1);
        sr.save(s2);
        sr.save(s3);

        cr.save(c1);
        cr.save(c2);
        cr.save(c3);
        cr.save(c4);

        tr.save(t1);
        tr.save(t2);

        r.changeCredit(c1, 15, sr);

        System.out.println(s2.getTotalCredits());

        System.out.println();
        for(Course c:r.getAllCourses(cr)){
            System.out.println(c.getName() + " " + c.getTeacher() + " " + c.getCredits() + " " + c.getMaxEnrollment());
        }

        r.register(c2,s2);
        r.register(c3,s1);

        System.out.println(c2.getStudentsEnrolled().contains(s2) + " " + c3.getStudentsEnrolled().contains(s1));
        System.out.println(s2.getEnrolledCourses().contains(c2) + " " + s1.getEnrolledCourses().contains(c3));

        System.out.println();
        r.retrieveCoursesWithFreePlaces(cr);

        for(Student s:r.retrieveStudentsEnrolledForACourse(c2)){
            System.out.println(s.getFirstName() + " " + s.getLastName() + " " + s.getStudentId() + " " + s.getTotalCredits());
        }

        /*
        Scanner scanner = new Scanner(System.in);
        int n;
        int x = 1;

        while(x != 0){
            System.out.println("Welcome to the Registration System! \n" +
                    "\tPress 0: Exit \n" +
                    "\tPress 1: To register a student to a course\n" +
                    "\tPress 2: To see courses with the number of free places\n" +
                    "\tPress 3: To see which students are enrolled for a course\n" +
                    "\tPress 4: To see all available courses\n" +
                    "\tPress 5: To change the credits of a course\n" +
                    "\tPress 6: To delete a course\n" +
                    "\tPress 7: To add a course\n" +
                    "\tPress 8: To add a student\n" +
                    "\n" +
                    "Your key: ");

            n = scanner.nextInt();
            x = n;
            switch(x) {
                case 1:
                    System.out.println("Enter student id: ");
                    int id = scanner.nextInt();
                    for(Student s:sr.findAll()){
                        if(s.getStudentId() == id){
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("5");
                    break;
                case 6:
                    System.out.println("6");
                    break;
                case 7:
                System.out.println("7");
                break;
                case 8:
                    System.out.println("8");
                    break;
                case 0:
                    System.out.println("bye");
                    break;

            }
        }
        */

    }
}
