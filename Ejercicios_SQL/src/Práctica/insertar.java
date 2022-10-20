package Práctica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertar {
	
	public static void ins_articulos(String nombre, double precio, String descripcion) {	
		try {
			String sql = "INSERT INTO `articulos`(`NOMBRE`, `PRECIO`, `DESCRIPCION`) VALUES (?, ?, ?)";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaInsert = conexion.prepareStatement(sql);
			sentenciaInsert.setString(1, nombre);
			sentenciaInsert.setDouble(2, precio);
			sentenciaInsert.setString(3, descripcion);
			sentenciaInsert.executeUpdate();
			
			System.out.println("\nDatos introducidos en la tabla artículos.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	
	public static void ins_clientes(String nombre, String dni) {
		try {
			String sql = "INSERT INTO `clientes`(`NOMBRE`, `DNI`) VALUES (?, ?)";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaInsert = conexion.prepareStatement(sql);
			sentenciaInsert.setString(1, nombre);
			sentenciaInsert.setString(2, dni);
			sentenciaInsert.executeUpdate();
			
			System.out.println("\nDatos introducidos en la tabla clientes.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	
	public static void ins_compras(boolean satisfecho, int id_articulo, int id_cliente) {
		try {
			String sql = "INSERT INTO `compras`(`SATISFECHO`, `ID_ARTICULO`, `ID_CLIENTE`) VALUES (?, ?, ?)";
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
			
			PreparedStatement sentenciaInsert = conexion.prepareStatement(sql);
			sentenciaInsert.setBoolean(1, satisfecho);
			sentenciaInsert.setInt(2, id_articulo);
			sentenciaInsert.setInt(3, id_cliente);
			sentenciaInsert.executeUpdate();
			
			System.out.println("\nDatos introducidos en la tabla compras.");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
}
