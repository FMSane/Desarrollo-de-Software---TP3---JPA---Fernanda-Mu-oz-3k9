package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Para indicar que la clase se debe pasar como entidad al modelo relacional de bd.
@Entity
//Para aclararles a JPA e Hibernate que esta clase debe generar una tabla "categoria" en la bd.
@Table(name = "categoria")

public class Categoria implements Serializable{
    private static final long serialVersionUID = 1L;

    //Indicar que esta es la clave primaria, y que es autoincremental.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "denominacion")
    private String denominacion;

    @ManyToMany(mappedBy = "categorias")
    private List<Articulo> articulos = new ArrayList<Articulo>();

    public Categoria() {
    }

    public Categoria(String denominacion) {
        this.denominacion = denominacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
}
