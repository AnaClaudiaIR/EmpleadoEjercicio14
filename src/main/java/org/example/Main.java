package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        //Crear conexión + statement
        try (Connection connection = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()
        );  Statement statement = connection.createStatement()) {
            System.out.println("Conectado con Oracle.");

            //Línea para crear la tabla
            String sql = "CREATE TABLE empleado ("+
                    "id NUMBER PRIMARY KEY, "+
                    "nombre VARCHAR(100), "+
                    "salario NUMBER(10,2))";
            statement.executeUpdate(sql); //Ejecutar la línea
            System.out.println("Tabla creada.");

        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
