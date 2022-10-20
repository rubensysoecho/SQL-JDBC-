package Boletin_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ej_01 {

	final static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conexion = null;
		
		try {			
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "root");
			
			System.out.println("Conexión con base de datos satisfactoria...");
			
			String modelo = "";
			
			while (true)	{
				String sql = "INSERT INTO `articulos`(`cod_marca`, `modelo`, `precio`, `descuento`, `descripcion`, `cod_familia`) VALUES ('2',?,?,?,?,'PORT')";
				
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				
				System.out.print("Modelo-> ");
				modelo = entrada.next();
				
				if (modelo.equals("*")) break;
				
				System.out.print("Precio-> ");
				int precio = entrada.nextInt();
				
				System.out.print("Descuento-> ");
				int descuento = entrada.nextInt();
				
				System.out.print("Descripción-> ");
				String descripcion = entrada.next();
				
				sentencia.setString(1, modelo);
				sentencia.setInt(2, precio);
				sentencia.setInt(3, descuento);
				sentencia.setString(4, descripcion);
				
				sentencia.executeUpdate();
				
				System.out.println("Artículo añadido correctamente.\n");
			}
			
			System.out.println("Programa finalizado.");
			
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
