package Práctica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class modificar {

	public static void alt_art(int id_buscada, String nombre, double precio, String descripcion)	{
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			String sql = "UPDATE `articulos` SET `NOMBRE`= ?,`PRECIO`= ?,`DESCRIPCION`= ? WHERE ID = ?";
			PreparedStatement sentenciaUpdate = conexion.prepareCall(sql);
			sentenciaUpdate.setString(1, nombre);
			sentenciaUpdate.setDouble(2, precio);
			sentenciaUpdate.setString(3, descripcion);
			sentenciaUpdate.setInt(4, id_buscada);
			sentenciaUpdate.executeUpdate();
			
			System.out.println("\nEl artículo con ID " + id_buscada + " ha sido actualizado.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void alt_cli(int id_buscada, String nombre, String dni)	{
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			String sql = "UPDATE `clientes` SET `NOMBRE`= ?,`DNI`= ? WHERE ID = ?";
			PreparedStatement sentenciaUpdate = conexion.prepareCall(sql);
			sentenciaUpdate.setString(1, nombre);
			sentenciaUpdate.setString(2, dni);
			sentenciaUpdate.setInt(3, id_buscada);
			sentenciaUpdate.executeUpdate();
			
			System.out.println("\nEl cliente con ID " + id_buscada + " ha sido actualizado.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void alt_com(int id_buscada, boolean satisfecho, int id_art, int id_cli)	{
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			String sql = "UPDATE `compras` SET `SATISFECHO`= ?,`ID_ARTICULO`= ?,`ID_CLIENTE`= ? WHERE ID = ?";
			PreparedStatement sentenciaUpdate = conexion.prepareCall(sql);
			sentenciaUpdate.setBoolean(1, satisfecho);
			sentenciaUpdate.setInt(2, id_art);
			sentenciaUpdate.setInt(3, id_cli);
			sentenciaUpdate.executeUpdate();
			
			System.out.println("\nLa compra con ID " + id_buscada + " ha sido actualizada.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
