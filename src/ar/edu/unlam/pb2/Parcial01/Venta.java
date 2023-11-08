package ar.edu.unlam.pb2.Parcial01;

import java.util.HashMap;
import java.util.Map;

public class Venta {

	private String codigo;
	private Cliente cliente;
	private Vendedor vendedor;
	private Map<Vendible, Integer> renglones;
	private Integer renglonActual;

	public Venta(String codigo, Cliente cliente, Vendedor vendedor) {
		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.codigo=codigo;
		this.cliente=cliente;
		this.vendedor=vendedor;
		this.renglones=new HashMap<Vendible, Integer>();
	}
	
	// TODO: Completar con los getters y setters necesarios

	public void agregarRenglon(Vendible vendible, Integer cantidad) {
		// TODO: Agregar el vendible a la venta 
		renglones.put(vendible, cantidad);
		
		for (Map.Entry<Vendible, Integer> entry : renglones.entrySet()) {
			Vendible key = entry.getKey();
			Integer val = entry.getValue();
			if(vendible.getTipo().equals(TipoItem.Servicio) && vendible.getCodigo().equals(key.getCodigo())) {
				val++;
			}
		}
		
	}
	
	@Override
	public String toString() {

		return "Venta: " + codigo + "\n" + "Cliente" + cliente + "\n" + "Vendedor" + vendedor + "\n";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	
	
	


	

}
