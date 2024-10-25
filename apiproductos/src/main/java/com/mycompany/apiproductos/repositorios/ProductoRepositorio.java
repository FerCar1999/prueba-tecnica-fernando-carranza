/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apiproductos.repositorios;

import com.mycompany.apiproductos.modelos.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fer
 */
@Stateless
public class ProductoRepositorio {
    @PersistenceContext
    private EntityManager em;
    
    public List<Producto> listar() {
        return em.createQuery("SELECT p FROM Producto p WHERE p.deletedAt IS NULL", Producto.class)
                 .getResultList();
    }
    
    public List<Producto> buscarPorCategoria(UUID categoriaId){
        return em.createQuery("SELECT p FROM Producto p WHERE p.categoria.id=:categoriaId AND p.deletedAt IS NULL", Producto.class)
                .setParameter("categoriaId",categoriaId).getResultList();
    }

    public Producto buscarPorId(UUID id) {
        return em.find(Producto.class, id);
    }

    public void guardar(Producto producto) {
        em.merge(producto);
    }

    public void eliminar(UUID id) {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            producto.setDeletedAt(LocalDateTime.now());
            em.merge(producto);
        }
    }
}
