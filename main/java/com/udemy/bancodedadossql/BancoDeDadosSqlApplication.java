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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DB.getConnection();
//
//            preparedStatement = conn.prepareStatement(
//                    "INSERT INTO seller "
//                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
//                            + "VALUES "
//                            + "(?,?,?,?,?)",
//                    Statement.RETURN_GENERATED_KEYS);
//
//            preparedStatement.setString(1, "Carl Purple");
//            preparedStatement.setString(2, "carl@gmail.com");
//            preparedStatement.setDate(3, new java.sql.Date(sdf.parse("11/11/1994").getTime()));
//            preparedStatement.setDouble(4, 3000.0);
//            preparedStatement.setInt(5, 4);

            preparedStatement = conn.prepareStatement("insert into department (name) values ('D1'), ('D2')",
            PreparedStatement.RETURN_GENERATED_KEYS);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();

                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! id = " + id);
                }

            } else {
                System.out.println("No rows affected");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }


    }

}
