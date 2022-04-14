package com.example.tema6_v2.GUI;

import com.example.tema6_v2.Model.Course;
import com.example.tema6_v2.Model.Student;
import com.example.tema6_v2.Model.Teacher;
import com.example.tema6_v2.Repository.TeacherInMemoryRepo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeacherStage extends Stage {
    TeacherStage(TeacherInMemoryRepo tr){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome Teacher!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle,0,0,2,1);

        Label userName = new Label("Username: ");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);

        Button button = new Button("Log in");
        grid.add(button,1,4);

        Text actionTarget = new Text();
        grid.add(actionTarget,1,6);

        button.setOnAction(e -> {
            for(Teacher t: tr.findAll()){
                if(userTextField.getText().equals(t.getLastName())){
                    VBox vBox = new VBox();
                    Text sceneTitle1 = new Text("Teacher Menu");
                    sceneTitle1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                    Button buttonAllCourses = new Button("See all your courses");
                    Button buttonCourseName = new Button("Search course");
                    Button buttonEnrolled = new Button("See the students enrolled for your course");

                    TextField textFieldAllCourses = new TextField();
                    TextField textFieldEnrolled = new TextField();
                    TextField textFieldCourseName = new TextField();
                    TextField textFieldCourseNameFeedback = new TextField();

                    Label label = new Label("Type the course name:");
                    Label emptyLabel1 = new Label("\n");
                    Label emptyLabel2 = new Label("\n");

                    //see all courses
                    buttonAllCourses.setOnAction(z -> {
                        String string = "";
                        for(Course c:t.getCourses()){
                            string += c.getName() + ",";
                        }
                        textFieldAllCourses.setText(string);
                    });

                    //select course
                    buttonCourseName.setOnAction(x -> {
                        for(Course c: t.getCourses()){
                            if(textFieldCourseName.getText().equals(c.getName())){
                                textFieldCourseNameFeedback.setText("Course found! Press button to show enrolled students.");

                                //see enrolled students
                                buttonEnrolled.setOnAction(y -> {
                                    String student = "";
                                    for(Student s: c.getStudentsEnrolled()){
                                        student += s.getLastName() + " " + s.getStudentId() + ";";
                                    }
                                    textFieldEnrolled.setText(student);
                                });
                            }/*
                            else{
                                textFieldCourseNameFeedback.setText("Course not found, try again.");
                            }*/
                        }
                    });


                    vBox.getChildren().addAll(sceneTitle1,
                            textFieldAllCourses,buttonAllCourses,emptyLabel1,
                            label,textFieldCourseName,buttonCourseName,textFieldCourseNameFeedback,emptyLabel2,
                            buttonEnrolled,textFieldEnrolled);

                    Scene scene = new Scene(vBox,500,400);
                    this.setScene(scene);
                }
            }

            actionTarget.setFill(Color.FIREBRICK);
            actionTarget.setText("No teacher found.");
        });

        Scene scene = new Scene(grid, 600, 500);
        this.setScene(scene);
        this.show();
    }
}
