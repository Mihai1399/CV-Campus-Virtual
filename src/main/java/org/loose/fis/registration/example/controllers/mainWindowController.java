package org.loose.fis.registration.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import org.loose.fis.registration.example.exceptions.UserNameOrPasswordAreIncorrect;
import org.loose.fis.registration.example.services.UserService;
//import services.JSONService;
//import services.UserService;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class mainWindowController {
    @FXML
    private TextField PasswordL;
    @FXML
    private TextField UserNameL;
    @FXML
    private Text loginMessage;

    public void handleRegister(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Scene RegisterScene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(RegisterScene);
        window.show();
    }


}