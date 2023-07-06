package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	//Metodo gerar uma conexao com o Banco de Dados
	
	private static Connection conn = null;
	
	public static Connection getConnection(){
		if(conn == null) {
			try {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			}
			catch(SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
		
		return conn;
	}
	
	//Metodo para fechar a conexao com o Banco de Dados 
	
	public static void closeConnection() {
		if(conn != null) { // Testando se a conexao foi instanciada
			try {
			conn.close();
			}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
			}
		}
	}

	//Criar uma metodo auxiliar para carregar as propriedades que estao salvas no db.propeties
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	//Método para fechar o objeto do tipo Statement 
	
	public static void closeStatement (Statement st) {
		if(st !=null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage()); 
			}
		}
	}
	//Método para fechar o objeto do tipo ResultSet 
	
	public static void closeResultSet (ResultSet rs) {
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage()); 
			}
		}
	}
	
}
