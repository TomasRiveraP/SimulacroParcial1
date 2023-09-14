package restaurante;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author ESTUDIANTE
 */
///Los diferentes metodos para los pedidos(agregar plato, modificar plato y mostrar pedido
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
