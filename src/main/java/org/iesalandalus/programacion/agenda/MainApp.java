package org.iesalandalus.programacion.agenda;

import java.util.Arrays;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.agenda.modelo.*;;

public class MainApp {
	private static final String ERROR = "La opción no ha podido ser ejecutada con exito";
	private static final String EXITO = "La opcición ha sido ejecutada con exito";
	private static final Agenda agenda=new Agenda();
	
	public MainApp() {

	}

	public static void main(String[] args) {
		System.out.println("Programa para gestionar una agenda de contactos");
		mostrarMenu();

	}

	private static void mostrarMenu() {
		System.out.println("Elige una de las siguientes opciones");
		System.out.println("1:Añadir contacto");
		System.out.println("2:Buscar contacto");
		System.out.println("3:Borrar contacto");
		System.out.println("4:Listar agenda");
		System.out.println("5:Salir");
		ejecutarOpcion(elegirOpcion());
	}

	private static int elegirOpcion() {
		int eleccion;
		do {
			eleccion = Entrada.entero();
		} while (eleccion > 6 | eleccion < 1);
		return eleccion;
	}

	private static void ejecutarOpcion(int eleccion) {

		switch (eleccion) {
		case 1:
			anadirContacto();
		case 2:
			buscarContacto();
		case 3:
			borrarContacto();
		case 4:
			listarAgenda();
		case 5:
			System.out.println("¡Gracias por usar el programa!");
			break;
		default:
			mostrarMenu();
		}
	}

	private static void anadirContacto() {
		String nContacto;
		String tContacto;
		String cContacto;
		Contacto contactoEleccion;
		
		System.out.println("Has elegido la opción: 1:Añadir contacto");
		do {
			System.out.println("Introduce el nombre del contacto");
			nContacto = Entrada.cadena();
		} while (nContacto == null | nContacto == "");
		do {
			System.out.println("Introduce el numero de " + nContacto);
			tContacto = Entrada.cadena();
		} while (tContacto == null | tContacto == "" | !(tContacto.matches("[69]\\d{8}")));
		do {
			System.out.println("Introduce el correo de " + nContacto);
			cContacto = Entrada.cadena();
		} while (cContacto == null | cContacto == ""
				| !(cContacto.matches("\\w+\\-*\\.*\\w*\\@\\w*[^1-9\\-]\\.\\w{1,5}")));
		try {
			contactoEleccion = new Contacto(nContacto, tContacto, cContacto);
			agenda.anadir(contactoEleccion);
			System.out.println(EXITO);
			mostrarMenu();
		} catch (OperationNotSupportedException e) {
			System.out.println(e);
			System.out.println(ERROR);
			mostrarMenu();
		}
	}

	private static void buscarContacto() {
		String nContacto;
		Contacto contactoEleccion;
		System.out.println("Has elegido la opción: 2:Buscar contacto");
		do {
			System.out.println("Introduce el nombre del contacto que deseas buscar");
			nContacto = Entrada.cadena();
		} while (nContacto == null | nContacto == "");
		contactoEleccion = agenda.buscar(nContacto);
		if (contactoEleccion == null) {
			System.out.println(ERROR);
			mostrarMenu();
		} else {
			System.out.println("Nombre: "+
					contactoEleccion.getNombre()+" Telefono: "+contactoEleccion.getTelefono() + " Correo: "+contactoEleccion.getCorreo());
			System.out.println(EXITO);
			mostrarMenu();
		}
	}

	private static void borrarContacto() {
		String nContacto;
		System.out.println("Has elegido la opción: 3:Borrar contacto");
		do {
			System.out.println("Introduce el nombre del contacto que deseas borrar");
			nContacto = Entrada.cadena();
		} while (nContacto == null | nContacto == "");

		try {
			agenda.borrar(nContacto);
			System.out.println(EXITO);
			mostrarMenu();
		} catch (OperationNotSupportedException e) {
			System.out.println(e);
			System.out.println(ERROR);
			mostrarMenu();
		}
	}

	private static void listarAgenda() {
		Contacto[] array;
		System.out.println("Has elegido la opción: 4:Listar agenda");
		System.out.println(agenda.getNumContactos());
		array=new Contacto[agenda.getContactos().length+1];
		array=agenda.getContactos();
		for (int z=0; z<agenda.getNumContactos();z++) {
			System.out.println(array[z]);
		}
		mostrarMenu();
	}
}
