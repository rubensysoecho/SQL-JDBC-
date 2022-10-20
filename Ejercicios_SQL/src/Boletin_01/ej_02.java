package Boletin_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ej_02 {

	final static Scanner entrada = new Scanner(System.in);

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "root");

			System.out.println("Conexión con base de datos satisfactoria...");

			String sql = "INSERT INTO familias(codigo,nombre, descripcion) VALUES (?,?,?)";

			PreparedStatement sentencia = conexion.prepareStatement(sql);

			String fam_codigo = "";
			String fam_nombre = "";
			String fam_desc = "";

			while(!fam_nombre.equals("*")) {

				// INSERCIÓN DE FAMILIA
				System.out.println("CREACIÓN DE FAMILIA\n===========");

				System.out.print("Nombre-> ");
				fam_nombre = entrada.nextLine();
				
				if (!fam_nombre.equals("*")) {	
					System.out.print("Codigo-> ");
					fam_codigo = entrada.nextLine();
					
					System.out.print("Descripcion-> ");
					fam_desc = entrada.nextLine();

					sentencia.setString(1, fam_codigo);
					sentencia.setString(2, fam_nombre);
					sentencia.setString(3, fam_desc);

					sentencia.executeUpdate();

					System.out.println("Familia introducida.\n");

					// INSERCIÓN DE MARCA
					System.out.println("CREACIÓN DE MARCA\n===========");

					System.out.print("Nombre: ");
					String marca_nombre = entrada.nextLine();
					sql = "INSERT INTO `marcas`(`nombre`) VALUES (?)";
					sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					sentencia.setString(1, marca_nombre);
					sentencia.executeUpdate();
					
					ResultSet resultado = sentencia.getGeneratedKeys();
					resultado.next();
					long cod_marca = resultado.getLong(1);
					
					System.out.println("Marca introducida.\n");

					// INSERCIÓN DE ARTÍCULO
					System.out.println("CREACIÓN DE ARTÍCULO\n===========");

					System.out.print("Modelo: ");
					String art_modelo = entrada.nextLine();

					System.out.print("Precio: ");
					double art_precio = entrada.nextDouble();

					System.out.print("Descuento: ");
					int art_descuento = entrada.nextInt();

					System.out.print("Descripción: ");
					String art_desc = entrada.nextLine();

					sql = "INSERT INTO `articulos`(`cod_marca`, `modelo`, `precio`, `descuento`, `descripcion`, `cod_familia`) VALUES (?,?,?,?,?,?)";
					sentencia = conexion.prepareStatement(sql);

					sentencia.setInt(1, (int)cod_marca);
					sentencia.setString(2, art_modelo);
					sentencia.setDouble(3, art_precio);
					sentencia.setInt(4, art_descuento);
					sentencia.setString(5, art_desc);
					sentencia.setString(6,fam_codigo);

					sentencia.executeUpdate();
					
					System.out.println("Artículo introducido.\n");
				}
			}
			
			System.out.println("PROGRAMA FINALIZADO.");
			
		}catch (SQLException e) {
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
