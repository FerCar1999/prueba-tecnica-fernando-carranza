package com.mycompany.apiproductos.modelos;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(generator = "GenerationType.IDENTITY")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
