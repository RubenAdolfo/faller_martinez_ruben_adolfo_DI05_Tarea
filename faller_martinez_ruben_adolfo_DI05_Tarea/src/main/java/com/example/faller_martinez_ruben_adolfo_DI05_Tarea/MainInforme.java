package com.example.faller_martinez_ruben_adolfo_DI05_Tarea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class MainInforme extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainInforme.class.getResource("MainInforme-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        BasicConfigurator.configure();
        launch();
    }
}