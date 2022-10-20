package Práctica;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class seleccionar {

	public static void ver_tablaArt() {
		String sql = "SELECT * FROM articulos";
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			System.out.println("ID || NOMBRE || PRECIO || DESCRIPCIÓN");
			while(resultadoSelect.next()) {
				int id = resultadoSelect.getInt(1);
				String nombre = resultadoSelect.getString(2);
				double precio = resultadoSelect.getDouble(3);
				String desc = resultadoSelect.getString(4);
				System.out.println(id + " || " + nombre + " || " + precio + " || " + desc);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void ver_tablaCli() {
		String sql = "SELECT * FROM clientes";
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			System.out.println("ID || NOMBRE  || DNI");
			while(resultadoSelect.next()) {
				int id = resultadoSelect.getInt(1);
				String nombre = resultadoSelect.getString(2);
				String dni = resultadoSelect.getString(3);
				System.out.println(id + " || " + nombre + " || " + dni);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void ver_tablaComID() {
		String sql = "SELECT * FROM compras";
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			System.out.println("ID || SATISFACCIÓN || ID ARTÍCULO || ID CLIENTE");
			while(resultadoSelect.next()) {
				int id = resultadoSelect.getInt(1);
				boolean satisfecho = resultadoSelect.getBoolean(2);
				int id_art = resultadoSelect.getInt(3);
				int id_cli = resultadoSelect.getInt(4);
				if (satisfecho) {
					System.out.println(id + " || Satisfecho || " + id_art + " || " + id_cli);
				}
				else	{
					System.out.println(id + " || No satisfecho || " + id_art + " || " + id_cli);
				}
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void ver_tablaComNOM() {
		String sql = "SELECT COMPRAS.ID,COMPRAS.SATISFECHO,ARTICULOS.NOMBRE,ARTICULOS.PRECIO,CLIENTES.DNI\r\n"
				+ "FROM articulos join compras on articulos.id=compras.id_articulo \r\n"
				+ "join clientes on compras.id_cliente = clientes.id";
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			System.out.println("ID || SATISFACCIÓN || NOMBRE ART. || PRECIO || DNI");
			while(resultadoSelect.next()) {
				int id = resultadoSelect.getInt(1);
				boolean satisfecho = resultadoSelect.getBoolean(2);
				String nom_art = resultadoSelect.getString(3);
				double pre_art = resultadoSelect.getDouble(4);
				String dni_cli = resultadoSelect.getString(5);
				if (satisfecho) {
					System.out.println(id + " || Satisfecho || " + nom_art + " || " + pre_art + " EUROS || " + dni_cli);
				}
				else	{
					System.out.println(id + " || No satisfecho || " + nom_art + " || " + pre_art + " EUROS || " + dni_cli);
				}
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sel_articuloID(int id)	{	
		try {
			String sql = "SELECT * FROM articulos WHERE ID = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaSelect = conexion.prepareStatement(sql);
			sentenciaSelect.setInt(1, id);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next())	{
				int id_art = resultadoSelect.getInt(1);
				String nom_art = resultadoSelect.getString(2);
				double pre_art = resultadoSelect.getDouble(3);
				String desc_art = resultadoSelect.getString(4);
				System.out.println(id_art + " || " + nom_art + " || " + pre_art + "€ || " + desc_art);				
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sel_articuloNom(String nombre)	{	
		try {
			String sql = "SELECT * FROM articulos WHERE NOMBRE = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaSelect = conexion.prepareStatement(sql);
			sentenciaSelect.setString(1, nombre);;
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next())	{
				int id_art = resultadoSelect.getInt(1);
				String nom_art = resultadoSelect.getString(2);
				double pre_art = resultadoSelect.getDouble(3);
				String desc_art = resultadoSelect.getString(4);
				System.out.println(id_art + " || " + nom_art + " || " + pre_art + "€ || " + desc_art);				
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sel_clienteID(int id) {
		try {
			String sql = "SELECT * FROM clientes WHERE ID = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaSelect = conexion.prepareStatement(sql);
			sentenciaSelect.setInt(1, id);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next())	{
				int id_cli = resultadoSelect.getInt(1);
				String nom_cli = resultadoSelect.getString(2);
				String dni_cli = resultadoSelect.getString(3);
				System.out.println(id_cli + " || " + nom_cli + " || " + dni_cli);				
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sel_clienteNom(String nombre) {
		try {
			String sql = "SELECT * FROM clientes WHERE NOMBRE = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaSelect = conexion.prepareStatement(sql);
			sentenciaSelect.setString(1, nombre);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next())	{
				int id_cli = resultadoSelect.getInt(1);
				String nom_cli = resultadoSelect.getString(2);
				String dni_cli = resultadoSelect.getString(3);
				System.out.println(id_cli + " || " + nom_cli + " || " + dni_cli);				
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sel_clienteDNI(String dni)	{
		try {
			String sql = "SELECT * FROM clientes WHERE DNI = ?";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaSelect = conexion.prepareStatement(sql);
			sentenciaSelect.setString(1, dni);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next())	{
				int id_cli = resultadoSelect.getInt(1);
				String nom_cli = resultadoSelect.getString(2);
				String dni_cli = resultadoSelect.getString(3);
				System.out.println(id_cli + " || " + nom_cli + " || " + dni_cli);				
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sel_comprasID(int id) {
		Connection conexion;
		try {
			String sql = "SELECT * FROM compras WHERE ID = ?";
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			sentenciaSelect.setInt(1, id);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next()) {
				boolean satisfecho = resultadoSelect.getBoolean(2);
				int id_art = resultadoSelect.getInt(3);
				int id_cli = resultadoSelect.getInt(4);
				if (satisfecho) {
					System.out.println(id + " || Satisfecho || " + id_art + " || " + id_cli);
				}
				else	{
					System.out.println(id + " || No satisfecho || " + id_art + " || " + id_cli);
				}
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
