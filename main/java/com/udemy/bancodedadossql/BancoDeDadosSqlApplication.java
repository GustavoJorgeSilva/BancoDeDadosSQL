package com.udemy.bancodedadossql;

import com.udemy.bancodedadossql.db.DB;
import com.udemy.bancodedadossql.db.DbException;
import com.udemy.bancodedadossql.db.DbIntegrityException;
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
        Statement statement = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            statement = conn.createStatement();

            int rows1 = statement.executeUpdate("UPDATE seller set BaseSalary = 2090 where DepartmentId = 1");

//            int x = 1;
//            if (x < 2){
//                throw new SQLException("FAKE ERROR");
//            }

            int rows2 = statement.executeUpdate("UPDATE seller set BaseSalary = 3090 where DepartmentId = 2");

            conn.commit();

            System.out.println("Rows 1 " + rows1);
            System.out.println("Rows 2 " + rows2);





        } catch (SQLException e){
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback! caused by: " + e.getMessage());
            }
        } finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }




    }

}
