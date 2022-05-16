package pruebas;
import java.util.ArrayList;
import java.util.Objects;

public class Alarma {
	
	// Atributos
	private Integer id;
	private String descripcion;
	private ArrayList<Protocolo> protocolos;
	
	public Alarma(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
		protocolos = new ArrayList<>();
	}
	
	
	// Getters y Setters
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<Protocolo> getProtocolos() {
		return protocolos;
	}
	public void setProtocolos(ArrayList<Protocolo> protocolos) {
		this.protocolos = protocolos;
	}
	
	
	public Integer getId() {
		return this.id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, protocolos);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarma other = (Alarma) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(protocolos, other.protocolos);
	}
}
