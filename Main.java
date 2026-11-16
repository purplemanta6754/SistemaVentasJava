import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        // Opcional: cargar algunos datos iniciales de prueba
        inventario.registrarProducto("Arroz 1kg", 20);
        inventario.registrarProducto("Fideos 400g", 4);
        inventario.registrarProducto("Aceite 1L", 5);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n==========================================");
            System.out.println("       SISTEMA DE GESTIÓN DE BODEGA       ");
            System.out.println("==========================================");
            System.out.println("1. Registrar producto / Agregar stock");
            System.out.println("2. Vender producto (descontar stock)");
            System.out.println("3. Mostrar inventario completo");
            System.out.println("4. Mostrar productos con stock crítico (<= 5)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String entrada = scanner.nextLine().trim();

            switch (entrada) {
                case "1":
                    System.out.print("\nIngrese el nombre del producto: ");
                    String nombreRegistro = scanner.nextLine().trim();
                    System.out.print("Ingrese la cantidad a registrar: ");
                    try {
                        int cantidadRegistro = Integer.parseInt(scanner.nextLine().trim());
                        inventario.registrarProducto(nombreRegistro, cantidadRegistro);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un número entero válido.");
                    }
                    break;

                case "2":
                    System.out.print("\nIngrese el nombre del producto a vender: ");
                    String nombreVenta = scanner.nextLine().trim();
                    System.out.print("Ingrese la cantidad a vender: ");
                    try {
                        int cantidadVenta = Integer.parseInt(scanner.nextLine().trim());
                        inventario.venderProducto(nombreVenta, cantidadVenta);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un número entero válido.");
                    }
                    break;

                case "3":
                    inventario.mostrarInventario();
                    break;

                case "4":
                    inventario.mostrarStockCritico();
                    break;

                case "5":
                    System.out.println("\n¡Gracias por utilizar el sistema de bodega! Saliendo...");
                    continuar = false;
                    break;

                default:
                    System.out.println("\nOpción no válida. Por favor, ingrese un número del 1 al 5.");
                    break;
            }
        }

        scanner.close();
    }
}
