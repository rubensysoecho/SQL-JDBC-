package Transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ej02 {

	final static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "root");
			
			conexion.setAutoCommit(false);
			
			System.out.print("Num pedido: ");
			int num_pedido = entrada.nextInt();
			
			String sql = "SELECT * FROM lineas_pedido WHERE NUM_PEDIDO = ?";
			PreparedStatement sentenciaSelect = conexion.prepareCall(sql);
			sentenciaSelect.setInt(1, num_pedido);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();
			
			while(resultadoSelect.next())	{
				int id_linea = resultadoSelect.getInt(1);
				String cod_articulo = resultadoSelect.getString(3);
				int cantidad = resultadoSelect.getInt(4);
				double precio = resultadoSelect.getDouble(5);
				
				System.out.println("ID -> " + id_linea);
				System.out.println("Cod artículo -> " + cod_articulo);
				System.out.println("Cantidad -> " + cantidad);
				System.out.println("Precio -> " + precio + " €");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			
			System.out.print("Introduce ID de linea a borrar: ");
			int id_borrar = entrada.nextInt();
			
			sql = "DELETE FROM `lineas_pedido` WHERE ID_LINEA = ?";
			PreparedStatement sentenciaDelete = conexion.prepareStatement(sql);
			sentenciaDelete.setInt(1, id_borrar);
			sentenciaDelete.executeUpdate();
			
			System.out.print("Seguro que quieres borrar la línea " + id_borrar +"?(s/n): ");
			
			switch(entrada.next()) {
			case "s":
				System.out.println("Borrado completado.");
				conexion.commit();
				break;
			case "n":
				System.out.println("Operación cancelada.");
				conexion.rollback();
				break;
			default:
				System.out.println("Opción inválida. Deshaciendo cambios...");
				conexion.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally	{
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
