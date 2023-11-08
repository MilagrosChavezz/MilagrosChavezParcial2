package ar.edu.unlam.pb2.Parcial01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TiendaTest {

	/**
	 * Resolver los siguientes tests
	 * 
	 * @throws VendedorDeLicenciaException
	 */

	@Test(expected = VendedorDeLicenciaException.class)
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException()
			throws VendedorDeLicenciaException {
		Tienda tienda = new Tienda("212312", "tienda");
		Vendedor vendedor = new Vendedor("34242324321", "kevin");
		Producto poducto = new Producto(1, "mouse", 213.0, 2);
		Cliente cliente = new Cliente("121331213", "razonSocial");

		vendedor.setDeLicencia(true);
		tienda.agregarProducto(poducto);
		tienda.agregarVendedor(vendedor);

		tienda.agregarVenta(new Venta("738", cliente, vendedor));
		assertTrue(true);
	}

	@Test(expected = VendibleInexistenteExeption.class)
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException()
			throws VendedorDeLicenciaException, VendibleInexistenteExeption {
		Tienda tienda = new Tienda("212312", "tienda");
		Vendedor vendedor = new Vendedor("34242324321", "kevin");
		Producto poducto = new Producto(1, "mouse", 213.0, 2);
		Cliente cliente = new Cliente("121331213", "razonSocial");

		vendedor.setDeLicencia(false);

		tienda.agregarVendedor(vendedor);
		tienda.agregarVenta(new Venta("738", cliente, vendedor));
		tienda.agregarProductoAVenta("738", poducto);

		assertTrue(true);
	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		Tienda tienda = new Tienda("212312", "tienda");
		Vendedor vendedor = new Vendedor("34242324321", "kevin");
		Producto poducto = new Producto(1, "mouse", 213.0, 2);
		Producto poducto2 = new Producto(2, "mouse", 213.0, 2);
		Producto poducto3 = new Producto(3, "mouse", 213.0, 2);
		Producto poducto4 = new Producto(4, "mouse", 213.0, 2);

		tienda.agregarStock(poducto4, 2);
		tienda.agregarStock(poducto, 23);
		tienda.agregarStock(poducto3, 1);
		tienda.agregarStock(poducto2, 2);

		List<Producto> listaEsperada = new ArrayList<Producto>();
		listaEsperada.add(poducto4);
		listaEsperada.add(poducto3);
		listaEsperada.add(poducto2);

		List<Producto> listaProductosStockMenor = tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion();

		assertTrue(listaProductosStockMenor.containsAll(listaProductosStockMenor));

	}

	@Test
	public void queSePuedaObtenerUnSetDeClientesOrdenadosPorRazonSocialDescendente() {

		Tienda tienda = new Tienda("212312", "tienda");

		Cliente cliente = new Cliente("1", "A");
		Cliente cliente2 = new Cliente("12", "B");
		Cliente cliente3 = new Cliente("121", "C");
		Cliente cliente4 = new Cliente("1213", "D");

		tienda.agregarCliente(cliente);
		tienda.agregarCliente(cliente4);
		tienda.agregarCliente(cliente2);
		tienda.agregarCliente(cliente3);

		List<Cliente> SetDeClientesOrdenadosPorRazonSocialDescendente = tienda
				.obtenerClientesOrdenadosPorRazonSocialDescendente();

		assertEquals("D", SetDeClientesOrdenadosPorRazonSocialDescendente.get(0).getRazonSocial());

	}

//	@Test
//	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor()
//			throws VendedorDeLicenciaException, VendibleInexistenteExeption {
//		// TODO: usar como key el vendedor y Set<Venta> para las ventas
//		Tienda tienda = new Tienda("212312", "tienda");
//		Vendedor vendedor = new Vendedor("1", "kevin");
//		Vendedor vendedor2 = new Vendedor("2", "mili");
//		Vendedor vendedor3 = new Vendedor("3", "Ben");
//
//		Producto poducto = new Producto(1, "mouse", 213.0, 2);
//		Producto poducto2 = new Producto(2, "mouse", 213.0, 2);
//		Producto poducto3 = new Producto(3, "mouse", 213.0, 2);
//		Producto poducto4 = new Producto(4, "mouse", 213.0, 2);
//		Cliente cliente = new Cliente("1", "A");
//
//		tienda.agregarProducto(poducto3);
//		tienda.agregarProducto(poducto2);
//		tienda.agregarProducto(poducto);
//		tienda.agregarProducto(poducto4);
//
//		tienda.agregarStock(poducto4, 2);
//		tienda.agregarStock(poducto, 23);
//		tienda.agregarStock(poducto3, 1);
//		tienda.agregarStock(poducto2, 2);
//
//		tienda.agregarVendedor(vendedor);
//		tienda.agregarVendedor(vendedor2);
//		tienda.agregarVendedor(vendedor3);
//
//		tienda.agregarVenta(new Venta("738", cliente, vendedor));
//		tienda.agregarProductoAVenta("738", poducto);
//
//		tienda.agregarVenta(new Venta("78", cliente, vendedor2));
//		tienda.agregarProductoAVenta("738", poducto3);
//
//		tienda.agregarVenta(new Venta("78", cliente, vendedor2));
//		tienda.agregarProductoAVenta("78", poducto2);
//
//		Map<Vendedor, Set<Venta>> ventasOrdenadasPorVendedor = tienda.obtenerVentasPorVendedor();
//		ventasOrdenadasPorVendedor.get(vendedor).size();
//
//		assertEquals(1, ventasOrdenadasPorVendedor.get(vendedor).size());
//		assertEquals(2, ventasOrdenadasPorVendedor.get(vendedor2).size());
//
//	}

	@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios()
			throws VendedorDeLicenciaException, VendibleInexistenteExeption {
		Tienda tienda = new Tienda("212312", "tienda");
		Vendedor vendedor = new Vendedor("34242324321", "kevin");
		Servicio servicio = new Servicio(1, "s1", 12.0, "01012001", "01012001");
		Servicio servicio2 = new Servicio(2, "s1", 12.0, "01012001", "01012001");
		Servicio servicio3 = new Servicio(3, "s1", 12.0, "01012001", "01012001");
		Cliente cliente = new Cliente("1", "A");

		tienda.agregarServicio(servicio3);
		tienda.agregarServicio(servicio);
		tienda.agregarServicio(servicio2);

		tienda.agregarVenta(new Venta("2", cliente, vendedor));
		tienda.agregarVenta(new Venta("7", cliente, vendedor));
		tienda.agregarVenta(new Venta("1", cliente, vendedor));

		tienda.agregarServicioAVenta("2", servicio3);
		tienda.agregarServicioAVenta("7", servicio);
		tienda.agregarServicioAVenta("1", servicio2);

		Double vo = tienda.obtenerTotalDeVentasDeServicios();

		assertEquals(3.0, vo, 0.01);

	}

	@Test
	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente()
			throws VendedorDeLicenciaException, VendibleInexistenteExeption {

		Tienda tienda = new Tienda("212312", "tienda");
		Vendedor vendedor = new Vendedor("34242324321", "kevin");
		Producto poducto = new Producto(1, "mouse", 213.0, 2);
		Cliente cliente = new Cliente("121331213", "razonSocial");

		tienda.agregarProducto(poducto);
		tienda.agregarStock(poducto, 3);
		

		tienda.agregarVendedor(vendedor);

		tienda.agregarVenta(new Venta("738", cliente, vendedor));
		tienda.agregarProductoAVenta("738", poducto);

		Integer v0 = tienda.getStock(poducto);

		assertEquals((Integer) 2, v0);
	}
}
