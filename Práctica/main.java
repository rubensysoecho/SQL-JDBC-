package Práctica;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class main {

	final static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conexion = null;
		
		try {
			boolean finalizar = false;
			int num_operaciones = 0;
			
			while(!finalizar)	{
				System.out.println("\nMENÚ PAPELERÍA\n"
						+ "##############");

				System.out.println("1----Insertar datos");
				System.out.println("2----Ver tablas según parámetros");
				System.out.println("3----Ver tablas");
				System.out.println("4----Borrar");
				System.out.println("5----Modificar");
				System.out.println("6----Salir");

				switch(entrada.next()) {
				// INSERCIÓN
				case "1":
					System.out.println("SELECCIONE LA TABLA");
					System.out.println("1----Artículos");
					System.out.println("2----Clientes");
					System.out.println("3----Compras");

					switch(entrada.next())	{
					// INSERCION ARTICULOS
					case "1":
						System.out.print("Nombre: ");
						String nom_articulo = entrada.nextLine();
						System.out.print("Precio: ");
						double precio_articulo = entrada.nextDouble();
						System.out.print("Descripción: ");
						String desc_articulo = entrada.nextLine();

						insertar.ins_articulos(nom_articulo, precio_articulo, desc_articulo);
						num_operaciones++;
						break;

					// INSERCION CLIENTES
					case "2":
						System.out.print("Nombre: ");
						String nom_cliente = entrada.nextLine();
						System.out.print("DNI/NIF: ");
						String dni = entrada.next();

						insertar.ins_clientes(nom_cliente, dni);
						num_operaciones++;
						break;

					// INSERCION COMPRAS
					case "3":
						System.out.print("¿Está el cliente satisfecho?(s/n): ");
						boolean satisfecho = false;
						if(entrada.next().toLowerCase().equals("s")) satisfecho = true;

						System.out.println("LISTA ARTÍCULOS");
						String sql = "SELECT * FROM articulos";
						conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/papeleria", "root", "root");
						CallableStatement sentenciaSelect = conexion.prepareCall(sql);
						ResultSet resultadoSelect = sentenciaSelect.executeQuery();

						while(resultadoSelect.next()) {
							String nombre = resultadoSelect.getString(2);

							System.out.println("- " + nombre);
						}

						System.out.print("\nNombre artículo: ");
						String nom_id_art = entrada.next();

						sentenciaSelect = conexion.prepareCall(sql);
						resultadoSelect = sentenciaSelect.executeQuery();

						int id_art = 0;
						while(resultadoSelect.next()) {
							if(resultadoSelect.getString(2).equals(nom_id_art)) id_art = resultadoSelect.getInt(1);
						}

						System.out.println("LISTA CLIENTES");
						sql = "SELECT * FROM clientes";
						sentenciaSelect = conexion.prepareCall(sql);
						resultadoSelect = sentenciaSelect.executeQuery();

						while(resultadoSelect.next()) {
							String nombre = resultadoSelect.getString(2);
							String dni_cli = resultadoSelect.getString(3);

							System.out.println(nombre + " || " + dni_cli);
						}

						System.out.print("\nDNI/NIF cliente: ");
						String dni_id_cli = entrada.next();

						sentenciaSelect = conexion.prepareCall(sql);
						resultadoSelect = sentenciaSelect.executeQuery();

						int id_cli = 0;
						while(resultadoSelect.next()) {
							if(resultadoSelect.getString(2).equals(dni_id_cli)) id_cli = resultadoSelect.getInt(1);
						}

						insertar.ins_compras(satisfecho, id_art, id_cli);
						num_operaciones++;
						conexion.close();
						break;
					default:
						System.out.println("Tabla no seleccionada.");
					// FIN INSERCIÓN
					}
					break;

				// SELECCIONAR CON PARAMETROS
				case "2":
					System.out.println("SELECCIONE LA TABLA");
					System.out.println("1----Artículos");
					System.out.println("2----Clientes");
					System.out.println("3----Compras");

					switch(entrada.next()) {
					// SELECCION ARTICULOS
					case "1":
						System.out.println("Parámetro de búsqueda ->");
						System.out.println("1--ID");
						System.out.println("2--Nombre");

						// PARAMETROS
						switch(entrada.next())	{
						// BUSCAR POR ID
						case "1":
							System.out.print("Introduce la ID a buscar: ");
							seleccionar.sel_articuloID(entrada.nextInt());
							break;

						// BUSCAR POR NOMBRE
						case "2":
							System.out.print("Introduce el nombre del artículo a buscar: ");
							seleccionar.sel_articuloNom(entrada.next());
							break;
						default:
							System.out.println("Parámetro no encontrado.");
						}
						break;
					// SELECCION CLIENTES
					case "2":
						System.out.println("Parámetro de búsqueda ->");
						System.out.println("1--ID");
						System.out.println("2--Nombre");
						System.out.println("3--DNI");

						switch(entrada.next()) {
						case "1":
							System.out.print("Introduce la ID a buscar: ");
							seleccionar.sel_clienteID(entrada.nextInt());
							break;
						case "2":
							System.out.print("Introduce el nombre a buscar: ");
							seleccionar.sel_clienteNom(entrada.next());
							break;
						case "3":
							System.out.print("Introduce el DNI a buscar: ");
							seleccionar.sel_clienteDNI(entrada.next());
							break;
						default:
							System.out.println("Parámetro no encontrado.");
						}
						break;
					// SELECCION COMPRAS
					case "3":
						System.out.println("Parámetro de búsqueda ->");
						System.out.println("1--ID");
						System.out.println("2--Satisfecho?");
						System.out.println("3--ID artículo");
						System.out.println("4--ID cliente");

						switch(entrada.next()) {
						case "1":
							System.out.print("Introduce la ID a buscar: ");
							seleccionar.sel_comprasID(entrada.nextInt());
							break;
						case "2":
							System.out.print("¿Cliente satisfecho?(s/n): ");
							break;
						case "3":
							System.out.print("Introduce la ID de artículo a buscar: ");
							break;
						case "4":
							System.out.print("Introduce la ID de cliente a buscar: ");
							break;
						default:
							System.out.println("Parámetro no encontrado.");
						}
						break;
					}
					break;

				//SELECCIÓN
				case "3":
					System.out.println("SELECCIONE LA TABLA");
					System.out.println("1----Artículos");
					System.out.println("2----Clientes");
					System.out.println("3----Compras");

					switch(entrada.next()) {
					case "1":
						seleccionar.ver_tablaArt();
						break;
					case "2":
						seleccionar.ver_tablaCli();
						break;
					case "3":
						System.out.println("1----Tabla con IDs");
						System.out.println("2----Tabla con nombres");
						switch(entrada.next()) {
						case "1":
							seleccionar.ver_tablaComID();
							break;
						case "2":
							seleccionar.ver_tablaComNOM();
							break;
						default:
							System.out.println("Opción inválida.");
						}
						break;
					default:
						System.out.println("Parámetro no encontrado.");
					}
					break;
				case "4":
					System.out.println("SELECCIONE LA TABLA");
					System.out.println("1----Artículos");
					System.out.println("2----Clientes");
					System.out.println("3----Compras");

					switch(entrada.next()) {
					case "1":
						seleccionar.ver_tablaArt();
						System.out.print("Introduce la ID a borrar: ");
						borrar.borrar_art(entrada.nextInt());
						break;
					case "2":
						seleccionar.ver_tablaCli();
						System.out.print("Introduce la ID a borrar: ");
						borrar.borrar_cli(entrada.nextInt());
						break;
					case "3":
						seleccionar.ver_tablaComID();
						System.out.print("Introduce la ID a borrar: ");
						borrar.borrar_com(entrada.nextInt());
						break;
					default:
						System.out.println("Parámetro no encontrado.");
					}
					break;
				case "5":
					System.out.println("SELECCIONE LA TABLA");
					System.out.println("1----Artículos");
					System.out.println("2----Clientes");
					System.out.println("3----Compras");

					switch(entrada.next()) {
					case "1":
						seleccionar.ver_tablaArt();
						System.out.print("Introduce la ID a modificar: ");
						int id_art = entrada.nextInt();
						System.out.print("Nombre: ");
						String nom_art = entrada.next();
						System.out.print("Precio: ");
						double pre_art = entrada.nextDouble();
						System.out.print("Descripción: ");
						String desc_art = entrada.next();
						
						modificar.alt_art(id_art, nom_art, pre_art, desc_art);
						break;
					case "2":
						seleccionar.ver_tablaCli();
						System.out.print("Introduce el ID a modificar: ");
						int id_cli = entrada.nextInt();
						System.out.print("Nombre: ");
						String nom_cli = entrada.next();
						System.out.print("DNI: ");
						String dni_cli = entrada.next();
						
						modificar.alt_cli(id_cli, nom_cli, dni_cli);
						break;
					case "3":
						seleccionar.ver_tablaComID();
						System.out.print("Introduce el ID a modificar: ");
						int id_com = entrada.nextInt();
						System.out.print("¿Satisfecho?(s/n): ");
						String satisfaccion = entrada.next();
						boolean satisfecho = false;
						if (satisfaccion.toLowerCase().equals("s")) satisfecho = true;
						if (!satisfaccion.toLowerCase().equals("s") || !satisfaccion.toLowerCase().equals("n")) System.out.println("Respuesta incorrecta, satisfacción negativa por defecto.");
						
						seleccionar.ver_tablaArt();
						System.out.print("ID artículo: ");
						int id_art_com = entrada.nextInt();
						
						seleccionar.ver_tablaCli();
						System.out.print("ID cliente: ");
						int id_cli_com = entrada.nextInt();
						
						modificar.alt_com(id_com, satisfecho, id_art_com, id_cli_com);
						break;
					default:
						System.out.println("Parámetro no encontrado.");
					}
					
					break;
				case "6":
					finalizar = true;
					break;
				}
			}
			System.out.println("Programa finalizado. Has introducido " + num_operaciones + " filas nuevas."); //filas borradas y modificadas);
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