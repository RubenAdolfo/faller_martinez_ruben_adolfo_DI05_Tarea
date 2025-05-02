package com.example.faller_martinez_ruben_adolfo_DI05_Tarea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class MainInformeController implements Initializable
{
    @FXML
    private final String urlDB ="jdbc:sqlite:db/chinook.db";
    public Button b;
    Connection conn;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            conn = DriverManager.getConnection(urlDB);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void handlerClientes(ActionEvent actionEvent)
    {
        try
        {
            String jasperFilePath = "informes/informeClientes.jrxml";
            InputStream inputStream = MainInforme.class.getResourceAsStream(jasperFilePath);
            // Compilar el informe JRXML a un archivo Jasper
            System.out.println("Compilando : " + jasperFilePath);
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            // Al informe compilado le cargamos los parametros y la conexión a la base de datos
            // como nuestro informe no tiene nada de eso proporcionamos objetos vacios.
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);
            // Mostramos el informe (el valor true al cerrar el informe se cierra la aplicación.
            JasperViewer.viewReport(jasperPrint);
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    public void handlerArtistas(ActionEvent actionEvent)
    {
        Scene scene = null;
        FXMLLoader fxmlLoader = new FXMLLoader(MainInforme.class.getResource("Artistas-view.fxml"));
        try
        {
            scene = new Scene(fxmlLoader.load());
        }
        catch (IOException e)
        {
            System.out.println("ERROR al cargar la ventana de artistas");
            e.printStackTrace();
        }
        Stage st = new Stage();
        st.setScene(scene);
        st.setTitle("Artistas");
        st.showAndWait();
    }
    public void handlerSalir(ActionEvent actionEvent)
    {
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
}