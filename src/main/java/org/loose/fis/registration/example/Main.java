package org.loose.fis.registration.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.registration.example.services.UserService;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        UserService.loadUsersFromFile();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration ");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
