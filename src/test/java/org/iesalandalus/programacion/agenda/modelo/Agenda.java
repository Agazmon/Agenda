package org.iesalandalus.programacion.agenda.modelo;

import javax.naming.OperationNotSupportedException;

public class Agenda {
	private static final int MAX_CONTACTOS = 99; /* Max contactos tampoco se a que se refiere por ahora */
	private int numContactos = 0;
	Contacto[] contactos;

	public Agenda() {
		this.contactos = new Contacto[MAX_CONTACTOS];
		/* todavía no tengo clara la funcion del constructor Agenda */
	}

	public Contacto[] getContactos() {
		Contacto[] contactosCopia;
		contactosCopia = new Contacto[numContactos];
		for (int p = 0; p < numContactos; p++) {
			contactosCopia[p] = contactos[p];
		}
		return contactosCopia;
	}

	public int getNumContactos() {
		return numContactos;
	}

	public void anadir(Contacto contactoNuevo) throws OperationNotSupportedException {
		contactos[buscarPrimerIndiceComprobandoExistencia(contactoNuevo)] = contactoNuevo;
		numContactos++;
	}

	private int buscarPrimerIndiceComprobandoExistencia(Contacto contactoIndice) throws OperationNotSupportedException {
		int indice = 0;
		if (contactoIndice == null) {
			throw new IllegalArgumentException("El contacto no puede ser nulo");
		}
		for (int q = 0; q < numContactos; q++) {
			if (contactos[q] == null) {
				indice = q;
				if (indiceNoSuperaTamano(indice)) {
					throw new OperationNotSupportedException(
							"El contacto no se puede almacenar en la agenda dado que no hay espacio");
				}
			} else if (contactos[q] == contactoIndice) {
				throw new OperationNotSupportedException("Ya existe un contacto con ese nombre.");
			}
		}
		return indice;
	}

	private boolean indiceNoSuperaTamano(int Indice) {
		boolean comprobacion = false;
		if (Indice > MAX_CONTACTOS) {
			comprobacion = true;
		} else if (Indice < MAX_CONTACTOS) {
			comprobacion = false;
		}
		return comprobacion;
	}

	public Contacto buscar(String nombreBuscar) {
		Contacto contactoEncontrado = null;
		int indice;
		indice = buscarIndiceCliente(nombreBuscar);
		if (indice != -1) {
			contactoEncontrado = contactos[indice];
			return contactoEncontrado;
		} else {
			for (int z = 0; z < numContactos; z++) {
				if (contactos[z].equals(null)) {
					contactoEncontrado = contactos[z];
					return contactoEncontrado;
				}
			}
		}
		return contactoEncontrado;
	}

	private int buscarIndiceCliente(String nombreBusqueda) {
		int indiceContacto = -1;
		Contacto contactoActual;
		for (int z = 0; z < numContactos; z++) {
			contactoActual = contactos[z];
			if (nombreBusqueda.equals(contactoActual.getNombre())) {
				indiceContacto = z;

			}

		}
		return indiceContacto;
	}
}
