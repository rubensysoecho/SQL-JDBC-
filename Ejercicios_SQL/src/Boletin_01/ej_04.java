package Boletin_01;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ej_04 {

	final static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "root");
			System.out.println("Conexión con base de datos satisfactoria...\n");
			
			System.out.print("Precio máximo para eliminar: ");
			double max_precio = entrada.nextDouble();
			
			String sql = "DELETE FROM articulos WHERE cod_familia = 'PORT' AND precio > ?";
			PreparedStatement sentenciaDelete = conexion.prepareStatement(sql);
			sentenciaDelete.setDouble(1, max_precio);
			int afectados = sentenciaDelete.executeUpdate();
			
			System.out.println(afectados);
			
			if (afectados == 1) System.out.println(afectados + " artículo eliminado satisfactoriamente.");
			else System.out.println(afectados + " artículos eliminados satisfactoriamente.");
				
			
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
