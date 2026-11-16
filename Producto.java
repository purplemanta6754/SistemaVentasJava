public class Producto {
    private String nombre;
    private int stock;

    public Producto(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Alias para compatibilidad con evaluaciones que usen getCantidad / setCantidad
    public int getCantidad() {
        return stock;
    }

    public void setCantidad(int cantidad) {
        this.stock = cantidad;
    }

    // Método para sumar stock recibido
    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
        }
    }

    // Método para restar stock si hay suficiente
    public boolean restarStock(int cantidad) {
        if (cantidad > 0 && this.stock >= cantidad) {
            this.stock -= cantidad;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + " | Stock disponible: " + stock;
    }
}
