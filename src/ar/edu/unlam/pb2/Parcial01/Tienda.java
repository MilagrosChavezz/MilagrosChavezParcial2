package ar.edu.unlam.pb2.Parcial01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Tienda {

	/**
	 * En esta ocasion deberemos resolver un producto software que nos permita
	 * administrar la venta de productos o servicios de nuestra tienda. Venderemos
	 * entonces, productos como mouse o teclados y servicios como el soporte tecnico
	 * a domicilio. Sabemos que la tienda cuenta con items Vendibles que pueden ser
	 * del tipo Producto o Servicio. Ademas, podemos registrar el stock de los
	 * productos, los clientes a quienes les vendemos algun producto o servicio, las
	 * ventas y los vendedores de la tienda. Antes de realizar alguna operacion, se
	 * debera obtener el elemento correspondiente de las colecciones. Ejemplo: Si
	 * quisiera realizar alguna operacion con un cliente, el mismo debe obtenerse de
	 * la coleccion de clientes.
	 * 
	 * Cada Venta contiene renglones los cuales representa a los productos o
	 * servicios que se incluyen en la misma. Tambien cuenta con el Cliente y
	 * Vendedor que participan en la Venta. Cuando agregamos un vendible a una
	 * venta, lo haremos con 1 unidad. En una version posterior, admitiremos
	 * cantidades variables.
	 * 
	 * Cada Item debe compararse por nombre y precio, en caso de ser necesario.
	 * Recordar que los items deben ser Vendibles.
	 * 
	 */

	private String cuit;
	private String nombre;
	private Set<Vendible> vendibles;
	private Map<Producto, Integer> stock;
	private List<Cliente> clientes;
	private Set<Venta> ventas;
	private Set<Vendedor> vendedores;
	private Double cantidaddeServiviosVendidios;

	public Tienda(String cuit, String nombre) {

		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.cuit=cuit;
		this.nombre=nombre;
		this.vendibles=new HashSet<Vendible>();
		this.stock=new HashMap<Producto, Integer>();
		this.clientes=new ArrayList<Cliente>();
		this.ventas=new HashSet<Venta>();
		this.vendedores=new HashSet<Vendedor>();
		cantidaddeServiviosVendidios=0.0;
	}

	// TODO: Completar con los getters y setters necesarios

	public Vendible getVendible(Integer codigo) {
		// TODO: Obtiene un producto o servicio de la coleccion de vendibles utilizando
		// el codigo. En caso de no existir devuelve null.
		for (Vendible v :vendibles) {
			if(v.getCodigo().equals(codigo)) {
				return v;
			}
		}
		return null;
	}

	public void agregarProducto(Producto producto) {
		this.agregarProducto(producto, 0);
	}

	public void agregarProducto(Producto producto, Integer stockInicial) {
		// TODO: Agrega un producto a la coleccion de vendibles y pone en la coleccion
		// de stocks al producto con su stock inicial
		stock.put(producto, stockInicial);
		vendibles.add(producto);
	}

	public void agregarServicio(Servicio servicio) {
		// TODO: Agrega un servicio a la coleccion de vendibles
		vendibles.add(servicio);
	}
	

	public Integer getStock(Producto producto) {
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if(producto.getCodigo().equals(key.getCodigo()) && producto.getNombre().equals(key.getNombre())) {
				return val;
			}
		}
		return null;
	}

	public void agregarStock(Producto producto, Integer incremento){
		// TODO: se debe agregar stock a un producto existente
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if(producto.getCodigo().equals(key.getCodigo()) && producto.getNombre().equals(key.getNombre())) {
				
				 stock.remove(key);
				 stock.put(key, val+incremento);
			}
		}
		
	}
	
	public void descontarStock(Producto producto, Integer incremento){
		// TODO: se debe agregar stock a un producto existente
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if(producto.getCodigo().equals(key.getCodigo()) && producto.getNombre().equals(key.getNombre())) {
				 stock.remove(key);
				 stock.put(key, val-incremento);
			}
		}
		
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarVendedor(Vendedor vendedor) {
		vendedores.add(vendedor);
	}

	public void agregarVenta(Venta venta) throws VendedorDeLicenciaException {
		// TODO: Agrega una venta a la coleccion correspondiente. En caso de que el
		// vendedor este de licencia, arroja una
		// VendedorDeLicenciaException
		
		if(venta.getVendedor().isDeLicencia()) {
			throw new VendedorDeLicenciaException();
		}
		ventas.add(venta);
	}

	public Producto obtenerProductoPorCodigo(Integer codigo) {
		// TODO: Obtiene un producto de los posibles por su codigo. En caso de no
		// encontrarlo se debera devolver null
		if(getVendible(codigo)!=null && getVendible(codigo).getTipo().equals(TipoItem.Producto)) {
			return (Producto) getVendible(codigo);
		}
		return null;
	}

	public Venta getVenta(String codigoVenta) {
		for (Venta v : ventas) {
			if(v.getCodigo().equals(codigoVenta)) {
				return v;
			}
		}
		return null;
		
	}
	
	
	
	
	public void agregarProductoAVenta(String codigoVenta, Producto producto) throws VendibleInexistenteExeption {

		// TODO: Agrega un producto a una venta. Si el vendible no existe (utilizando su
		// codigo), se debe lanzar una VendibleInexistenteException
		// Se debe actualizar el stock en la tienda del producto que se agrega a la
		// venta
		if(getVendible(producto.getCodigo())!=null && (getVendible(producto.getCodigo()).getTipo().equals(TipoItem.Producto))){
			descontarStock(producto, 1);
			getVenta(codigoVenta).agregarRenglon(producto, getStock(producto));
			
		}else {
			throw new VendibleInexistenteExeption();
		}
		
		
	}

	public void agregarServicioAVenta(String codigoVenta, Servicio servicio) throws VendibleInexistenteExeption {
		// TODO: Agrega un servicio a la venta. Recordar que los productos y servicios
		// se traducen en renglones
		if(getVendible(servicio.getCodigo())!=null && getVendible(servicio.getCodigo()).getTipo().equals(TipoItem.Servicio)){
			
			getVenta(codigoVenta).agregarRenglon(servicio, 1);
			cantidaddeServiviosVendidios++;
			
		}else {
			throw new VendibleInexistenteExeption();
		}
	}

	public List<Producto> obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		// TODO: Obtiene una lista de productos cuyo stock es menor o igual al punto de
		// reposicion. El punto de reposicion, es un valor que
		// definimos de manera estrategica para que nos indique cuando debemos reponer
		// stock para no quedarnos sin productos
		
		List<Producto>productosStockMenor=new ArrayList<Producto>();
		
		for (Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if(key.getPuntoDeReposicion()>=val) {
				productosStockMenor.add(key);
			}
		}
		
		return productosStockMenor;
	}

	public List<Cliente> obtenerClientesOrdenadosPorRazonSocialDescendente() {
		// TODO: obtiene una lista de clientes ordenados por su razon social de manera
		// descendente
		List<Cliente>clientesOrdenadosPorRazonSocial=new ArrayList<Cliente>();
		
		for (Cliente cliente : clientes) {
			for (Cliente cliente2 : clientes) {
				if(cliente.getRazonSocial().compareTo(cliente2.getRazonSocial())>0 && !cliente.getCuit().equals(cliente2.getCuit())) {
					clientesOrdenadosPorRazonSocial.add(cliente);
				}
			}
		}
		
		return clientesOrdenadosPorRazonSocial;
	}

	public Set<Venta> ventaPorVendedor(Vendedor ve) {
		Set<Venta>ventas=new HashSet<Venta>();
		for (Venta v : ventas) {
			if(v.getVendedor().getDni().equals(ve.getDni())) {
				ventas.add(v);
			}
		}
		return ventas;
	}
	
	public Map<Vendedor, Set<Venta>> obtenerVentasPorVendedor() {
		// TODO: Obtiene un mapa que contiene las ventas realizadas por cada vendedor.
		
		Map<Vendedor,Set<Venta>> ventasPorVendedor=new HashMap<Vendedor, Set<Venta>>();
		
		for (Venta v : ventas) {
			ventasPorVendedor.putIfAbsent(v.getVendedor(), ventaPorVendedor(v.getVendedor()));
		}
		
		
		return ventasPorVendedor;
	}

	public Double obtenerTotalDeVentasDeServicios() {
		// TODO: obtiene el total acumulado de los vendibles que son servicios incluidos
		// en todas las ventas.
		// Si una venta incluye productos y servicios, solo nos interesa saber el total
		// de los servicios
		
		
		return cantidaddeServiviosVendidios;
	}
}
