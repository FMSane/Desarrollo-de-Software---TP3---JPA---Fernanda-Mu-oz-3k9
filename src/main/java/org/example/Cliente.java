package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Para indicar que la clase se debe pasar como entidad al modelo relacional de bd.
@Entity
//Para aclararles a JPA e Hibernate que esta clase debe generar una tabla "cliente" en la bd.
@Table(name = "cliente")

//Para hacer los objetos persistentes. Se transformarán en una secuencia de bytes para poder almacenarlos en una bd.
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    //Indicar que esta es la clave primaria, y que es autoincremental.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Avisa que estos atributos serán columna de la tabla cliente.
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    //Para que en la tabla no se puedan ingresar dos registros que tengan el mismo dni.
    @Column(name = "dni", unique = true)
    private int dni;

    //Indicar el tipo de relación entre dos clases
    //Entre paréntesis del OneToOne va el tipo de cascadeo (la forma en que se van a propagar los cambios del padre al hijo)
    //ALL es para que cualquier cambio que se realice en el cliente, se va a ver reflejado en el domicilio del mismo (persistir a la vez el cliente y el domicilio). Si se borra el cliente, también se borra el domicilio.
    @OneToOne(cascade = CascadeType.ALL)
    //Crear una columna en clienteque va a contener la clave foránea de domicilio
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();

    public Cliente(String nombre, String apellido, int dni, Domicilio domicilio, List<Factura> facturas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.facturas = facturas;
    }

    //Constructor
    public Cliente(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    //Constructor vacío
    public Cliente() {
    }

    //Constructor con el campo domicilio incluído.
    public Cliente(String nombre, String apellido, int dni, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
    }

    //Getters y Setters

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
