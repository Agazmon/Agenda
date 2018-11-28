package org.iesalandalus.programacion.agenda.modelo;

import java.util.regex.Pattern;

public class Contacto {

	private static final String ER_TELEFONO = "\\[69]\\d{8}";
	private static final String ER_CORREO = "\\w(-*\\w*)+@\\w+.\\w{2,5}";
	private String nombre;
	private String telefono;
	private String correo;

	public String getNombre() {
		return this.nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null | nombre == "")
			throw new IllegalArgumentException("El nombre de un contacto no puede ser nulo o vacío.");
		else if (nombre != this.nombre) {
			throw new IllegalArgumentException("No puedes cambiarle el nombre");
		} else {
			this.nombre = nombre;
		}
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		Pattern entrada_telefono = Pattern.compile(ER_TELEFONO);
		if (telefono.charAt(0) != '6' & telefono.charAt(0) != '9') {
			throw new IllegalArgumentException("El teléfono de un contacto debe empezar por 6 o 9");
		} else if (telefono.matches(ER_TELEFONO)) {
			this.telefono = telefono;
		} else {
			throw new IllegalArgumentException("El teléfono de un contacto no puede ser nulo o vacío.");
		}
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		Pattern entrada_correo = Pattern.compile(ER_CORREO);
		if (telefono.matches(ER_CORREO)) {
			this.correo = correo;
		}
	}
}
