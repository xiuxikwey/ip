package gui;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.Region;

import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException{
        Region root = (Region)FXMLLoader.load(getClass().getResource("/view/MainRegion.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/view/style.css");

        stage.setTitle("Oliver");
        stage.setScene(scene);
        stage.show();
    }
}
