package ar.edu.unlam.pb2.Parcial01;

public class Servicio extends Item {
	private String fechaDeInicio;
	private String fechaDeFinalizacion;
	private TipoItem tipo=TipoItem.Servicio;

	
	public Servicio(Integer codigo, String nombre, Double precio, String fechaDeInicio,String fechaDeFinalizacion) {
		super(codigo, nombre, precio);
		// TODO Auto-generated constructor stub
	
		this.fechaDeFinalizacion=fechaDeFinalizacion;
		this.fechaDeInicio=fechaDeInicio;
		this.tipo=getTipo();
	}


	@Override
	public TipoItem getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

	



	// TODO: Completar con los getters y setters necesarios
	
}
