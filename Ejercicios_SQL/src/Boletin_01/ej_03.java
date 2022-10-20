package Boletin_01;

import java.util.Scanner;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ej_03 {

	final static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "root");

			System.out.println("ARTÍCULOS: ");

			String sql = "SELECT `modelo`, `precio`, `descuento` FROM `articulos`";
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();

			while(resultadoSelect.next()) {
				String modelo = resultadoSelect.getString(1);
				double precio = resultadoSelect.getDouble(2);
				int descuento = resultadoSelect.getInt(3);

				System.out.println(modelo + " || " + precio + "€ || " + descuento);
			}
			
			System.out.print("\nAumentar porcentaje aumento precio(%): ");
			int aplicar_descuento = entrada.nextInt();

			sql = "UPDATE `articulos` SET `precio` = precio+precio*(?/100) WHERE descuento is NULL";
			
			PreparedStatement sentenciaUpdate = conexion.prepareCall(sql);
			sentenciaUpdate.setInt(1, aplicar_descuento);
			sentenciaUpdate.executeUpdate();
			int afectadas = sentenciaUpdate.executeUpdate();
			
			System.out.println("Se han actualizado " + afectadas + " artículos.");
			
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
