package Boletin_01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ej_08 {

	public static void main(String[] args) {
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "root");

			String sql = "SELECT * FROM departamentos ORDER BY NOMBRE";
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			int total_departamentos = 0;
			
			while(resultadoSelect.next()) {
				total_departamentos++;				
			}

			sql = "SELECT * FROM empleados";
			sentenciaSelect = conexion.prepareCall(sql);
			resultadoSelect = sentenciaSelect.executeQuery();
			
			int total_empleados = 0;
			double max_salario = 0;
			double suma_salario = 0;
			double media_salario = 0;
			
			while(resultadoSelect.next())	{
				double salario = resultadoSelect.getDouble(6);
				suma_salario += salario;
				if (salario > max_salario)	{
					max_salario = salario;
				}
				total_empleados++;
			}
			
			media_salario = suma_salario/total_empleados;
			
			System.out.println("TOTAL DE DEPARTAMENTOS -> " + total_departamentos + "\n" +
								"TOTAL DE EMPLEADOS -> " + total_empleados + "\n" +
								"MÁXIMO SALARIO -> " + max_salario + " euros\n" +
								"SALARIO MEDIO -> " + media_salario + " euros\n");
			
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
