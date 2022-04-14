package com.example.tema6_v2.Controller;

import java.util.ArrayList;
import java.util.List;
import com.example.tema6_v2.Model.*;
import com.example.tema6_v2.Repository.*;

public class RegistrationSystem{

    /**
     * @param course - the course to be registered to, must not be null
     * @param student - the student trying to enroll to course
     * @return true if successfully enrollen or false if not
     */
    public boolean register(Course course, Student student){
        //falls der Student zu viele Kredite hat
        if(student.getTotalCredits() + course.getCredits() > 30){
            System.out.println("Du hast zu viele Kredite, wahle ein anderes Kurs!");
            return false;
        }
        //falls der Kurs keine Platze mehr hat
        if(course.getStudentsEnrolled().size() == course.getMaxEnrollment()){
            System.out.println("Den Kurs hat keine freie Platze mehr! ");
            return false;
        }

        //wir fugen den Kurs in der Kursliste des Studentens hinzu
        student.enrolledCourses.add(course);

        //wir addieren die Kredite des Kurses zu die totale Kredite des Studenten
        student.totalCredits += course.getCredits();

        //wir fugen den Student in die Liste der enrolled Students des Kurses
        course.studentsEnrolled.add(student);

        return true;
    }

    /**
     * @return list of courses with available places
     */
    public List<Course> retrieveCoursesWithFreePlaces(CourseInMemoryRepo cr){
        List<Course> availableCourses = new ArrayList<>();
        List<Integer> freiePlatze = new ArrayList<>();
        int anzahl;
        //in die var anzahl rechnen wir die gebliebenen Platze
        //in freie Platze fugen wir alle Platze fur alle Kurse

        for(Course c: cr.findAll()){
            if(c.getMaxEnrollment() - c.getStudentsEnrolled().size() > 0){
                availableCourses.add(c);
                anzahl = c.getMaxEnrollment() - c.getStudentsEnrolled().size();
                freiePlatze.add(anzahl);
            }
        }

        int i=0;
        for(Course c:availableCourses){
            System.out.println("Course:" + c.getName() +" "+c.getTeacher() +" "+ c.getMaxEnrollment() + " "  +" hat:" + freiePlatze.get(i) + " freie Platze.");
            i++;
        }
        return availableCourses;
    }

    /**
     * @param course course with enrolled students
     * @return the enrolled students of the course
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        return course.getStudentsEnrolled();
    }

    /**
     * @return all courses with available places
     */
    public List<Course> getAllCourses(CourseInMemoryRepo cr){
        List<Course> availableCourses = new ArrayList<>();

        for(Course c:cr.findAll()){
            if(c.getMaxEnrollment() - c.getStudentsEnrolled().size() > 0){
                availableCourses.add(c);
            }
        }
        return availableCourses;
    }

    /**
     * @param course course to be updated
     * @param credit new update value for course
     */
    public void changeCredit(Course course, int credit, StudentInMemoryRepo sr){
        //wir loschen die Kredite des Kurses bevor wir sie verandern
        int sum;
        for(Student s:sr.findAll()){
            sum = 0;
            sum += s.getTotalCredits() - course.getCredits();
        }
        //wir verandern die kredite
        course.setCredits(credit);

        //wir addieren die neue kredite zu den totalen kredite des Stundenten
        for(Student s:sr.findAll()){
            if(s.getEnrolledCourses().contains(course)) {
                sum = 0;
                sum += s.getTotalCredits() + course.getCredits();
                s.setTotalCredits(sum);
            }
        }
    }

    /**
     * @param course must not be null, course will be deleted
     */
    public void deleteCourse(Course course, TeacherInMemoryRepo tr, StudentInMemoryRepo sr, CourseInMemoryRepo cr){
        //wir loschen den kurs aus der liste des lehrers
        for(Teacher t:tr.findAll()){
            t.getCourses().remove(course);
        }

        //wir loeschen den kurs aus der liste der angemeldeten studenten
        for(Student s:sr.findAll()){
            s.getEnrolledCourses().remove(course);
        }

        //wir suchen den id des kurses, sodass wir es loschen konnen
        int i = 0;
        for (Course c:cr.findAll()){
            if(course.equals(c)){
                cr.delete(i);   //wir loschen den kurs
                break;
            }
            i++;
        }
    }
}
