package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "<h1>Databse is alive</h1>";
	}

	@RequestMapping("/getColors")
	public String getColors(){
        String SQL = "";
		String response = "";
		String connectionString ="jdbc:sqlserver://davidsazuredatabase.database.windows.net:1433;database=AzureDatabase;user=DaveC@davidsazuredatabase;password={9027018Dc};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        
		SQL = "select * from rgb";

		try {
			Connection con = DriverManager.getConnection(connectionString);
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(SQL);
			while (result.next()) {
				response += result.getString("color");
			}
			con.close();
		} catch (SQLException e) {
			response = e.getMessage();
		}
         
         return response;
	}
}