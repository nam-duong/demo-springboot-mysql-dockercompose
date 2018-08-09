package com.example.demospringbootmysql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@RestController
public class DemoSpringBootMysqlApplication {

    @Value("${MYSQL_HOST:127.0.0.1}")
    private String MYSQL_HOST;

    @Value("${MYSQL_PORT:3306}")
    private String MYSQL_PORT;

    @Value("${MYSQL_USER:root}")
    private String MYSQL_USER;

    @Value("${MYSQL_PASSWORD:password}")
    private String MYSQL_PASSWORD;

    @Value("${MYSQL_DATABASE:testdb}")
    private String MYSQL_DATABASE;

    private Connection connection;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootMysqlApplication.class, args);
	}

	@GetMapping("/hello")
	public String helloWorld() throws SQLException {
	    connectMySql();
		return "Hello World! " + connection.getMetaData().getUserName();
	}

	private void connectMySql() {
		try {
		    String sqlConnection = "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE + "?allowPublicKeyRetrieval=true&useSSL=false";
			connection = DriverManager
					.getConnection(sqlConnection,MYSQL_USER, MYSQL_PASSWORD);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		System.out.println("Connection Success fully!");
	}
}
