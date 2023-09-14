package restaurante;
/**
 *
 * @author Tomas Rivera
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Restaurante {
    
    private static java.sql.Connection con;
    
    public static String driver  = "com.mysql.jdbc.Driver";
    public static String user = "root";
    public static String pass = "";
    public static String url = "jdbc:mysql://localhost:3306/test1";
    
    
    private static List<Plato> menu = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException{
        
        //iniciar();
        // Crear el menú
        menu.add(new Plato("Hamburguesa", 8.99));
        menu.add(new Plato("Pizza", 10.99));
        menu.add(new Plato("Ensalada", 6.99));

        int opcion;
        do {
            System.out.println("Seleccione una opcion a seguir: ");
            System.out.println("1. Ver menu \t2. Realizar Pedido \t3. Mostrar Pedidos \t4. Salir del Restaurante");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    mostrarMenu();
                    break;
                case 2:
                    realizarPedido(con);
                    break;
                case 3:
                    mostrarPedidos();
                    break;
                case 4:
                    // Salir del programa
                    System.out.println("Saliendo del restaurante.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void mostrarMenu() {
        System.out.println("Menu Disponible:");
        
        for (int i = 0; i < menu.size(); i++) {
            Plato plato = menu.get(i);
            System.out.println(i + 1 + ". " + plato.getNombre() + " - $" + plato.getPrecio());
        }
    }

    public static void realizarPedido(Connection conexion) {
        Pedido pedido = new Pedido();
        System.out.println("Agregue platos al pedido (ingrese el numero de plato y cantidad):");
        mostrarMenu();

        int opcionPlato;
        do {
            opcionPlato = scanner.nextInt();
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            if (opcionPlato >= 1 && opcionPlato <= menu.size() && cantidad > 0) {
                Plato plato = menu.get(opcionPlato - 1);
                pedido.agregarPlato(plato, cantidad);
                System.out.println("Plato agregado al pedido.");
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }

            System.out.println("¿Desea agregar otro plato? (1: Sí / 0: No)");
        } while (scanner.nextInt() == 1);
        
        con = null;
        try {

            con =  DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Conexión exitosa");
            }
            insertarPedidoEnBaseDeDatos(con, pedido);
            } catch (SQLException e) {
                System.out.println("Conexión no exitosa");
            }

        pedidos.add(pedido);
        System.out.println("Pedido realizado con exito.");
    }

    private static void mostrarPedidos() {
        System.out.println("Pedidos Realizados:");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            System.out.println("Pedido " + (i + 1) + ":");
            pedido.mostrarPedido();
        }
    }
    public static void iniciar(){
        con = null;
        try {

            con =  DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException e) {
            System.out.println("Conexión no exitosa");
        }
        
        
        
    }
    private static void insertarPedidoEnBaseDeDatos(Connection conexion, Pedido pedido) {
    String sql = "INSERT INTO pedidos (cantidad, plato, precio ) VALUES (?, ?, ?)";

    try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
        for (Map.Entry<Plato, Integer> entry : pedido.getItems().entrySet()) {
            int cantidad = entry.getValue();
            Plato plato = entry.getKey();
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setString(2, plato.getNombre());
            preparedStatement.setDouble(3, plato.getPrecio());
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        System.err.println("Error al insertar el pedido en la base de datos: " + e.getMessage());
        }
    } 
}

    

