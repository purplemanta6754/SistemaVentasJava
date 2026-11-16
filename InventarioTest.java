import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventarioTest {
    private Inventario inventario;

    @Before
    public void setUp() {
        inventario = new Inventario();
    }

    @Test
    public void testAgregarStock() {
        // Caso 1: Registrar producto y sumar cantidad recibida si ya existe
        inventario.registrarProducto("Manzanas", 10);
        Producto producto = inventario.buscarProducto("Manzanas");
        assertNotNull("El producto debería existir en el inventario", producto);
        assertEquals("El stock inicial debe ser 10", 10, producto.getStock());

        // Sumar stock recibido al producto ya existente
        inventario.registrarProducto("Manzanas", 5);
        assertEquals("El stock acumulado debe ser 15", 15, producto.getStock());
    }

    @Test
    public void testRestarStock() {
        // Caso 2: Venta válida que resta unidades correctamente
        inventario.registrarProducto("Leche", 12);
        boolean ventaExitosa = inventario.venderProducto("Leche", 4);

        assertTrue("La venta debe realizarse exitosamente", ventaExitosa);
        Producto producto = inventario.buscarProducto("Leche");
        assertEquals("El stock restante debe ser 8", 8, producto.getStock());
    }

    @Test
    public void testRechazarVentaExcesoStock() {
        // Caso 3: Rechazar una venta que supere las unidades disponibles sin permitir stock negativo
        inventario.registrarProducto("Pan", 5);
        boolean ventaExitosa = inventario.venderProducto("Pan", 10);

        assertFalse("La venta no debe concretarse si excede el stock disponible", ventaExitosa);
        Producto producto = inventario.buscarProducto("Pan");
        assertEquals("El stock no debe modificarse si la venta es rechazada", 5, producto.getStock());
    }

    // Permite ejecutar las pruebas directamente desde la terminal con: java -cp ".:lib/*" InventarioTest
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("InventarioTest");
    }
}
