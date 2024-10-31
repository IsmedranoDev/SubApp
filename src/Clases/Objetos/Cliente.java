package Clases.Objetos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

/**
 * La clase Cliente extiende de Buceador y representa a un cliente que realiza actividades de buceo. 
 * Incluye atributos específicos como el número de buceos realizados y el nivel de experiencia del cliente.
 * 
 * Esta clase se puede utilizar para almacenar y gestionar información de los clientes en un sistema de registro de buceo.
 * Los datos de cliente también pueden ser serializados desde/deserializados a formato JSON.
 * 
 * <p>Ejemplo de creación de un objeto Cliente:</p>
 * <pre>
 *     Cliente cliente = new Cliente(1, "Juan", "Pérez", new Date(), "Calle 123", "123456789", 
 *                                   "juan@example.com", "PADI Open Water", 30, "Avanzado");
 * </pre>
 * 
 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente extends Buceador {
    
    /** Número total de buceos realizados por el cliente */
    private int numeroBuceos;
    
    /** Nivel de experiencia del cliente en buceo */
    private String nivel;

    /**
     * Constructor por defecto de la clase Cliente.
     */
    public Cliente() {
        super();
    }

    /**
     * Constructor completo que permite inicializar un objeto Cliente con todos sus atributos.
     * 
     * @param Id Identificador único del cliente
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param fechaNacimiento Fecha de nacimiento del cliente
     * @param direccion Dirección física del cliente
     * @param telefono Número de teléfono del cliente
     * @param email Dirección de correo electrónico del cliente
     * @param certificaciones Certificaciones de buceo que posee el cliente
     * @param numeroBuceos Número total de buceos realizados por el cliente
     * @param nivel Nivel de experiencia en buceo del cliente
     */
    public Cliente(int Id, String nombre, String apellidos, Date fechaNacimiento, String direccion, 
                   String telefono, String email, String certificaciones, int numeroBuceos, String nivel) {
        super(Id, nombre, apellidos, fechaNacimiento, direccion, telefono, email, certificaciones);
        this.nivel = nivel;
        this.numeroBuceos = numeroBuceos;
    }

    /**
     * Obtiene el nivel de experiencia en buceo del cliente.
     * 
     * @return Nivel de experiencia en buceo del cliente
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel de experiencia en buceo del cliente.
     * 
     * @param nivel Nivel de experiencia en buceo del cliente
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Obtiene el número total de buceos realizados por el cliente.
     * 
     * @return Número total de buceos realizados
     */
    public int getNumeroBuceos() {
        return numeroBuceos;
    }

    /**
     * Establece el número total de buceos realizados por el cliente.
     * 
     * @param numeroBuceos Número total de buceos realizados por el cliente
     */
    public void setNumeroBuceos(int numeroBuceos) {
        this.numeroBuceos = numeroBuceos;
    }
}
