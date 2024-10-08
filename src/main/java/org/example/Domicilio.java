package org.example;

import javax.persistence.*;
import java.io.Serializable;

//Para indicar que la clase se debe pasar como entidad al modelo relacional de bd.
@Entity
//Para aclararles a JPA e Hibernate que esta clase debe generar una tabla "domicilio" en la bd.
@Table(name = "domicilio")

public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    //Indicar que esta es la clave primaria, y que es autoincremental.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCalle")
    private String nombreCalle;

    @Column(name = "numero")
    private int numero;

    //Permite generar la relación inversa; bidireccionalidad; acceder desde domicilio a cliente.
    //El mappedBy recibe como parámetro el "dueño" de la relación, osea el private Domicilio domicilio que aparece en la clase Cliente.
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;

    public Domicilio() {
    }

    public Domicilio(String nombreCalle, int numero) {
        this.numero = numero;
        this.nombreCalle = nombreCalle;
    }

    public Domicilio(String nombreCalle, int numero, Cliente cliente) {
        this.nombreCalle = nombreCalle;
        this.numero = numero;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
