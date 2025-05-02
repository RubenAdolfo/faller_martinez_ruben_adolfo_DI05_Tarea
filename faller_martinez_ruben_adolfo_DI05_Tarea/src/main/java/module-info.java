module com.example.btninformes {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires log4j;
    requires org.xerial.sqlitejdbc;
    requires java.sql;


    opens com.example.faller_martinez_ruben_adolfo_DI05_Tarea;
    exports com.example.faller_martinez_ruben_adolfo_DI05_Tarea;
}