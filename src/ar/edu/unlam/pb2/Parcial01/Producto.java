package ar.edu.unlam.pb2.Parcial01;

public class Producto extends Item implements Vendible{
	
	private Integer puntoDeReposicion;
	private TipoItem tipo=TipoItem.Producto;

	
	public Producto(Integer codigo, String nombre, Double precio,Integer puntoDeReposicion) {
		super(codigo, nombre, precio);
		// TODO Auto-generated constructor stub
		this.puntoDeReposicion=puntoDeReposicion;
		this.tipo=getTipo();
	
	}

	public Integer getPuntoDeReposicion() {
		return puntoDeReposicion;
	}

	public void setPuntoDeReposicion(Integer puntoDeReposicion) {
		this.puntoDeReposicion = puntoDeReposicion;
	}

	@Override
	public TipoItem getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

	public void setTipo(TipoItem tipo) {
		this.tipo = tipo;
	}

	


	// TODO: Completar con los getters y setters necesarios
	
}
