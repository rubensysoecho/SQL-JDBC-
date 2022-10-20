package Práctica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class borrar {
	
	public static void borrar_art(int id)	{
		try {
			String sql = "DELETE FROM articulos WHERE ID = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria","root","root");
			
			PreparedStatement sentenciaDelete = conexion.prepareCall(sql);
			sentenciaDelete.setInt(1, id);
			sentenciaDelete.executeUpdate();
			
			System.out.println("\nArtículo con ID " + id + " eliminada.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrar_cli(int id)	{
		try {
			String sql = "DELETE FROM clientes WHERE ID = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaDelete = conexion.prepareCall(sql);
			sentenciaDelete.setInt(1, id);
			sentenciaDelete.executeUpdate();
			
			System.out.println("\nCliente con ID " + id + " eliminada.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrar_com(int id)	{
		try {
			String sql = "DELETE FROM compras WHERE ID = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaDelete = conexion.prepareCall(sql);
			sentenciaDelete.setInt(1, id);
			sentenciaDelete.executeUpdate();
			
			System.out.println("\nCompra con ID " + id + " eliminada.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
