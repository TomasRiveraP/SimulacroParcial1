
package restaurante;

/**
 *
 * @author ESTUDIANTE
 */
///Se declaran los atributos de la clase plato y los metodos para su creacion, y los get)
public class Plato {
    private String nombre;
    
    private double precio;

    public Plato(String nombre, double precio) {
        this.nombre = nombre;
        
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
}
