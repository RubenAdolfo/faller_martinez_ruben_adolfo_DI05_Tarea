package com.example.faller_martinez_ruben_adolfo_DI05_Tarea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class ArtistasController implements Initializable
{
    @FXML
    private ListView listView;
    Connection conn;
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:db/chinook.db");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Name FROM artists");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(resultSet.getString("Name"));
            }
            ObservableList<String> observableList = FXCollections.observableArrayList(result);
            listView.setItems(observableList);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void handlerInforme(ActionEvent actionEvent)
    {
        try
        {
            String jasperFilePath = "informes/informeArtista.jrxml";
            InputStream inputStream = MainInforme.class.getResourceAsStream(jasperFilePath);
            // Compilar el informe JRXML a un archivo Jasper
            System.out.println("Compilando : " + jasperFilePath);
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("artista", listView.getSelectionModel().getSelectedIndex() + 1);
            // Al informe compilado le cargamos los parametros y la conexión a la base de datos
            // como nuestro informe no tiene nada de eso proporcionamos objetos vacios.
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            // Mostramos el informe (el valor true al cerrar el informe se cierra la aplicación.
            JasperViewer.viewReport(jasperPrint);
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
}
