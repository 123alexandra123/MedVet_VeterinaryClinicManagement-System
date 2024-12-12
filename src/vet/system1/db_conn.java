package vet.system1;

import java.sql.*;

public class db_conn
{

    Connection connection;
    Statement statement;


    public db_conn()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica_veterinara", "root", "654321Alex");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
