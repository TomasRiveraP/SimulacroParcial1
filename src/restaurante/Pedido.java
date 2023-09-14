package restaurante;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ESTUDIANTE
 */
public class Pedido {
    final String  tabla = "PEDIDOS";
    
    private Map<Plato, Integer> items = new HashMap<>();

    public void agregarPlato(Plato plato, int cantidad) {
        items.put(plato, cantidad);
    }

    public void modificarPlato(Plato plato, int nuevaCantidad) {
        if (items.containsKey(plato)) {
            items.put(plato, nuevaCantidad);
        }
    }

    public void mostrarPedido() {
        double total = 0;
        System.out.println("Pedido:");
        for (Map.Entry<Plato, Integer> entry : items.entrySet()) {
            Plato plato = entry.getKey();
            int cantidad = entry.getValue();
            double subtotal = plato.getPrecio() * cantidad;
            total += subtotal;
            System.out.println(plato.getNombre() + " x" + cantidad + ": $" + subtotal);
        }
        System.out.println("Total: $" + total);
    }
    public Map<Plato, Integer> getItems() {
        return items;
    }  
        
}
