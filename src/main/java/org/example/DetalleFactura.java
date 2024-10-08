package org.example;

import javax.persistence.*;
import java.io.Serializable;

//Para indicar que la clase se debe pasar como entidad al modelo relacional de bd.
@Entity
//Para aclararles a JPA e Hibernate que esta clase debe generar una tabla "detalleFactura" en la bd.
@Table(name = "detalleFactura")

public class DetalleFactura implements Serializable{
    private static final long serialVersionUID = 1L;

    //Indicar que esta es la clave primaria, y que es autoincremental.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private float subtotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    //Sólo se va a cascadear la persistencia del objeto:
    //Esto es para relación bidireccional con Factura. Comentar si se quiere hacer una relación uni:
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_factura")
    private Factura factura;

    public DetalleFactura() {
    }

    public DetalleFactura(int cantidad, float subtotal) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DetalleFactura(int cantidad, float subtotal, Factura factura) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.factura = factura;
    }

    public DetalleFactura(int cantidad, float subtotal, Articulo articulo, Factura factura) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.articulo = articulo;
        this.factura = factura;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
