package com.udemy.bancodedadossql;

import com.udemy.bancodedadossql.db.DB;
import com.udemy.bancodedadossql.db.DbException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class BancoDeDadosSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoDeDadosSqlApplication.class, args);


		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DB.getConnection();

			st = conn.createStatement();

			rs = st.executeQuery("select * from department");

			while (rs.next()){
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			DB.closedResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}






	}

}
