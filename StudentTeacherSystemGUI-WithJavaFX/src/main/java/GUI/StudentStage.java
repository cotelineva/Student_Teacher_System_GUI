package com.example.tema6_v2.GUI;

import com.example.tema6_v2.Controller.RegistrationSystem;
import com.example.tema6_v2.Model.Course;
import com.example.tema6_v2.Model.Student;
import com.example.tema6_v2.Repository.CourseInMemoryRepo;
import com.example.tema6_v2.Repository.StudentInMemoryRepo;
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

public class StudentStage extends Stage {
    StudentStage(StudentInMemoryRepo sr, CourseInMemoryRepo cr, RegistrationSystem r) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome Student!");
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

        button.setOnAction(x -> {
            for(Student s: sr.findAll()){
                if(userTextField.getText().equals(s.getLastName())){
                    VBox vBox = new VBox();
                    Text sceneTitle1 = new Text("Student Menu");
                    sceneTitle1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                    Button buttonCredits = new Button("See your total credits");
                    Button buttonCourses = new Button("See available courses");
                    Button buttonRegister = new Button("Register to a course");

                    TextField textFieldCredits = new TextField();
                    TextField textFieldCourses = new TextField();
                    TextField textFieldRegister = new TextField();
                    TextField textFieldRegisterFeedback = new TextField();

                    Label label = new Label("Type the course name:");
                    Label emptyLabel = new Label("\n");

                    //to get the total credits
                    int totalCredits = s.getTotalCredits();
                    String stringCredits = Integer.toString(totalCredits);

                    //to get the names of the courses
                    String string = "";
                    for(Course c:cr.findAll()){
                        string += c.getName() + ",";
                    }
                    String stringCourses = string;

                    //to register to a course

                    buttonRegister.setOnAction(e -> {
                        for(Course c:cr.findAll()){
                            if(textFieldRegister.getText().equals(c.getName())){
                                r.register(c,s);

                                if(s.getEnrolledCourses().contains(c)){
                                    textFieldRegisterFeedback.setText("Registered!");
                                }
                                else{
                                    textFieldRegisterFeedback.setText("Not registered!");
                                }
                            }
                        }
                    });

                    buttonCredits.setOnAction(e -> textFieldCredits.setText(stringCredits));
                    buttonCourses.setOnAction(e -> textFieldCourses.setText(stringCourses));

                    vBox.getChildren().addAll(sceneTitle1,buttonCredits,textFieldCredits,buttonCourses,textFieldCourses,
                            emptyLabel,label,textFieldRegister,buttonRegister,textFieldRegisterFeedback);

                    Scene scene = new Scene(vBox,500,400);
                    this.setScene(scene);
                }
            }
            actionTarget.setFill(Color.FIREBRICK);
            actionTarget.setText("No student found.");
        });

        Scene scene = new Scene(grid, 600, 500);
        this.setScene(scene);
        this.show();
    }
}
