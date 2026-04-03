package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        //Try para hacer la conexión
        try (Connection connection = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()
        );  Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false); //Desactivar el commit automático

            //Try para intentar meter los datos --> Si no sale hace rollback
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DEPARTAMENTO (ID_DEP, NOMBRE_DEP) VALUES (?,?)")) {
                preparedStatement.setInt(1,1);
                preparedStatement.setString(2,"Recursos humanos");
                preparedStatement.executeUpdate();

                preparedStatement.setInt(1, 2);
                preparedStatement.setString(2, "Gestión de datos");
                preparedStatement.executeUpdate();

                preparedStatement.setInt(1, 3);
                preparedStatement.setString(2, "Supervisores");
                preparedStatement.executeUpdate();

                connection.commit(); //Hacer el commit al acabar
                System.out.println("Transacción exitosa");

            } catch (SQLException e) {
                connection.rollback(); //Hacer rollback de todo si hay más de una operación
                System.out.println("Error al hacer la operación -> " + e.getMessage());
            }
        } catch (SQLException e){
            System.out.println("ERROR -> "+e.getMessage());
        }
    }
}
