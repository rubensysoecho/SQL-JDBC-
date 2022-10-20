package Boletin_01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ej_06 {
	
	public static void main(String[] args) {

		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "root");
			
			String sql = "SELECT * FROM departamentos ORDER BY NOMBRE";
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next()) {
				int num_departamento = resultadoSelect.getInt(1);
				String nombre = resultadoSelect.getString(2);
				String localidad = resultadoSelect.getString(3);
				
				System.out.println(num_departamento + " || " + nombre + " || " + localidad);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
