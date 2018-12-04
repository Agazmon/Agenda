package org.iesalandalus.programacion.agenda.modelo;

public class Agenda {
	private static final int MAX_CONTACTOS; /*Max contactos tampoco se a que se refiere por ahora*/
	private int numContactos;
	Contacto[] contactosAgenda;

	public Agenda() {

		/* todavía no tengo clara la funcion del constructor Agenda */
	}

	public Contacto[] getContactos() {
		Contacto[] contactosCopia;
		contactosCopia = new Contacto[numContactos];
		for (int p = 0; p < numContactos; p++) {
			contactosCopia[p] = contactosAgenda[p];
		}
		return contactosCopia;
	}

	public int getNumContactos() {
		return numContactos;
	}
}
