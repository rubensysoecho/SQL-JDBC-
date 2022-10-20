package Boletin_01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ej_09 {

	final static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "root");
			
			System.out.print("Introduce el departamento a buscar: ");
			String departamento_buscado = entrada.next().toUpperCase();
			
			String sql = "SELECT * FROM `empleados`\r\n"
					+ "JOIN departamentos ON (empleados.NUM_DEPARTAMENTO = departamentos.NUM_DEPARTAMENTO) WHERE departamentos.NOMBRE = ?";
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			sentenciaSelect.setString(1, departamento_buscado);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next()) {
				int num_empleado = resultadoSelect.getInt(1);
				String nom_empleado = resultadoSelect.getString(2);
				String nom_departamento = resultadoSelect.getString(10);
				
				
				System.out.println(num_empleado + " || " + nom_empleado + " || " + nom_departamento);
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
