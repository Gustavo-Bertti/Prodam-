package com.Farm.Singleton;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.Farm.conexao.Credenciais;

public class ConnectionManager {
	private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private static final String USER = Credenciais.user;
	private static final String PWD = Credenciais.pwd;
	
	private static ConnectionManager manager;

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ConnectionManager() {
	}

	public static ConnectionManager getInstance() throws IOException {
		if (manager == null) {
			manager = new ConnectionManager();
		}
		return manager;
	}
}
