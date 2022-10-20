package Transacciones;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ej01 {

	final static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "root");
			
			conexion.setAutoCommit(false);
			
			while(true) {
				
				String sql = "SELECT * FROM clientes";
				CallableStatement sentenciaSelect = conexion.prepareCall(sql);
				ResultSet resultadoSelect = sentenciaSelect.executeQuery();
				
				while(resultadoSelect.next()) {
					String nif = resultadoSelect.getString(1);
					String nombre = resultadoSelect.getString(2);
					
					System.out.println(nombre + "|---|" + nif + "\n");
				}
				
				System.out.print("Introduce NIF de cliente: ");
				String nif = entrada.next();
				SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
				System.out.print("Fecha(dd/MM/yyyy): ");
				String strFecha = entrada.next();
				Date d = formateador.parse(strFecha);
				
				sql = "INSERT INTO `pedidos`(`NIF_CLIENTE`, `FECHA`) VALUES (?, ?)";
				PreparedStatement sentenciaInsert = conexion.prepareCall(sql);
				sentenciaInsert.setString(1, nif);
				java.sql.Date fechasql=new java.sql.Date(d.getTime());
				sentenciaInsert.setDate(2, fechasql);
				sentenciaInsert.executeUpdate();
				
				System.out.println("NIF cliente: " + nif);
				System.out.println("Fecha: " + strFecha);
				System.out.print("Datos correctos(s/n): ");
				
				switch(entrada.next().toLowerCase())	{
				case "s":
					System.out.println("Datos de pedido introducidos.\n");
					conexion.commit();
					break;
				case "n":
					System.out.println("Cancelando operacion...");
					conexion.rollback();
					break;
				default:
					System.out.println("Respuesta incorrecta. Deshaciendo cambios...");
					conexion.rollback();
					break;
				}
				
				sql = "SELECT * FROM pedidos";
				sentenciaSelect = conexion.prepareCall(sql);
				resultadoSelect = sentenciaSelect.executeQuery();
				
				int num_pedido = 0;
				while (resultadoSelect.next()) {
					if (resultadoSelect.getDate(3).equals(fechasql)) num_pedido = resultadoSelect.getInt(1);
				}
				
				System.out.print("Introduce el código del artículo: ");
				String cod_articulo = entrada.next();
				if (cod_articulo.equals("*"))	{
					System.out.print("¿Desea grabar el pedido(s/n)?:  ");
					if (entrada.next().toLowerCase().equals("s")) break;
					else if (entrada.next().toLowerCase().equals("n")) conexion.rollback();
				}
				System.out.print("Indique la cantidad: ");
				int cantidad = entrada.nextInt();
				System.out.print("Indique el precio en euros: ");
				double precio = entrada.nextDouble();
				
				sql = "INSERT INTO `lineas_pedido`(`NUM_PEDIDO`, `COD_ARTICULO`, `CANTIDAD`, `PRECIO`) VALUES (?, ?, ?, ?)";
				sentenciaInsert = conexion.prepareCall(sql);
				sentenciaInsert.setInt(1, num_pedido);
				sentenciaInsert.setString(2, cod_articulo);
				sentenciaInsert.setInt(3, cantidad);
				sentenciaInsert.setDouble(4, precio);
				sentenciaInsert.executeUpdate();
				
				System.out.println("Nº pedido --> " + num_pedido);
				System.out.println("Código artículo --> " + cod_articulo);
				System.out.println("Cantidad --> " + cantidad);
				System.out.println("Precio --> " + precio);
				
				System.out.print("¿Son los datos correctos?(s/n): ");
				
				switch(entrada.next().toLowerCase())	{
				case "s":
					System.out.println("Datos de linea de pedido introducidos.");
					conexion.commit();
					break;
				case "n":
					System.out.println("Cancelando operacion...");
					conexion.rollback();
					break;
				default:
					System.out.println("Respuesta incorrecta. Deshaciendo cambios...");
					conexion.rollback();
					break;
				}
			}
			
			System.out.println("Programa finalizado.");
			
		} catch (Exception e)	{
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
