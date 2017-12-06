package com.fluxo.connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	
	static String status;

	public static Connection getMysqlConnection() {
		
		Connection connection = null;
		
		try {
			
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			
			String serverName = "192.168.0.112";
			String myDatabase = "rico";
			String url = "jdbc:mysql://" + serverName + "/" + myDatabase + "?autoReconnect=true&useSSL=false";
			String username = "root";
			String password = "123";
			
			connection = DriverManager.getConnection(url, username, password);
			
			if (connection != null) {
				status = "Conectado com sucesso!";
			} else {
				status = "N�o foi poss�vel realizar a conex�o.";
			}
			
			System.out.println(status);
			return connection;
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("O driver especificado n�o foi encontrado");
			return null;
		} catch (SQLException e) {
			
			System.out.println("SQLException - n�o foi poss�vel conectar ao banco de dados.");
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getConnectionStatus() {
		
		return status;
		
	}
	
	public static boolean closeConnection() {
		
		try {
			
			getMysqlConnection().close();
			return true;
			
		} catch (Exception e) {
			
			return false;
			
		}
	}
	
	public static Connection restartConnection() {
		
		closeConnection();
		
		return getMysqlConnection();
		
	}
}
