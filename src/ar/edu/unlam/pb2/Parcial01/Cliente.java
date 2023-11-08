package ar.edu.unlam.pb2.Parcial01;

import java.util.Objects;

public class Cliente {

	private String cuit;
	private String razonSocial;
	
	public Cliente(String cuit, String razonSocial) {
		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.cuit=cuit;
		this.razonSocial=razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cuit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		Cliente other = (Cliente) obj;
		return Objects.equals(cuit, other.cuit);
	}

	// TODO: Completar con los getters y setters necesarios
	
	
}
