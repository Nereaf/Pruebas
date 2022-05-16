package pruebas;

import java.util.Objects;

public class Usuario {
	private String nombre;
	private String DNI;
	private String usuario;
	private String contrasena;
	private String email;
	private Integer telefono;

	public Usuario(String nombre, String DNI, String usuario, String contrasena, String email, Integer telefono) {
		this.nombre = nombre;
		this.DNI = DNI;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.telefono = telefono;
	}

	public String getUsr() {
		return usuario;
	}

	public String getPass() {
		return contrasena;
	}

	public String getCorreo() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDNI() {
		return DNI;
	}

	public Integer getTelef() {
		return telefono;
	}

	public void setCorreo(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public void setTelef(Integer telefono) {
		this.telefono = telefono;
	}

	public void setUsr(String usuario) {
		this.usuario = usuario;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	@Override
	public int hashCode() {
		return Objects.hash(DNI, contrasena, email, nombre, telefono, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(DNI, other.DNI) && Objects.equals(contrasena, other.contrasena)
				&& Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(usuario, other.usuario);
	}

}
