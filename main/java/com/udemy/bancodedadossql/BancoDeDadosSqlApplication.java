package com.udemy.bancodedadossql;

import com.udemy.bancodedadossql.db.DB;
import com.udemy.bancodedadossql.db.DbException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class BancoDeDadosSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoDeDadosSqlApplication.class, args);

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DB.getConnection();
            preparedStatement = conn.prepareStatement(
                    "UPDATE seller " +
                            "SET BaseSalary = BaseSalary + ? " +
                            "WHERE " +
                            "(DepartmentId = ?)"
            );

            preparedStatement.setDouble(1,200.0);
            preparedStatement.setInt(2,2);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Done! Rows Affected: " + rowsAffected);

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }




    }

}
