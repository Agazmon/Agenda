package org.iesalandalus.programacion.agenda.modelo;

public class Contacto {

	private static final String ER_TELEFONO = "[69]\\d{8}";
	private static final String ER_CORREO = "\\w+-*.*\\w*+@\\w[^1-9+-]+\\.+\\w+{2,5}";
	private String nombre;
	private String telefono;
	private String correo;

	public Contacto(String nombre, String telefono, String correo) {
		if (nombre==getNombre()) {
			if (nombre==null) {
				throw new IllegalArgumentException("El nombre de un contacto no puede ser nulo o vacío.");
			}
			setTelefono(getTelefono());
			setCorreo(getCorreo());
		}
		if (nombre == null | nombre == "")
			throw new IllegalArgumentException("El nombre de un contacto no puede ser nulo o vacío.");
		else { 
		setNombre(nombre);
		}
		if (telefono == null | telefono == "") {
			throw new IllegalArgumentException("El teléfono de un contacto no puede ser nulo o vacío.");
		} else if (telefono.matches(ER_TELEFONO)){
			setTelefono(telefono);
		} else {
			throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
		}
		if (correo == null | correo == "") {
			throw new IllegalArgumentException("El correo de un contacto no puede ser nulo o vacío.");
		} else if (correo.matches(ER_CORREO)) {
			setCorreo(correo);
		} else {
			throw new IllegalArgumentException("El correo no tiene un formato válido.");
		}
	}

	public String getNombre() {
		return this.nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null | nombre == "") {
			throw new IllegalArgumentException("El nombre de un contacto no puede ser nulo o vacío.");
	}	else { 
			this.nombre = nombre;
		}
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null | telefono == "") {
			throw new IllegalArgumentException("El teléfono de un contacto no puede ser nulo o vacío.");
		} else if (telefono.matches(ER_TELEFONO)){
				this.telefono = telefono;
		} else {
			throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
		}
	}
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		if (correo == null | correo == "") {
			throw new IllegalArgumentException("El correo de un contacto no puede ser nulo o vacío.");
		} else if (correo.matches(ER_CORREO)) {
			this.correo = correo;
		} else {
			throw new IllegalArgumentException("El correo no tiene un formato válido.");
		}
	}

	@Override
	public String toString() {
		return getInciales() +" [" + telefono +", " + correo+"]";
	}

	private String getInciales() {
		String nombre = this.nombre;
		char iniciales = ' ';
		String inicialesCadena = "";
		nombre= nombre.replaceAll("\\s+", " ");
		String[] division = nombre.split(" ");
		for (int i = 0; i < division.length; i++) {
			nombre = division[i];
			if (division[i]!=" ") {
			iniciales = nombre.charAt(0);
			inicialesCadena = inicialesCadena + iniciales;
			} else {
			}
		}
		return inicialesCadena.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

}
