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

            //Línea para crear la tabla
            String tablaDep =
                    "CREATE TABLE DEPARTAMENTO \n" +
                    "    ( \n" +
                    "     ID_dep     NUMBER (3)  NOT NULL , \n" +
                    "     nombre_dep VARCHAR2 (100)  NOT NULL \n" +
                    "    ) ";
            statement.executeUpdate(tablaDep);//Ejecutar la línea

            //Alterar tabla departamento --> Clave primaria
            String alterDep = "ALTER TABLE DEPARTAMENTO \n" +
                    "    ADD CONSTRAINT DEPARTAMENTO_PK PRIMARY KEY ( ID_dep )";
            statement.execute(alterDep);

            System.out.println("Tabla Departamento creada.");

            String tableEmp = "CREATE TABLE EMPLEADO2 \n" +
                    "    ( \n" +
                    "     ID_emp              NUMBER  NOT NULL , \n" +
                    "     nombre_emp          VARCHAR2 (100)  NOT NULL , \n" +
                    "     ID_dep NUMBER (3)   NOT NULL \n" +
                    "    )";
            statement.execute(tableEmp);

            String alterEmp1 = "ALTER TABLE EMPLEADO2 \n" +
                    "    ADD CONSTRAINT EMPLEADO2_PK PRIMARY KEY ( ID_emp )";
            statement.execute(alterEmp1);

            //Alterar tabla empelado para establecer clave foranea con dep
            String alterEmp2 = "ALTER TABLE EMPLEADO2 \n" +
                    "    ADD CONSTRAINT EMPLEADO2_DEPARTAMENTO_FK FOREIGN KEY \n" +
                    "    ( \n" +
                    "     ID_dep\n" +
                    "    ) \n" +
                    "    REFERENCES DEPARTAMENTO \n" +
                    "    ( \n" +
                    "     ID_dep\n" +
                    "    ) \n";
            statement.execute(alterEmp2);
            System.out.println("Tabla Empleado creada.");

        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
