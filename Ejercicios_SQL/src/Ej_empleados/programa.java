package Ej_empleados;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class programa {

	final static Scanner entrada = new Scanner(System.in);

	static String filtro = "";
	static String ordenacion = "";
	static String sql = "";

	public static void menu_principal()	{
		System.out.println("1.-Escoger ordenación");
		System.out.println("2.-Escoger filtro");
		System.out.println("3.-Mostrar");
		System.out.println("4.-Fin");
	}
	public static void menu_ordenacion01()	{
		System.out.println("CAMPO:");
		System.out.println("1.-Numero");
		System.out.println("2.-Nombre");
		System.out.println("3.-Puesto");
		System.out.println("4.-Jefe");
		System.out.println("5.-Salario");
		System.out.println("6.-Comision");
		System.out.println("7.-Departamento");
		System.out.println("8.-Ninguno");
	}
	public static void menu_ordenacion02()	{
		System.out.println("ORDEN:");
		System.out.println("1.-Ascendente");
		System.out.println("2.-Descendente");
	}
	public static void menu_filtrar()	{
		System.out.println("1.-Filtrar por puesto");
		System.out.println("2.-Filtrar por departamento");
		System.out.println("3.-Filtrar por puesto y departamento");
		System.out.println("4.-Ninguno");
	}
	public static void mostrar(Connection conexion)	{
		try	
			{
			sql = "SELECT * FROM empleados" + filtro + ordenacion;
			CallableStatement sentenciaSelect = conexion.prepareCall(sql);
			ResultSet resultadoSelect = sentenciaSelect.executeQuery();

			while(resultadoSelect.next())	{
				int num_empleado = resultadoSelect.getInt(1);
				String nom_empleado = resultadoSelect.getString(2);
				String puesto_empleado = resultadoSelect.getString(3);
				int num_jefe = resultadoSelect.getInt(4);
				double salario_empleado = resultadoSelect.getDouble(6);
				double comision_empleado = resultadoSelect.getDouble(7);
				int num_dpto = resultadoSelect.getInt(8);
				System.out.println(num_empleado + "|---|" + nom_empleado + "|---|" + puesto_empleado + "|---|" + num_jefe + "|---|" + salario_empleado + "|---|" + comision_empleado + "|---|" + num_dpto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "root");



			boolean finalizar = false;
			while(!finalizar) {
				menu_principal();

				// Decisión de acción
				switch(entrada.next()) {

				// ORDENAR
				case "1":
					ordenacion = "";
					//Parámetro
					menu_ordenacion01();

					String respuesta= "";
					switch(entrada.next()) {
					
					// Ordenar por NUMERO
					case "1":
						ordenacion = " ORDER BY NUM_EMPLEADO";
						menu_ordenacion02();
						respuesta = entrada.next();
						if (respuesta.equals("1")) ordenacion = " ORDER BY NUM_EMPLEADO ASC";
						else if (respuesta.equals("2")) ordenacion = " ORDER BY NUM_EMPLEADO DESC";
						break;
					// Ordenar por NOMBRE
					case "2":
						ordenacion = " ORDER BY NOMBRE_EMPLEADO";
						menu_ordenacion02();
						respuesta = entrada.next();
						if (respuesta.equals("1")) ordenacion = " ORDER BY NOMBRE_EMPLEADO ASC";
						else if (respuesta.equals("2")) ordenacion = " ORDER BY NOMBRE_EMPLEADO DESC";
						break;
					// Ordenar por PUESTO
					case "3":
						ordenacion = " ORDER BY PUESTO";
						menu_ordenacion02();
						respuesta = entrada.next();
						if (respuesta.equals("1")) ordenacion = " ORDER BY PUESTO ASC";
						else if (respuesta.equals("2")) ordenacion = " ORDER BY PUESTO DESC";
						break;
					// Ordenar por JEFE
					case "4":
						ordenacion = " ORDER BY NUM_JEFE";
						menu_ordenacion02();
						respuesta = entrada.next();
						if (respuesta.equals("1")) ordenacion = " ORDER BY NUM_JEFE ASC";
						else if (respuesta.equals("2")) ordenacion = " ORDER BY NUM_JEFE DESC";
						break;
					// Ordenar por SALARIO FALTAN RESPUESTA A PARTIR DE AQUI
					case "5":
						ordenacion = " ORDER BY SALARIO";
						menu_ordenacion02();
						respuesta = entrada.next();
						if (respuesta.equals("1")) ordenacion = " ORDER BY SALARIO ASC";
						else if (respuesta.equals("2")) ordenacion = " ORDER BY SALARIO DESC";
						break;
					// Ordenar por COMISION
					case "6":
						ordenacion = " ORDER BY COMISION";
						menu_ordenacion02();
						respuesta = entrada.next();
						if (respuesta.equals("1")) ordenacion = " ORDER BY COMISION ASC";
						else if (respuesta.equals("2")) ordenacion = " ORDER BY COMISION DESC";
						break;
					// Ordenar por DEPARTAMENTO
					case "7":
						ordenacion = " ORDER BY NUM_DEPARTAMENTO";
						menu_ordenacion02();
						respuesta = entrada.next();
						if (respuesta.equals("1")) ordenacion = " ORDER BY NUM_DEPARTAMENTO ASC";
						else if (respuesta.equals("2")) ordenacion = " ORDER BY NUM_DEPARTAMENTO DESC";
						break;
					// NO ordenar
					case "8":
						break;
					}
					break;

				// FILTRAR
				case "2":
					filtro = "";
					menu_filtrar();
					switch (entrada.next())	{
					case "1":
						System.out.print("Introduce el puesto: ");
						filtro = " WHERE PUESTO = '" + entrada.next() + "'";
						break;
					case "2":
						System.out.print("Introduce el nº de departamento: ");
						filtro = " WHERE NUM_DEPARTAMENTO = " + entrada.nextInt();
						break;
					case "3":
						System.out.print("Introduce el puesto: ");
						String puesto = entrada.next();
						System.out.print("Introduce el nº de departamento: ");
						String num_dpto = entrada.next();
						filtro = " WHERE PUESTO = '" + puesto + "' && NUM_DEPARTAMENTO = " + num_dpto;
						break;
					case "4":
						break;
					}
					break;

				// MOSTRAR
				case "3":
					mostrar(conexion);
					System.out.println(sql);
					break;

				// SALIR
				case "4":
					finalizar = true;
					break;
				}
			} System.out.println("\nPrograma finalizado.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
