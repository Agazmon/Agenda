package org.iesalandalus.programacion.agenda.modelo;

import javax.naming.OperationNotSupportedException;

public class Agenda {
	private static final int MAX_CONTACTOS = 100; /* Max contactos tampoco se a que se refiere por ahora */
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
		int indice=0;
		if (contactoIndice == null) {
			throw new IllegalArgumentException("El contacto no puede ser nulo");
		} else {
			for (int q=0;q<numContactos+1;q++) {
				if (contactos[q]==null) {
					if (indiceNoSuperaTamano(indice)) {
						indice=q;
						return indice;
					}
				} else if(contactos[q]!=null) {
					if(contactos[q]==contactoIndice) {
						throw new OperationNotSupportedException("Ya existe un contacto con ese nombre.");
					} else if (contactos[q+1]==null){
						indice=q+1;
						return indice;
					}
				} 
			}
			
		}return indice;
			
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
			if (contactoActual != null) {
				if (nombreBusqueda.equals(contactoActual.getNombre())) {
					indiceContacto = z;
				}
			} else if (contactoActual == null) {
				indiceContacto = z;
			}
		}
		return indiceContacto;
	}

	public void borrar(String nombreBorrar) throws OperationNotSupportedException {
		int indiceBorrado = -1;
		indiceBorrado = buscarIndiceCliente(nombreBorrar);
		if (indiceBorrado == -1) {
			throw new OperationNotSupportedException("El contacto a borrar no existe.");

		} else {
			indiceBorrado = buscarIndiceCliente(nombreBorrar);
			contactos[indiceBorrado] = null;
			desplazarUnaPosicionHaciaIzquierda(indiceBorrado);
			numContactos = numContactos - 1;
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int Posicion) {
		for (int z = 0; z < numContactos; z++) {
			if (contactos[z + 1] != null) { /*
											 * El codigo esta hecho de forma que compacte siempre la agenda para que no
											 * haya espacios nulos o esa es la intención
											 */
				contactos[z] = null;
				contactos[z] = new Contacto(contactos[z + 1].getNombre(), contactos[z + 1].getTelefono(),
						contactos[z + 1].getCorreo());
				contactos[z + 1] = null;
			}
		}

	}

}
