package Boletin_01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ej_07 {

	public static void main(String[] args) {
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "root");
			
			String sql = "SELECT * FROM empleados WHERE salario > 1000 AND salario < 2500";
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next())	{
				int num_empleado = resultadoSelect.getInt(1);
				String nombre = resultadoSelect.getString(2);
				String puesto = resultadoSelect.getString(3);
				double salario = resultadoSelect.getDouble(6);
				System.out.println(num_empleado + " || " + nombre + " || " + puesto + " || " + salario);
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
