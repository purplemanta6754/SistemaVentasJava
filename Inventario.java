import java.util.ArrayList;
import java.util.Iterator;

public class Inventario {
    private final ArrayList<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    // Buscamos un producto por su nombre (ignorando mayúsculas y minúsculas)
    public Producto buscarProducto(String nombre) {
        if (nombre == null) {
            return null;
        }
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    // Caso 1: Registrar producto. Si ya existe, sumamos la cantidad al stock actual.
    public void registrarProducto(String nombre, int cantidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre del producto no puede estar vacío.");
            return;
        }
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad ingresada debe ser mayor a 0.");
            return;
        }

        Producto existente = buscarProducto(nombre);
        if (existente != null) {
            existente.agregarStock(cantidad);
            System.out.println("El producto '" + existente.getNombre() + "' ya existe. Se añadieron " 
                    + cantidad + " unidades. Stock total: " + existente.getStock());
        } else {
            Producto nuevo = new Producto(nombre.trim(), cantidad);
            productos.add(nuevo);
            System.out.println("Producto '" + nuevo.getNombre() + "' registrado exitosamente con stock inicial de " + cantidad + " unidades.");
        }
    }

    // Sobrecargas y alias de registro para compatibilidad
    public void registrarProducto(Producto producto) {
        if (producto != null) {
            registrarProducto(producto.getNombre(), producto.getStock());
        }
    }

    public void agregarProducto(String nombre, int cantidad) {
        registrarProducto(nombre, cantidad);
    }

    public void agregarProducto(Producto producto) {
        registrarProducto(producto);
    }

    // Caso 2: Vender producto deduciendo unidades sin permitir stock negativo ni exceder disponibilidad.
    public boolean venderProducto(String nombre, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad a vender debe ser mayor a 0.");
            return false;
        }

        Producto producto = buscarProducto(nombre);
        if (producto == null) {
            System.out.println("Error: El producto '" + nombre + "' no se encuentra en el inventario.");
            return false;
        }

        if (cantidad > producto.getStock()) {
            System.out.println("Venta rechazada: La cantidad solicitada (" + cantidad 
                    + ") supera el stock disponible (" + producto.getStock() + ").");
            return false;
        }

        boolean ventaExitosa = producto.restarStock(cantidad);
        if (ventaExitosa) {
            System.out.println("Venta realizada: Se vendieron " + cantidad + " unidades de '" 
                    + producto.getNombre() + "'. Stock restante: " + producto.getStock());
        }
        return ventaExitosa;
    }

    // Alias para vender
    public boolean vender(String nombre, int cantidad) {
        return venderProducto(nombre, cantidad);
    }

    // Caso 3: Mostrar el inventario completo (nombre y stock) usando Iterator API
    public void mostrarInventario() {
        System.out.println("\n--- INVENTARIO COMPLETO ---");
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        Iterator<Producto> iter = productos.iterator();
        while (iter.hasNext()) {
            Producto p = iter.next();
            System.out.println(p);
        }
    }

    // Caso 4: Mostrar productos con stock crítico (5 unidades o menos).
    public void mostrarStockCritico() {
        System.out.println("\n--- PRODUCTOS CON STOCK CRÍTICO (<= 5 unidades) ---");
        ArrayList<Producto> criticos = obtenerStockCritico();
        if (criticos.isEmpty()) {
            System.out.println("No hay productos en estado crítico.");
        } else {
            for (Producto p : criticos) {
                System.out.println(p + " [ALERTA: STOCK CRÍTICO]");
            }
        }
    }

    // Devuelve la lista de productos con stock <= 5
    public ArrayList<Producto> obtenerStockCritico() {
        ArrayList<Producto> criticos = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getStock() <= 5) {
                criticos.add(p);
            }
        }
        return criticos;
    }

    public ArrayList<Producto> getProductosCriticos() {
        return obtenerStockCritico();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
